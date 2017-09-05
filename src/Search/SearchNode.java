package Search;

import java.util.Set;

public interface SearchNode {
    Boolean isSolution();
    Double h();
    Double g();
    Set<SearchNode> generateSuccessors();
    void print();

}
