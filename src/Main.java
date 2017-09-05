import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        Puzzle p = new Puzzle();

        Set<State> ss = new HashSet<>();
        ss.add(puzzle.getBoard());

        if(ss.contains(p.getBoard())) {
            System.out.println("contains");
        }
        else {
            System.out.println("not contains");
        }

        if(p.equals(puzzle)) {
            System.out.println("equals good");
        }

        System.out.println("Blocking: " + p.getBoard().blocking());

        Map<String, Double> fScore = new HashMap<>();
        fScore.put("test1", 2.0);
        fScore.put("test2", 2.0);
        fScore.put("test3", 2.0);
        System.out.println(fScore.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey());

        puzzle.AStar(puzzle.getBoard());
//        puzzle.bestFirst(puzzle.getBoard());


    }
}
