import Search.SearchAlgorithms;
import Search.SearchNode;
import RushHour.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Car> cars = new LinkedHashSet<>();
        cars.add(new Car(new int[]{0, 2, 2, 2}));
        cars.add(new Car(new int[]{0, 0, 4, 3}));
        cars.add(new Car(new int[]{0, 3, 4, 2}));
        cars.add(new Car(new int[]{0, 4, 1, 2}));
        cars.add(new Car(new int[]{1, 2, 0, 2}));
        cars.add(new Car(new int[]{1, 4, 2, 2}));
        SearchNode rushHour = new RushHour(cars);

        //SearchAlgorithms.breadthFirstSearch(rushHour).forEach(SearchNode::print);
        SearchAlgorithms.depthFirstSearch(rushHour).forEach(SearchNode::print);
        //SearchAlgorithms.AStar(rushHour).forEach(SearchNode::print);
    }
}
