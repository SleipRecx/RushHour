package Search;

import java.util.ArrayList;
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

    List<SearchNode> extractPath() {
        List<SearchNode> path = new ArrayList<>();
        SearchNode current = this.goal;
        while (current.getParent() != null){
            path.add(current);
            current = current.getParent();
        }
        return path;
    }

    @Override
    public String toString() {
        return "SearchResult:" + "\n" +
                "Algorithm = " + algorithm + "\n" +
                "Moves in solution = " + extractPath().size() + "\n" +
                "Time Elapsed = " + this.timeElapsed / 1000 + " seconds" + "\n" +
                "Total nodes = " + this.totalNodes + "\n";
    }

}
