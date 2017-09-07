package Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Maze {
    List<List<String>> grid = new ArrayList<>();


    public Maze(String filename) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                grid.add(new ArrayList<>());
                for (int i = 0, n = line.length(); i < n; i++) {
                    grid.get(grid.size() - 1).add(("" + line.charAt(i)));
                }
            });
        }
    }

    public void print() {
        grid.forEach(line -> {
            System.out.println();
            line.forEach(s -> System.out.print(s + " "));
        });
    }
}
