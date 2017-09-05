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

    public static List<SearchProblem> dfs(SearchProblem root) {
        Stack<SearchProblem> stack = new Stack<>();
        List<SearchProblem> discovered = new ArrayList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            SearchProblem v = stack.pop();

            if (!discovered.contains(v)) {
                discovered.add(v);
                for (SearchProblem sp: v.generateSuccessors()) {
                    stack.push(sp);
                }
            }

            if(v.isSolution()) {
                System.out.println("sol");
                return discovered;
            }

        }
        return null;
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


    public static List<SearchProblem> AStar(SearchProblem start) {

        Set closedSet = new LinkedHashSet();
        Queue<SearchProblem> openSet = new LinkedList<>();
        Map<SearchProblem, SearchProblem> cameFrom = new HashMap<>();
        Map<SearchProblem, Double> gScore = new HashMap<>();
        Map<SearchProblem, Double> fScore = new HashMap<>();

        openSet.add(start);
        gScore.put(start, 0.0);
        fScore.put(start, start.h());

        int moves = 0;
        while(!openSet.isEmpty()) {

            // get the node from openSet with lowest fScore. TODO: refactor
            SearchProblem current = openSet.peek();
            double value = fScore.get(current);
            for(SearchProblem s: openSet) {
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

            for (SearchProblem successor: current.generateSuccessors()) {
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
