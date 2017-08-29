public class Move {
    private Car car;
    private int destinationX;
    private int destinationY;

    public Move(Car car, int destinationX, int destinationY) {
        this.car = car;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(int destinationX) {
        this.destinationX = destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(int destinationY) {
        this.destinationY = destinationY;
    }
}
