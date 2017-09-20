import Search.Heuristic;
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
        SearchNode root = new RushHour("rush_hour_problems/medium.txt");
        //System.out.println(SearchAlgorithms.AStar(root, Heuristic::zeroHeuristic, false));
        //System.out.println(SearchAlgorithms.AStar(root, Heuristic::manhattenHeuristic, false));
        //System.out.println(SearchAlgorithms.AStar(root, Heuristic::simpleBlockingHeuristic, false));
        System.out.println(SearchAlgorithms.breadthFirstSearch(root));
        System.out.println(SearchAlgorithms.depthFirstSearch(root));
        System.out.println(SearchAlgorithms.AStar(root, Heuristic::advancedHeuristic, false));
    }
}