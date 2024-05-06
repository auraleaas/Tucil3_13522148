package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.HashSet;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.util.Set;
 
 /**
  *
  * @author Auralea A S
  */
 class UniformCostSearch {
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
 }
 