package Search;

import java.util.Set;

public interface SearchNode {
    Set<SearchNode> generateSuccessors();
    Boolean isSolution();
    Double h();
    Double g();
}
