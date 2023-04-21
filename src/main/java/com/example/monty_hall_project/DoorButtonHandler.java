package com.example.monty_hall_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DoorButtonHandler implements EventHandler<ActionEvent> {

    private final Main main;
    private final int doorNumber;

    DoorButtonHandler(Main main, int doorNumber)
    {
        this.main = main;
        this.doorNumber = doorNumber;
    }

    @Override
    public void handle(ActionEvent event) {
        if (main.getSelectedDoor() == -1) {
            main.setSelectedDoor(doorNumber);
            System.out.println("Selected Door: " + main.getSelectedDoor());
            main.revealNonPrizeDoor();
        } else {
            main.setSelectedDoor(doorNumber);
            System.out.println("Selected Door: " + main.getSelectedDoor());
            main.revealPrizeDoor();
            main.allowRestart(true);
        }
    }
}