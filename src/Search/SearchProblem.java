package Search;

import java.util.Set;

public interface SearchProblem {
    Boolean isSolution();
    Double h();
    Double g();
    Set<SearchProblem> generateSuccessors();
    void print();

}
