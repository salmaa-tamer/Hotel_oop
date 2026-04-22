package GUI.Controllers;

import GuestandRoomSystem.*;
import StaffSystem.Receptionist;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptionistDashboardController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label hoursLabel;


    private Receptionist currentReceptionist;

    @FXML
    public void initialize() {

        currentReceptionist = (Receptionist) HotelDatabase.staff.get(0);
        nameLabel.setText("Name: " + currentReceptionist.getUsername());
        roleLabel.setText("Role: " + currentReceptionist.getRole());
        hoursLabel.setText("working Hours: " + currentReceptionist.getWorkingHours());
    }
}