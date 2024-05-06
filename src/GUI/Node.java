package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Auralea A S
 */
import java.util.ArrayList;
import java.util.List;

class Node {
    String word;
    int cost;
    List<String> path;

    public Node(String word, int cost, List<String> path) {
        this.word = word;
        this.cost = cost;
        this.path = new ArrayList<>(path);
    }
}

