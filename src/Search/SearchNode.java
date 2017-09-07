package Search;

import java.util.Set;

public interface SearchNode {
    Set<SearchNode> generateSuccessors();
    SearchNode getParent();
    void setParent(SearchNode parent);
    Boolean isSolution();
    Double h();
    Double g();
}
