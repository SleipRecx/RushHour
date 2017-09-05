package RushHour;

public class Move {
    private RushHour currentState;
    private Car car;
    private int destX;
    private int destY;

    public Move(Car car, int destX, int destY, RushHour state) {
        this.car = car;
        this.destX = destX;
        this.destY = destY;
        this.currentState = state;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getDestX() {
        return destX;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    public int getDestY() {
        return destY;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    public boolean isLegal() {
        if (car.isVertical()) {
            if (destX != car.getX()) return false;
            if (Math.abs(destY - car.getY()) > 1) return false;
        } else {
            if (destY != car.getY()) return false;
            if (Math.abs(destX - car.getX()) > 1) return false;
        }

        Car newCar = new Car(car.getQuadFormat());
        newCar.setX(destX);
        newCar.setY(destY);
        for (int[] cord: newCar.getCoordinatesOccupied()) {
            if (cord[0] < 0 || cord[0] >= RushHour.ROW_COUNT) return false;
            if (cord[1] < 0 || cord[1] >= RushHour.COL_COUNT) return false;
            if (!currentState.isOpen(cord[0], cord[1])) {
                if (currentState.getCarAt(cord[0], cord[1]).isPresent()) {
                    if (!currentState.getCarAt(cord[0],cord[1]).get().equals(car)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
