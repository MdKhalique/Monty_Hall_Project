package com.example.monty_hall_project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Bryan Lor and Md Khalique
public class Main extends Application implements IMontyGame {

    private final int NUM_DOORS = 3;

    private int prizeDoor;
    private int selectedDoor;

    private Button[] doorButtons;
    private Label messageLabel;
    private Button restartGame;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        doorButtons = new Button[NUM_DOORS];
        for (int i = 0; i < NUM_DOORS; i++) {
            Button button = new Button();
            button.setOnAction(new DoorButtonHandler(this, i));
            doorButtons[i] = button;
        }

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(doorButtons);

        messageLabel = new Label("Choose a door");
        messageLabel.setAlignment(Pos.CENTER);

        restartGame = new Button("Restart Game");
        restartGame.setOnAction(e -> startNewGame());

        root.getChildren().addAll(buttonBox, messageLabel, restartGame);

        stage.setTitle("Monty Hall Game");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        startNewGame();
    }


     public void startNewGame() {
        prizeDoor = (int) (Math.random()*3);
        System.out.println("Prize Door: " + prizeDoor);
        selectedDoor = -1;

        for (Button button : doorButtons) {
            ImageView imgDoor = new ImageView( new Image("File:src/main/resources/Images/Door.jpg"));
            button.setGraphic(imgDoor);
            button.setDisable(false);
        }

        allowRestart(false);

        messageLabel.setText("Choose a door");
    }

    public void revealNonPrizeDoor() {
        int doorToReveal;
        do {
            doorToReveal = (int) (Math.random()*3);
        } while (doorToReveal == prizeDoor || doorToReveal == selectedDoor);

        System.out.println("Door To Reveal: " + doorToReveal);

        ImageView imgGoat = new ImageView( new Image("File:src/main/resources/Images/Goat.jpg"));
        doorButtons[doorToReveal].setGraphic(imgGoat);
        doorButtons[doorToReveal].setDisable(true);

        messageLabel.setText("Do you want to switch?");
    }

    public void revealPrizeDoor() {
        for (int i = 0; i < NUM_DOORS; i++) {
            if (i != prizeDoor)
            {
                ImageView imgGoat = new ImageView( new Image("File:src/main/resources/Images/Goat.jpg"));
                doorButtons[i].setGraphic(imgGoat);
                doorButtons[i].setDisable(true);
            }
        }

        ImageView imgCar = new ImageView( new Image("File:src/main/resources/Images/Bugatti.jpg"));
        if (selectedDoor == prizeDoor) {
            doorButtons[selectedDoor].setGraphic(imgCar);
            messageLabel.setText("You win a Bugatti!");
        }
        else {
            doorButtons[prizeDoor].setGraphic(imgCar);
            doorButtons[prizeDoor].setDisable(true);
            doorButtons[selectedDoor].setDisable(false);
            messageLabel.setText("Sorry, you lose.");
        }
    }

    public int getSelectedDoor() {
        return selectedDoor;
    }

    public void setSelectedDoor(int selectedDoor) {
        this.selectedDoor = selectedDoor;
    }

    public void allowRestart(boolean value) {
        restartGame.setDisable(!value);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
