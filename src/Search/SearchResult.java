package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResult {
    private String algorithm;
    private SearchNode goal;
    private int totalNodes;
    private long timeElapsed;

    public SearchResult(SearchNode goal, int totalNodes, long timeElapsed, String algorithm) {
        this.goal = goal;
        this.totalNodes = totalNodes;
        this.timeElapsed = timeElapsed;
        this.algorithm = algorithm;
    }

    public static List<SearchNode> extractPath(SearchNode goal) {
        List<SearchNode> path = new ArrayList<>();
        SearchNode current = goal;
        while (current.getParent() != null){
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public void visualizePath(int time){
        List<SearchNode> path = extractPath(this.goal);
        path.forEach(node -> {
            node.printState();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public String toString() {
        return "SearchResult:" + "\n" +
                "Algorithm = " + algorithm + "\n" +
                "Moves in solution = " + extractPath(this.goal).size() + "\n" +
                "Time Elapsed = " + this.timeElapsed / 1000 + " seconds" + "\n" +
                "Total nodes = " + this.totalNodes + "\n";
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public SearchNode getGoal() {
        return goal;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }
}
