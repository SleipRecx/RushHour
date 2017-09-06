package Search;
import java.util.*;

public class SearchAlgorithms {

    public static SearchResult breadthFirstSearch(SearchNode root) {
        Map<SearchNode, SearchNode> cameFrom = new HashMap<>();
        Set<SearchNode> closed = new HashSet<>();
        List<SearchNode> open = new ArrayList<>();
        open.add(root);
        closed.add(root);
        while (open.size() > 0) {
            SearchNode current = open.remove(0);
            if (current.isSolution()) {
                List<SearchNode> path = reconstructPath(cameFrom, current);
                return new SearchResult(path, open.size(), closed.size(), "Breadth First Search");
            }
            for (SearchNode successor: current.generateSuccessors()) {
                if (!closed.contains(successor)) {
                    closed.add(successor);
                    open.add(successor);
                    cameFrom.put(successor, current);
                }
            }
        }
        throw new IllegalStateException("No solution found");
    }

    public static List<SearchNode> depthFirstSearch(SearchNode root) {
        Map<SearchNode, SearchNode> cameFrom = new HashMap<>();
        Set<SearchNode> closed = new HashSet<>();
        Stack<SearchNode> open = new Stack<>();
        open.push(root);
        while (open.size() > 0){
            SearchNode current = open.pop();
            if (current.isSolution()) {
                return reconstructPath(cameFrom, current);
            }
            if (!closed.contains(current)){
                closed.add(current);
                for (SearchNode successor: current.generateSuccessors()) {
                    open.push(successor);
                    cameFrom.put(successor, current);
                }
            }

        }
        throw new IllegalStateException("No solution found");
    }


    private static List<SearchNode> reconstructPath(Map<SearchNode, SearchNode> cameFrom, SearchNode goalState) {
        List<SearchNode> path = new ArrayList<>();
        SearchNode current = goalState;
        while(cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }


    public static SearchResult AStar(SearchNode start) {

        Set closed = new LinkedHashSet();
        Queue<SearchNode> open = new LinkedList<>();
        Map<SearchNode, SearchNode> cameFrom = new HashMap<>();
        Map<SearchNode, Double> gScore = new HashMap<>();
        Map<SearchNode, Double> fScore = new HashMap<>();

        open.add(start);
        gScore.put(start, 0.0);
        fScore.put(start, start.h());

        int moves = 0;
        while(!open.isEmpty()) {

            // get the node from openSet with lowest fScore. TODO: refactor
            SearchNode current = open.peek();
            double value = fScore.get(current);
            for(SearchNode s: open) {
                if (fScore.get(s) < value) {
                    current = s;
                }
            }


            if(!gScore.containsKey(current)) {
                gScore.put(current, (double) moves);
            }

            if(current.isSolution()) {
                List<SearchNode> path = reconstructPath(cameFrom, current);
                return new SearchResult(path, open.size(), closed.size(), "A Star");
            }

            open.remove(current);
            closed.add(current);

            for (SearchNode successor: current.generateSuccessors()) {
                if (closed.contains(successor)) {
                    continue;
                }
                if (!open.contains(successor)) {
                    open.add(successor);
                    fScore.put(successor, Double.POSITIVE_INFINITY);

                }

                // gScore[current] + distance from current to neighbor (always 1?)
                double tentativeGScore = gScore.get(current) + 1;
                if (tentativeGScore >= gScore.get(current)) {
                }

                cameFrom.put(successor, current);
                gScore.put(successor, tentativeGScore);
                fScore.put(successor, gScore.get(successor) + successor.h());

            }

            moves++;

        }
        return null;

    }


}
