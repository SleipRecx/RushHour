import Search.SearchAlgorithms;
import Search.SearchProblem;
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
        SearchProblem rushHour = new RushHour(cars);

        //SearchAlgorithms.bfs(rushHour).forEach(SearchProblem::print);
        //SearchAlgorithms.dfs(rushHour).forEach(SearchProblem::print);
        SearchAlgorithms.AStar(rushHour).forEach(SearchProblem::print);
    }
}
