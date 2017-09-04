import java.util.*;

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
