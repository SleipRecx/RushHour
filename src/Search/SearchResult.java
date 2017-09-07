package Search;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private String algorithm;
    private SearchNode goal;
    private int expanded;
    private int visited;

    public SearchResult(SearchNode goal, int expanded, int visited, String algorithm) {
        this.goal = goal;
        this.expanded = expanded;
        this.visited = visited;
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
        int totalNodes = visited + expanded;
        return "SearchResult:" + "\n" +
                "Algorithm = " + algorithm + "\n" +
                "Moves in solution = " + extractPath().size() + "\n" +
                "Expanded nodes = " + expanded + "\n" +
                "Visited nodes = " + visited + "\n" +
                "Total nodes = " + totalNodes + "\n";
    }

}
