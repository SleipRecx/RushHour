import Search.SearchAlgorithms;
import Search.SearchNode;
import RushHour.*;
import Search.SearchResult;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        SearchNode rushHour = new RushHour("boards/easy.txt");
        System.out.println(SearchAlgorithms.breadthFirstSearch(rushHour));
        System.out.println(SearchAlgorithms.depthFirstSearch(rushHour));
        System.out.println(SearchAlgorithms.AStar(rushHour));
    }
}
