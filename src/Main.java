import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {

    // ------------------------------------ UCS -----------------------------------

    public static Pair<List<String>, Integer> uniformCostSearch(String beginWord, String endWord, List<String> wordList) {
        Set<String> myset = new HashSet<>();
        boolean isPresent = false;

        // Insert all words from Dict in myset
        for (String word : wordList) {
            if (endWord.equals(word))
                isPresent = true;
            myset.add(word); // Insert word in Dict
        }
        if (!isPresent) // endWord is not present in Dict
            return new Pair<>(new ArrayList<>(), 0);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(beginWord, 0, Arrays.asList(beginWord))); // Start node
        Set<String> visited = new HashSet<>(); // Track visited nodes
        List<String> resultPath = new ArrayList<>();
        int nodesVisited = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            nodesVisited++;

            if (curr.word.equals(endWord)) {
                resultPath = curr.path;
                return new Pair<>(resultPath, nodesVisited);
            }

            visited.add(curr.word);

            // Check for all possible 1-depth words
            for (int i = 0; i < curr.word.length(); ++i) {
                StringBuilder temp = new StringBuilder(curr.word);
                for (char c = 'a'; c <= 'z'; ++c) {
                    temp.setCharAt(i, c);
                    String tempWord = temp.toString();
                    if (tempWord.equals(curr.word) || visited.contains(tempWord) || !myset.contains(tempWord))
                        continue; // Skip the same word, visited words, and words not in myset

                    List<String> newPath = new ArrayList<>(curr.path);
                    newPath.add(tempWord);
                    pq.add(new Node(tempWord, curr.cost + 1, newPath));
                }
            }
        }

        // If end word is not reachable
        return new Pair<>(new ArrayList<>(), nodesVisited);
    }




    // ---------------------------- GREEDY BFS ----------------------------

    // Function to calculate the Hamming distance between two words
    public static int hammingDistance(String word1, String word2) {
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public static Pair<List<String>, Integer> greedyBestFirstSearch(String beginWord, String endWord, List<String> wordList) {
        Set<String> myset = new HashSet<>(wordList);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> hammingDistance(a.word, endWord)));
        pq.add(new Node(beginWord, 0, Arrays.asList(beginWord))); // Start node
        Set<String> visited = new HashSet<>(); // Track visited nodes
        List<String> resultPath = new ArrayList<>();
        int nodesVisited = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            nodesVisited++;

            if (curr.word.equals(endWord)) {
                resultPath = curr.path;
                return new Pair<>(resultPath, nodesVisited);
            }

            visited.add(curr.word);

            // Check for all possible 1-depth words
            for (int i = 0; i < curr.word.length(); ++i) {
                StringBuilder temp = new StringBuilder(curr.word);
                for (char c = 'a'; c <= 'z'; ++c) {
                    temp.setCharAt(i, c);
                    String tempWord = temp.toString();
                    if (tempWord.equals(curr.word) || visited.contains(tempWord) || !myset.contains(tempWord))
                        continue; // Skip the same word, visited words, and words not in myset

                    List<String> newPath = new ArrayList<>(curr.path);
                    newPath.add(tempWord);
                    pq.add(new Node(tempWord, curr.cost + 1, newPath));
                }
            }
        }

        // If end word is not reachable
        return new Pair<>(new ArrayList<>(), nodesVisited);
    }



    // ---------------------------- A STAR -----------------------------------------------

    public static Pair<List<String>, Integer> aStarSearch(String beginWord, String endWord, List<String> wordList) {
        Set<String> myset = new HashSet<>(wordList);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (a.cost + hammingDistance(a.word, endWord))));
        pq.add(new Node(beginWord, 0, Arrays.asList(beginWord))); // Start node
        Set<String> visited = new HashSet<>(); // Track visited nodes
        List<String> resultPath = new ArrayList<>();
        int nodesVisited = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            nodesVisited++;

            if (curr.word.equals(endWord)) {
                resultPath = curr.path;
                return new Pair<>(resultPath, nodesVisited);
            }

            visited.add(curr.word);

            // Check for all possible 1-depth words
            for (int i = 0; i < curr.word.length(); ++i) {
                StringBuilder temp = new StringBuilder(curr.word);
                for (char c = 'a'; c <= 'z'; ++c) {
                    temp.setCharAt(i, c);
                    String tempWord = temp.toString();
                    if (tempWord.equals(curr.word) || visited.contains(tempWord) || !myset.contains(tempWord))
                        continue; // Skip the same word, visited words, and words not in myset

                    List<String> newPath = new ArrayList<>(curr.path);
                    newPath.add(tempWord);
                    pq.add(new Node(tempWord, curr.cost + 1, newPath));
                }
            }
        }

        // If end word is not reachable
        return new Pair<>(new ArrayList<>(), nodesVisited);
    }





    // ------------------------------------ MAIN --------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parse dict.txt
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("dict.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Enter begin word
        System.out.print("Enter the begin word: ");
        String beginWord = scanner.nextLine();

        if (beginWord.split(" ").length > 1) {
            System.out.println("Error: The begin word must contain only one word.");
            scanner.close();
            return;
        }

        if (!beginWord.chars().allMatch(Character::isLetter)) {
            System.out.println("Error: The begin word must contain only alphabetical characters.");
            scanner.close();
            return;
        }

        // Enter end word 
        System.out.print("Enter the end word: ");
        String endWord = scanner.nextLine();

        if (endWord.split(" ").length > 1) {
            System.out.println("Error: The end word must contain only one word.");
            scanner.close();
            return;
        }

        if (!endWord.chars().allMatch(Character::isLetter)) {
            System.out.println("Error: The end word must contain only alphabetical characters.");
            scanner.close();
            return;
        }


        if (!wordList.contains(endWord)) {
            System.out.println("Error: The end word is not present in the word list.");
            scanner.close();
            return;
        }
        
        // Check begin word and end word length
        if (beginWord.length() != endWord.length()) {
            System.out.println("Error: Begin word and end word must have the same length.");
            scanner.close();
            return;
        }
        
        System.out.println("\n\n");
        System.out.println("  ╔══════════════ CHOOSE ALGORITHM ═════════════╗");
        System.out.println("  ║                                             ║");
        System.out.println("  ║  1. Create using Uniform Cost Search        ║");
        System.out.println("  ║  2. Create using Greedy Best-first Search   ║");
        System.out.println("  ║  3. Create using A*                         ║");
        System.out.println("  ╚═════════════════════════════════════════════╝");
        System.out.println();
        System.out.print(">> Enter your option (1 / 2 / 3): ");
        String userInput = scanner.nextLine();


        if(userInput.equals("1")){
    
    
            long startTime = System.nanoTime();
            Pair<List<String>, Integer> result = uniformCostSearch(beginWord, endWord, wordList);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // Convert to microseconds
    
            if (!result.getKey().isEmpty()) {
                System.out.print("Path from " + beginWord + " to " + endWord + ": ");
                for (String word : result.getKey())
                    System.out.print(word + " ");
                System.out.println();
                System.out.println("Number of nodes visited: " + result.getValue());
                System.out.println("Execution time: " + duration + " miliseconds");
            } else {
                System.out.println("No path found from " + beginWord + " to " + endWord);
                System.out.println("Number of nodes visited: " + result.getValue());
                System.out.println("Execution time: " + duration + " miliseconds");
            }
    
            scanner.close();

        }else if(userInput.equals("2")){
    
            long startTime = System.nanoTime();
            Pair<List<String>, Integer> result = uniformCostSearch(beginWord, endWord, wordList);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // Convert to microseconds
    
            if (!result.getKey().isEmpty()) {
                System.out.print("Path from " + beginWord + " to " + endWord + ": ");
                for (String word : result.getKey())
                    System.out.print(word + " ");
                System.out.println();
                System.out.println("Number of nodes visited: " + result.getValue());
                System.out.println("Execution time: " + duration + " miliseconds");
            } else {
                System.out.println("No path found from " + beginWord + " to " + endWord);
                System.out.println("Number of nodes visited: " + result.getValue());
                System.out.println("Execution time: " + duration + " miliseconds");
            }
    
            scanner.close();

        }else{
    
            long startTime = System.nanoTime();
            Pair<List<String>, Integer> result = uniformCostSearch(beginWord, endWord, wordList);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // Convert to microseconds
    
            if (!result.getKey().isEmpty()) {
                System.out.print("Path from " + beginWord + " to " + endWord + ": ");
                for (String word : result.getKey())
                    System.out.print(word + " ");
                System.out.println();
                System.out.println("Number of nodes visited: " + result.getValue());
                System.out.println("Execution time: " + duration + " miliseconds");
            } else {
                System.out.println("No path found from " + beginWord + " to " + endWord);
                System.out.println("Number of nodes visited: " + result.getValue());
                System.out.println("Execution time: " + duration + " miliseconds");
            }
    
            scanner.close();
        }


    }
}






