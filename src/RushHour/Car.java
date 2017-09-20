package RushHour;

import java.util.HashSet;
import java.util.Set;

public class Car {
    private boolean vertical;
    private int x;
    private int y;
    private int size;

    public Car(int[] quad) {
        this.vertical = quad[0] == 1;
        this.x = quad[1];
        this.y = quad[2];
        this.size = quad[3];
    }

    // Returns the coordinates currently occupied by this car
    public Set<int[]> getCoordinatesOccupied() {
        Set<int[]> occupied = new HashSet<>();
        occupied.add(new int[]{this.x, this.y});
        for (int i = 1; i < this.size; i++ ){
            if (this.vertical) {
                occupied.add(new int[]{this.x, this.y + i});
            } else {
                occupied.add(new int[]{this.x + i, this.y});
            }
        }
        return occupied;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Returns a car's position in the "native" format (used to make new copies)
    public int[] getQuadFormat() {
        return new int[]{this.vertical ? 1 : 0, x, y, size};
    }

    /*
    Since we make copies of the board when a move is made, new "equal" states get new references.
    We consider states where two boards look the same to be equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (vertical != car.vertical) return false;
        if (x != car.x) return false;
        if (y != car.y) return false;
        return size == car.size;

    }

    @Override
    public int hashCode() {
        int result = (vertical ? 1 : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "RushHour.Car{" +
                "vertical=" + vertical +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}
