package RushHour;

import Helpers.ConsoleColors;
import Search.SearchNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    This class is the RushHour specific implementation used with A*.
    It implements the SearchNode interfaces so the a*-algorithm can expect certain functions to be present.
    Implemented methods:
        Set<SearchNode> generateSuccessors();
        Boolean isSolution();
        SearchNode getParent();
        void setParent(SearchNode parent);
        Double arcCost(SearchNode node);
        void setG(Double g);
        Double getG();
        Double getF();
        void printState();

 */

public class RushHour implements SearchNode {

    final static int ROW_COUNT = 6;
    final static int COL_COUNT = 6;

    private Double g = 0.0;

    private SearchNode parent;

    private Set<Car> cars = new LinkedHashSet<>();

    public RushHour() {}

    public RushHour(String filename) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                int[] quad = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                cars.add(new Car(quad));
            });
        }
    }

    @Override
    /*
    Returns a set of possible successor states from the current state.
    In the case of rushour; new possible configurations of the board
    i.e  a set of states where each car that can be moved from the current position is moved (one move per new state)
     */
    public Set<SearchNode> generateSuccessors(){
        Set<Move> moves = new HashSet<>();
        getCars().forEach(car -> {
            Move m1 = new Move(car, car.getX(), car.getY() + 1, this);
            Move m2 = new Move(car, car.getX(), car.getY() - 1, this);
            Move m3 = new Move(car, car.getX() + 1, car.getY(), this);
            Move m4 = new Move(car, car.getX() - 1, car.getY(), this);
            if (m1.isLegal()) moves.add(m1);
            if (m2.isLegal()) moves.add(m2);
            if (m3.isLegal()) moves.add(m3);
            if (m4.isLegal()) moves.add(m4);
        });
        Set<SearchNode> successors = new HashSet<>();
        moves.forEach(move -> successors.add(performMove(move)));
        return successors;
    }

    @Override
    public SearchNode getParent() {
        return parent;
    }

    @Override
    // The cost of getting from one state(one search node to another) to another is always 1 for rushour.
    public Double arcCost(SearchNode node) {
        return 1.0;
    }

    @Override
    public void setParent(SearchNode parent) {
        this.parent = parent;
    }

    @Override
    // The accumulated arc-cost of getting this state (+1 for each new state)
    public void setG(Double g) {
        this.g = g;
    }

    @Override
    public Double getF() {
        return this.g + (double) distanceToGoal() + getBlockingCars();
    }

    @Override
    public Double getG() {
        return this.g;
    }

    @Override
    // A solution is found if car0 touches position 5,2
    public Boolean isSolution() {
        return getCarZero().getCoordinatesOccupied().stream()
                .filter(cord -> cord[0] == 5 && cord[1] == 2).collect(Collectors.toSet()).size() > 0;
    }

    // Checks if a position is currently occupied by a car
    public boolean isOpen(int x, int y) {
        return cars.stream()
                .map(Car::getCoordinatesOccupied)
                .flatMap(Collection::stream)
                .filter(cord -> cord[0] == x && cord[1] == y)
                .collect(Collectors.toSet()).isEmpty();
    }

    // Returns how many cars are directly blocking car0 from the exit. Used as one heuristic function
    public int getBlockingCars() {
        List<Car> blockingCars = cars.stream().filter(car -> !car.equals(getCarZero())).filter(car -> {
            for (int[] cord: car.getCoordinatesOccupied()) {
                if (cord[1] == 2 && cord[0] > getCarZero().getX()) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return blockingCars.size();
    }

    // How many moves must minimally be made to reach the goal (does not consider blocking cars)
    public int distanceToGoal() {
        return COL_COUNT - getCarZero().getX()+1;
    }

    // Return the car that occupies position (x, y)
    public Optional<Car> getCarAt(int x, int y) {
        for (Car car: cars) {
            for (int[] cord: car.getCoordinatesOccupied()) {
                if (cord[0] == x && cord[1] == y) {
                    return Optional.of(car);
                }
            }
        }
        return Optional.empty();
    }

    // The red car is always index 0
    private Car getCarZero() {
        return cars.iterator().next();
    }

    private Set<Car> getCars() {
        return cars;
    }

    // Used to show the state of the board at any given state
    public void printState() {
        String[][] board = new String[ROW_COUNT][COL_COUNT];
        List<String> colors = new ArrayList<>();
        colors.add(ConsoleColors.RED_BACKGROUND_BRIGHT);
        colors.add(ConsoleColors.CYAN_BACKGROUND);
        colors.add(ConsoleColors.BLUE_BACKGROUND);
        colors.add(ConsoleColors.GREEN_BACKGROUND);
        colors.add(ConsoleColors.PURPLE_BACKGROUND);
        colors.add(ConsoleColors.RED_BACKGROUND);
        colors.add(ConsoleColors.YELLOW_BACKGROUND);
        colors.add(ConsoleColors.CYAN_BACKGROUND_BRIGHT);
        colors.add(ConsoleColors.BLUE_BACKGROUND_BRIGHT);
        colors.add(ConsoleColors.GREEN_BACKGROUND_BRIGHT);
        colors.add(ConsoleColors.PURPLE_BACKGROUND_BRIGHT);
        colors.add(ConsoleColors.YELLOW_BACKGROUND_BRIGHT);
        colors.add(ConsoleColors.WHITE_BACKGROUND_BRIGHT);

        for (String[] row : board) {
            Arrays.fill(row, "*");
        }
        int counter = 0;
        for (Car car : cars) {
            for (int[] cord: car.getCoordinatesOccupied()) {
                int x = cord[0];
                int y = cord[1];
                board[y][x] = Integer.toString(counter);
            }
            counter++;
        }
        for (String[] row : board) {
            for (String digit: row) {
                if (digit.equals("*")) {
                    System.out.print(ConsoleColors.BLACK_BACKGROUND_BRIGHT + "    " + ConsoleColors.RESET);
                } else {
                    if (digit.equals("0")) {
                        System.out.print(ConsoleColors.RED_BACKGROUND_BRIGHT + " -> " + ConsoleColors.RESET);
                    } else {
                        System.out.print(colors.get(Integer.parseInt(digit)) + "    " + ConsoleColors.RESET);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Makes alteration to a cloned board. The result is the new state.
    private RushHour performMove(Move move) {
        RushHour newState = cloneBoard();
        newState.cars.forEach(car -> {
            if (car.getX() == move.getCar().getX() && car.getY() == move.getCar().getY()) {
                car.setX(move.getDestX());
                car.setY(move.getDestY());
            }
        });
        return newState;
    }

    // When making changes to the board-configuration we make copies of the old one,
    // so we have no side effects from altering values
    private RushHour cloneBoard() {
        RushHour newState = new RushHour();
        cars.forEach(car -> {
            Car newCar = new Car(car.getQuadFormat());
            newState.getCars().add(newCar);
        });
        return newState;
    }

    /*
    Since we make copies of the board when a move is made, new "equal" states get new references.
    We consider states where two boards look the same to be equal.
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof RushHour){
            RushHour toCompare = (RushHour) o;
            return this.toString().equals(toCompare.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return cars != null ? cars.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RushHour{" +
                "cars=" + cars +
                '}';
    }
}
