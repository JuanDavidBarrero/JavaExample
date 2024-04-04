package com.juanda.frame.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ButtonController {

    @FXML
    private TextField input;

    @FXML
    private Label name;

    @FXML
    void sayhello(ActionEvent event) {

        String input_name = input.getText();
        name.setText("Hola " + input_name);

    }

}
