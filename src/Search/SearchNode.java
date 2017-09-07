package Search;

import java.util.Set;

public interface SearchNode {
    Set<SearchNode> generateSuccessors();
    Boolean isSolution();
    SearchNode getParent();
    void setParent(SearchNode parent);
    Double arcCost(SearchNode node);
    void setG(Double g);
    Double getG();
    Double getF();
}
