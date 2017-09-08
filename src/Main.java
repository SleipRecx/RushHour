import Search.SearchAlgorithms;
import Search.SearchNode;
import RushHour.*;
import Search.SearchResult;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        SearchNode rushHour = new RushHour("rush_hour_problems/easy.txt");
        SearchResult result = SearchAlgorithms.AStar(rushHour);
        result.visualizePath(500);
        System.out.println(result);

    }
}