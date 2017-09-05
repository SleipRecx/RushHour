package Search;
import java.util.*;

public class SearchAlgorithms {

    public static List<SearchNode> breadthFirstSearch(SearchNode root) {
        Map<SearchNode, SearchNode> cameFrom = new HashMap<>();
        Set<SearchNode> visited = new HashSet<>();
        List<SearchNode> queue = new ArrayList<>();
        queue.add(root);
        visited.add(root);
        while (queue.size() > 0) {
            SearchNode current = queue.remove(0);
            if (current.isSolution()) {
                return reconstructPath(cameFrom, current);
            }
            for (SearchNode successor: current.generateSuccessors()) {
                if (!visited.contains(successor)) {
                    visited.add(successor);
                    queue.add(successor);
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
            current.print();
            if (current.isSolution()) {
                System.out.println("yeeah");
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


    public static List<SearchNode> reconstructPath(Map<SearchNode, SearchNode> cameFrom, SearchNode goalState) {
        List<SearchNode> path = new ArrayList<>();
        SearchNode current = goalState;
        while(cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }


    public static List<SearchNode> AStar(SearchNode start) {

        Set closedSet = new LinkedHashSet();
        Queue<SearchNode> openSet = new LinkedList<>();
        Map<SearchNode, SearchNode> cameFrom = new HashMap<>();
        Map<SearchNode, Double> gScore = new HashMap<>();
        Map<SearchNode, Double> fScore = new HashMap<>();

        openSet.add(start);
        gScore.put(start, 0.0);
        fScore.put(start, start.h());

        int moves = 0;
        while(!openSet.isEmpty()) {

            // get the node from openSet with lowest fScore. TODO: refactor
            SearchNode current = openSet.peek();
            double value = fScore.get(current);
            for(SearchNode s: openSet) {
                if (fScore.get(s) < value) {
                    current = s;
                }
            }


            if(!gScore.containsKey(current)) {
                gScore.put(current, (double) moves);
            }

            if(current.isSolution()) {
                System.out.println("Found solution! (in "+ moves +" moves)");
                System.out.println(moves);
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (SearchNode successor: current.generateSuccessors()) {
                if (closedSet.contains(successor)) {
                    continue;
                }
                if (!openSet.contains(successor)) {
                    openSet.add(successor);
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
