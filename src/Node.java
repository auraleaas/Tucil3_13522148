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