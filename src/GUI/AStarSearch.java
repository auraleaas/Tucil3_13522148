package GUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class AStarSearch {
    // Calculates the Hamming distance between two words
    public static int hammingDistance(String word1, String word2) {
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
    
    // Performs A* search algorithm to find the shortest path from beginWord to endWord
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
}

