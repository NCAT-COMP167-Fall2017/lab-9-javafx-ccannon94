/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author CCannon
 */
public class Lab9 extends Application {
    Button[][] buttons;
    
    @Override
    public void start(Stage primaryStage) {
        int n = 0;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Maze Configuration");
        dialog.setHeaderText("Each maze is an n x n square");
        dialog.setContentText("Please enter the dimension for n:");
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            n = Integer.parseInt(result.get());
        }
        
        GridPane grid = new GridPane();
        buttons = new Button[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Button newButt = new Button();
                newButt.setStyle("-fx-background-color: blue");
                newButt.setPrefSize(50, 50);
                
                grid.add(newButt, j, i);
                buttons[i][j] = newButt;
            }
        }
        
        BorderPane root = new BorderPane();
        
        root.setCenter(grid);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
