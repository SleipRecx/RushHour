import java.util.*;
import java.util.function.DoubleBinaryOperator;

public class Puzzle {

    private State board = new State();

    public Puzzle() {
        this.board.addCar(new Car(new int[]{0, 2, 2, 2}));
        this.board.addCar(new Car(new int[]{0, 0, 4, 3}));
        this.board.addCar(new Car(new int[]{0, 3, 4, 2}));
        this.board.addCar(new Car(new int[]{0, 4, 1, 2}));
        this.board.addCar(new Car(new int[]{1, 2, 0, 2}));
        this.board.addCar(new Car(new int[]{1, 4, 2, 2}));
    }

    public State getBoard() {
        return board;
    }

    public double h() {
        return 1;
    }

    /*public State getLowestFscore(Map<State, Double> fScore, ) {
        State lowest = fScore.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
        for()
    }*/

    public State AStar(State start) {

        Set closedSet = new LinkedHashSet();

        Queue<State> openSet = new LinkedList<>();
        openSet.add(start);

        Map<State, State> cameFrom = new HashMap<>();
        Map<State, Double> gScore = new HashMap<>();
        Map<State, Double> fScore = new HashMap<>();


        gScore.put(start, 0.0);
        fScore.put(start, h());


        int moves = 0;
        while(!openSet.isEmpty()) {
            moves++;

            // get the node from openSet with lowest fScore. TODO: refactor
            State current = openSet.peek();
            double value = fScore.get(current);
            for(State s: openSet) {
                if (fScore.get(s) < value) {
                    current = s;
                }
            }
            current.print();

            if(!gScore.containsKey(current)) {
                gScore.put(current, (double) moves);
            }

            if(current.isSolution()) {
                System.out.println("Found solution! (in "+ moves +" moves)");
                System.out.println(cameFrom.size());
                current.print();
                return current;
            }

            openSet.remove(current);
            closedSet.add(current);

            for (State successor: current.generateSuccessors()) {
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
                    continue;
                }

                cameFrom.put(successor, current);
                gScore.put(successor, tentativeGScore);
                fScore.put(successor, gScore.get(successor) + h());


            }


        }
        return null;

    }

    public State bestFirst(State initial) {
        Set<State> closed = new HashSet<>();
        Queue<State> open = new PriorityQueue<>();
        open.add(initial);
        while(!open.isEmpty()) {
            System.out.println(closed.size());
            State current = open.poll();
            if (!closed.contains(current)){
                closed.add(current);
                if (current.isSolution()) return current;
                for (State successor: current.generateSuccessors()) {
                    if (closed.contains(successor)) continue;
                    if (!open.contains(successor)) open.add(successor);
                }
            }
        }
        return null;
    }


}
