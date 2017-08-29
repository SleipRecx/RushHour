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
}
