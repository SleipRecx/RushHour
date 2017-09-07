package RushHour;


import Search.SearchAlgorithms;
import Search.SearchNode;
import Search.SearchResult;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Fx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("RushHour");
        Button btn = new Button();
        Label label = new Label();

        btn.setText("Pimp");
        btn.setOnAction(event -> {
            try {
                SearchNode rushhour = new RushHour("rush_hour_problems/hard.txt");
                SearchResult result = SearchAlgorithms.AStar(rushhour);
                label.setText(result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(label);
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
}
