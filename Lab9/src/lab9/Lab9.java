/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author CCannon
 */
public class Lab9 extends Application {

    private Button[][] buttons;
    private int n;

    @Override
    public void start(Stage primaryStage) {
        n = 0;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Maze Configuration");
        dialog.setHeaderText("Each maze is an n x n square");
        dialog.setContentText("Please enter the dimension for n:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            n = Integer.parseInt(result.get());
        }

        GridPane grid = new GridPane();
        buttons = new Button[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button newButt = new Button();
                newButt.setStyle("-fx-background-color: blue");
                newButt.setPrefSize(50, 50);
                newButt.setOnAction(new MyHandler());

                grid.add(newButt, j, i);
                buttons[i][j] = newButt;
            }
        }

        HBox commandPane = new HBox();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(new MyHandler());
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(new MyHandler());
        commandPane.getChildren().addAll(saveButton, exitButton);
        commandPane.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

        root.setCenter(grid);
        root.setBottom(commandPane);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //Returns the Maze character associated with the given button   
    private char getColorChar(Button b) {
        if (b.getStyle().indexOf("blue") > -1) {
            return '0';
        } else if (b.getStyle().indexOf("white") > -1) {
            return '1';
        } else if (b.getStyle().indexOf("green") > -1) {
            return 'S';
        } else {
            return 'E';
        }
    }

    //Inner class lister for buttons
    public class MyHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            //e.getSource() returns the button that was clicked.
            Button b = (Button) e.getSource();

            if (b.getText().equals("Save")) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Save Maze");
                dialog.setHeaderText("Your maze will be saved in a character configuration file");
                dialog.setContentText("Please enter a save file name:");
                
                Optional<String> result = dialog.showAndWait();
                if(result.isPresent()) {
                    try {
                        PrintWriter writer = new PrintWriter(result.get());
                        writer.println(n + " " + n);
                        for(int i = 0; i < n; i++) {
                            for(int j = 0; j < n; j++) {
                                writer.print(getColorChar(buttons[i][j]));
                            }
                            writer.println();
                        }
                        writer.close();
                    } catch (FileNotFoundException ex) {
                        System.err.println("Could not open " + result.get() + " for reading");
                        Logger.getLogger(Lab9.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else if (b.getText().equals("Exit")) {
                //exit the application

            } else { //a maze button was clicked

                char val = getColorChar(b);
                if (val == '0') {  //the button is currently blue so change it to white
                    b.setStyle("-fx-background-color: white;");
                } else if (val == '1') {

                } else if (val == 'S') {

                } else {

                }

            }
        }
    }
}
