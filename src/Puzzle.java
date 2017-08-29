import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {

    private final static int ROW_COUNT = 6;
    private final static int COL_COUNT = 6;

    private Set<Car> cars = new LinkedHashSet<>();

    public Puzzle() {
        cars.add(new Car(new int[]{0, 2, 2, 2}));
        cars.add(new Car(new int[]{0, 0, 4, 3}));
        cars.add(new Car(new int[]{0, 3, 4, 2}));
        cars.add(new Car(new int[]{0, 4, 1, 2}));
        cars.add(new Car(new int[]{1, 2, 0, 2}));
        cars.add(new Car(new int[]{1, 4, 2, 2}));
        // generateMoves(new ArrayList<>(cars).get(3)).forEach(m -> System.out.println(Integer.toString(m.getDestinationX()) + "," + Integer.toString(m.getDestinationY())));
        getCoordinatesOccupiedByCar(new ArrayList<>(cars).get(5)).forEach(cord -> System.out.println(Integer.toString(cord[0]) + "," + Integer.toString(cord[1])));
    }

    private boolean isOpen(int x, int y) {
        return cars.stream()
                .map(Car::getCoordinatesOccupied)
                .flatMap(Collection::stream)
                .filter(cord -> cord[0] == x && cord[1] == y)
                .collect(Collectors.toSet()).isEmpty();
    }

    private Set<int[]> getCoordinatesOccupiedByCar(Car car) {
        Set<int[]> occupied = new HashSet<>();
        occupied.add(new int[]{car.getX(), car.getY()});
        for (int i = 1; i < car.getSize(); i++ ){
            if (car.isVertical()) {
                occupied.add(new int[]{car.getX(), car.getY() + i});
            } else {
                occupied.add(new int[]{car.getX() + i, car.getY()});
            }
        }
        return occupied;
    }

    private Optional<Car> getCarAt(int x, int y) {
        return cars.stream().filter(car -> car.getCoordinatesOccupied().contains(new int[]{x,y})).findFirst();
    }

    public boolean isLegalMove(Move move) {
        if (!isOpen(move.getDestinationX(), move.getDestinationY())){
        }
        if (move.getCar().isVertical()) {
            if (move.getDestinationX() != move.getCar().getX()) return false;
            if (Math.abs(move.getDestinationY() - move.getCar().getY()) > 1) return false;
        } else {
            if (move.getDestinationY() != move.getCar().getY()) return false;
            if (Math.abs(move.getDestinationX() - move.getCar().getX()) > 1) return false;
        }
        Car newCar = new Car(new int[]{move.getCar().isVertical() ? 1 : 0, move.getDestinationX(), move.getDestinationY(), move.getCar().getSize()});
        return newCar.getCoordinatesOccupied().stream()
                .filter(cord -> cord[0] > ROW_COUNT || cord[0] < 0 || cord[1] > COL_COUNT || cord[1] < 0)
                .collect(Collectors.toSet()).isEmpty();
    }

    private Set<Move> generateMoves(Car car) {
        if (!cars.contains(car)) throw new IllegalArgumentException("Car do not exist");
        Set<Move> moves = new HashSet<>();
        Move m1 = new Move(car, car.getX(), car.getY() + 1);
        Move m2 = new Move(car, car.getX(), car.getY() - 1);
        Move m3 = new Move(car, car.getX() + 1, car.getY());
        Move m4 = new Move(car, car.getX() - 1, car.getY());
        if (isLegalMove(m1)) moves.add(m1);
        if (isLegalMove(m2)) moves.add(m2);
        if (isLegalMove(m3)) moves.add(m3);
        if (isLegalMove(m4)) moves.add(m4);
        return moves;
    }

    public void print() {
        String[][] board = new String[ROW_COUNT][COL_COUNT];
        for (String[] row : board) {
            Arrays.fill(row, "*");
        }
        int counter = 0;
        for (Car car : this.cars) {
            for (int[] cord: car.getCoordinatesOccupied()) {
                int x = cord[0];
                int y = cord[1];
                board[y][x] = Integer.toString(counter);
            }
            counter++;
        }
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

}
