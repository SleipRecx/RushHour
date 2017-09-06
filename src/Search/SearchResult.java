package Search;

import java.util.List;

public class SearchResult {
    private String algorithm;
    private List<SearchNode> path;
    private int expanded;
    private int visited;

    public SearchResult(List<SearchNode> path, int expanded, int visited, String algorithm) {
        this.path = path;
        this.expanded = expanded;
        this.visited = visited;
        this.algorithm = algorithm;
    }

    public List<SearchNode> getPath() {
        return path;
    }

    public int getPathLength() {
        return path.size();
    }

    public int getExpanded() {
        return expanded;
    }

    public int getVisited() {
        return visited;
    }

    @Override
    public String toString() {
        int totalNodes = visited + expanded;
        return "SearchResult:" + "\n" +
                "Algorithm = " + algorithm + "\n" +
                "Moves in solution = " + getPathLength() + "\n" +
                "Expanded nodes = " + expanded + "\n" +
                "Visited nodes = " + visited + "\n" +
                "Total nodes = " + totalNodes + "\n";
    }
}
