package Search;
import java.util.*;
import java.util.function.Function;

/*
General purpose search algorithms (dfs, bfs and a*)
 */

public class SearchAlgorithms {

    public static SearchResult breadthFirstSearch(SearchNode root) {
        long startTime = System.currentTimeMillis();
        Set<SearchNode> closed = new HashSet<>();
        List<SearchNode> open = new ArrayList<>();
        open.add(root);
        closed.add(root);
        while (open.size() > 0) {
            SearchNode current = open.remove(0);
            if (current.isSolution()) {
                long timeElapsed = System.currentTimeMillis() - startTime;
                return new SearchResult(current, closed.size() + open.size(), timeElapsed, "Breadth First");
            }
            for (SearchNode successor: current.generateSuccessors()) {
                if (!closed.contains(successor)) {
                    closed.add(successor);
                    open.add(successor);
                    successor.setParent(current);
                }
            }
        }
        throw new IllegalStateException("No solution found");
    }

    public static SearchResult depthFirstSearch(SearchNode root) {
        long startTime = System.currentTimeMillis();
        Set<SearchNode> closed = new HashSet<>();
        Stack<SearchNode> open = new Stack<>();
        open.push(root);
        while (open.size() > 0){
            SearchNode current = open.pop();
            if (current.isSolution()) {
                long timeElapsed = System.currentTimeMillis() - startTime;
                return new SearchResult(current, open.size() + closed.size(), timeElapsed, "Depth First");
            }
            if (!closed.contains(current)){
                closed.add(current);
                for (SearchNode successor: current.generateSuccessors()) {
                    open.push(successor);
                    successor.setParent(current);
                }
            }

        }
        throw new IllegalStateException("No solution found");
    }

    /**
     *
     * @param root the initial search state
     * @param heuristic a function that returns the estimated cost of reaching a state
     * @param debug set true if debug information is needed
     * @return SearchResult or throws an IllegalStateException if no solution is found
     */
    public static SearchResult AStar(SearchNode root, Function<SearchNode, Double> heuristic, boolean debug) {
        // used to track time elapsed
        long startTime = System.currentTimeMillis();

        Set<SearchNode> closed = new HashSet<>();
        // We use a priority queue sorted incrementally on the node with lowest f-score. this makes it simple to pop the correct node
        Queue<SearchNode> open = new PriorityQueue<>((o1, o2) ->  o1.getG() + heuristic.apply(o1) < o2.getG() + heuristic.apply(o2) ? -1: 1);
        open.add(root);

        while (open.size() > 0){
            SearchNode current = open.poll();
            if (debug) current.printState();
            closed.add(current);
            if (current.isSolution()) {
                return new SearchResult(current, open.size() + closed.size(), System.currentTimeMillis() - startTime, "A star");
            }
            for (SearchNode successor: current.generateSuccessors()) {
                if ((!open.contains(successor)) && (!closed.contains(successor))) {
                    // Successor is never explored or visited before.
                    successor.setParent(current);
                    successor.setG(current.getG() + current.arcCost(successor));
                    open.add(successor);
                }
                else if (current.getG() + current.arcCost(successor) < successor.getG()) {
                    // We found a cheaper path to successor.
                    successor.setParent(current);
                    successor.setG(current.getG() + current.arcCost(successor));
                    if (closed.contains(successor)) {
                        propagatePathImprovements(successor);
                    }
                }
            }
        }
        throw new IllegalStateException("No solution found");
    }

    private static void propagatePathImprovements(SearchNode current) {
        for (SearchNode successor: current.generateSuccessors()) {
            if (current.getG() + current.arcCost(successor) < successor.getG()) {
                successor.setParent(current);
                successor.setG(current.getG() + current.arcCost(successor));
                propagatePathImprovements(successor);
            }
        }
    }


}
