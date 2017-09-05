package RushHour;

import Search.SearchProblem;

import java.util.*;
import java.util.stream.Collectors;

public class RushHour implements SearchProblem {

    public final static int ROW_COUNT = 6;
    public final static int COL_COUNT = 6;

    private Set<Car> cars = new LinkedHashSet<>();

    public RushHour() {}

    public RushHour(Set<Car> cars) {
        this.cars = cars;
    }

    public boolean isOpen(int x, int y) {
        return cars.stream()
                .map(Car::getCoordinatesOccupied)
                .flatMap(Collection::stream)
                .filter(cord -> cord[0] == x && cord[1] == y)
                .collect(Collectors.toSet()).isEmpty();
    }

    public int blocking() {
        return cars.stream().filter(car -> (car.getX() == 2 && car.getY() >= getCarZero().getY())).collect(Collectors.toList()).size();
    }

    public int distanceToGoal() {
        return COL_COUNT - getCarZero().getX()+1;
    }

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

    public Car getCarZero() {
        return cars.iterator().next();
    }

    public Boolean isSolution() {
        return getCarAt(5, 2).isPresent() && getCarZero().equals(getCarAt(5, 2).get());
    }

    @Override
    public Double h() {
        return (double) (blocking() + distanceToGoal());
    }

    @Override
    public Double g() {
        return 0.0;
    }

    public void addCar(Car c) {
        cars.add(c);
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void print() {
        String[][] board = new String[ROW_COUNT][COL_COUNT];
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
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public Set<SearchProblem> generateSuccessors(){
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
        Set<SearchProblem> successors = new HashSet<>();
        moves.forEach(move -> successors.add(performMove(move)));
        return successors;
    }

    public RushHour performMove(Move move) {
        RushHour newState = cloneBoard();
        newState.cars.forEach(car -> {
            if (car.getX() == move.getCar().getX() && car.getY() == move.getCar().getY()) {
                car.setX(move.getDestX());
                car.setY(move.getDestY());
            }
        });
        return newState;
    }

    public RushHour cloneBoard() {
        RushHour newState = new RushHour();
        cars.forEach(car -> {
            Car newCar = new Car(car.getQuadFormat());
            newState.getCars().add(newCar);
        });
        return newState;
    }

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
