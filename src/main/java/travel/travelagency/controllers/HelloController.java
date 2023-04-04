package travel.travelagency.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private String text = "Hello";

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(text);
    }



}