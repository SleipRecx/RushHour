import Search.SearchAlgorithms;
import Search.SearchNode;
import RushHour.*;
import Search.SearchResult;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        SearchNode rushHour = new RushHour("boards/hard.txt");
        SearchResult result = SearchAlgorithms.breadthFirstSearch(rushHour);
        SearchResult result2 = SearchAlgorithms.AStar(rushHour);
        System.out.println(result);
        System.out.println(result2);

        // SearchAlgorithms.depthFirstSearch(rushHour).forEach(SearchNode::print);
        //SearchAlgorithms.AStar(rushHour).forEach(SearchNode::print);
    }
}
