package Search;
import java.util.*;

public class SearchAlgorithms {

    public static List<SearchProblem> bfs(SearchProblem root) {
        Map<SearchProblem, SearchProblem> cameFrom = new HashMap<>();
        Set<SearchProblem> visited = new HashSet<>();
        List<SearchProblem> queue = new ArrayList<>();
        queue.add(root);
        visited.add(root);
        while (queue.size() > 0) {
            SearchProblem current = queue.remove(0);
            if (current.isSolution()) {
                return reconstructPath(cameFrom, current);
            }
            for (SearchProblem successor: current.generateSuccessors()) {
                if (!visited.contains(successor)) {
                    visited.add(successor);
                    queue.add(successor);
                    cameFrom.put(successor, current);
                }
            }
        }
        throw new IllegalStateException("No solution found");
    }


    public static List<SearchProblem> reconstructPath(Map<SearchProblem, SearchProblem> cameFrom, SearchProblem goalState) {
        List<SearchProblem> path = new ArrayList<>();
        SearchProblem current = goalState;
        while(cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    /*
    public RushHour AStar(RushHour start) {

        Set closedSet = new LinkedHashSet();

        Queue<RushHour> openSet = new LinkedList<>();
        openSet.add(start);

        Map<RushHour, RushHour> cameFrom = new HashMap<>();
        Map<RushHour, Double> gScore = new HashMap<>();
        Map<RushHour, Double> fScore = new HashMap<>();


        gScore.put(start, 0.0);
        fScore.put(start, h(start));


        int moves = 0;
        while(!openSet.isEmpty()) {
            moves++;

            // get the node from openSet with lowest fScore. TODO: refactor
            RushHour current = openSet.peek();
            double value = fScore.get(current);
            for(RushHour s: openSet) {
                if (fScore.get(s) < value) {
                    current = s;
                }
            }
            //current.print();

            if(!gScore.containsKey(current)) {
                gScore.put(current, (double) moves);
            }

            if(current.isSolution()) {
                System.out.println("Found solution! (in "+ moves +" moves)");
                reconstructPath(cameFrom, current).forEach(RushHour::print);
                return current;
            }

            openSet.remove(current);
            closedSet.add(current);

            for (RushHour successor: current.generateSuccessors()) {
                successor.print();
                if (closedSet.contains(successor)) {
                    continue;
                }
                if (!openSet.contains(successor)) {
                    openSet.add(successor);
                    fScore.put(successor, Double.POSITIVE_INFINITY);
                }

                // gScore[current] + distance from current to neighbor (always 1?)
                double tentativeGScore = gScore.get(current) + 1;
                System.out.println(tentativeGScore);
                if (tentativeGScore >= gScore.get(current)) {
                }

                cameFrom.put(successor, current);
                gScore.put(successor, tentativeGScore);
                fScore.put(successor, gScore.get(successor) + h(successor));

            }

        }
        return null;

    }
    */

}
