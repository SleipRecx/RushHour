package Search;

import java.util.Set;

/*
Interface to be implemented in order to support the general A* search algorithm
 */
public interface SearchNode {
    Set<SearchNode> generateSuccessors();
    Boolean isSolution();
    SearchNode getParent();
    void setParent(SearchNode parent);
    Double arcCost(SearchNode node);
    void setG(Double g);
    Double getG();
    Double getF();
    void printState();
}
