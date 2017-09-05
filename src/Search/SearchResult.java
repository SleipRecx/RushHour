package Search;

import java.util.List;

public class SearchResult {

    private List<SearchNode> result;
    private int operations;

    public SearchResult(List<SearchNode> result, int operations) {
        this.result = result;
        this.operations = operations;
    }

    public List<SearchNode> getResult() {
        return result;
    }

    public void setResult(List<SearchNode> result) {
        this.result = result;
    }

    public int getOperations() {
        return operations;
    }

    public void setOperations(int operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "result=" + result +
                ", operations=" + operations +
                '}';
    }
}
