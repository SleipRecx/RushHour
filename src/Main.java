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
        SearchNode root = new RushHour("rush_hour_problems/hard.txt");
        System.out.println(SearchAlgorithms.AStar(root, Heuristic::zeroHeuristic));
        System.out.println(SearchAlgorithms.AStar(root, Heuristic::manhattenHeuristic));
        System.out.println(SearchAlgorithms.AStar(root, Heuristic::simpleBlockingHeuristic));
        System.out.println(SearchAlgorithms.AStar(root, Heuristic::advancedHeuristic));
    }
}