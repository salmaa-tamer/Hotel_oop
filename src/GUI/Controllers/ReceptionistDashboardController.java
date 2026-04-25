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

    @FXML private Label nameLabel;
    @FXML private Label roleLabel;
    @FXML private Label hoursLabel;


    private Receptionist currentReceptionist;

    @FXML
    public void initialize() {

        currentReceptionist = (Receptionist) HotelDatabase.staff.get(0);
        nameLabel.setText("Name: " + currentReceptionist.getUsername());
       // roleLabel.setText("Role: " + currentReceptionist.getRole());
        hoursLabel.setText("working Hours: " + currentReceptionist.getWorkingHours());
    }

    @FXML
    public void openCheckInScreen(javafx.event.ActionEvent event) {
        //labels w buttons main dashboard
        Label lblTitle = new Label("Process Guest Check-In");
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8px 15px;");
        btnBack.setOnAction(e -> goBackToDashboard(e));
        //horizontal listing lel back to dashb
        HBox header = new HBox(20);
        header.getChildren().add(btnBack);
        header.getChildren().add(lblTitle);
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20px; -fx-alignment: center-left;");
        // dropdown lel reservations
        ComboBox<Reservation> resCombo = new ComboBox<>();
        resCombo.setPromptText("Select a Reservation...");
        resCombo.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
        resCombo.setPrefWidth(450);
        // show kol el reservations mn el database
        for (Reservation res : HotelDatabase.reservations) {
            resCombo.getItems().add(res);
        }
        //labels w buttons el check in screen
        Button btnConfirm = new Button("Confirm Check-In");
        btnConfirm.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10px;");
        btnConfirm.setPrefWidth(450);
        Label lblStatus = new Label();
        lblStatus.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // handling reservation event
        btnConfirm.setOnAction(e -> {
            try {
                Reservation selectedRes = resCombo.getValue();
                if (selectedRes == null) {
                    throw new IllegalArgumentException("Please select a reservation first!");
                }

                currentReceptionist.manageCheckIn(selectedRes);
                lblStatus.setText("Success! " + selectedRes.getGuest().getUsername() + " has been checked in.");
                lblStatus.setStyle("-fx-text-fill: #27ae60;");
            } catch (Exception ex) {
                lblStatus.setText("Error: " + ex.getMessage());
                lblStatus.setStyle("-fx-text-fill: #e74c3c;");

            }
        });
        // list el reservations
        VBox formLayout = new VBox(25);
        formLayout.getChildren().add(new Label("Select a pending reservation to check in:"));
        formLayout.getChildren().add(resCombo);
        formLayout.getChildren().add(btnConfirm);
        formLayout.getChildren().add(lblStatus);
        formLayout.setAlignment(javafx.geometry.Pos.CENTER);
        formLayout.setStyle("-fx-background-color: #ffffff; -fx-padding: 40px;");

        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(formLayout);

        switchScene(event, layout);
    }

    @FXML
    public void openCheckOutScreen(javafx.event.ActionEvent event) {
        Label lblTitle = new Label("Process Check-Out & Payment");
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button btnBack = new Button("Back to Dashboard");
        btnBack.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8px 15px;");
        btnBack.setOnAction(e -> goBackToDashboard(e));

        HBox header = new HBox(20);
        header.getChildren().add(btnBack);
        header.getChildren().add(lblTitle);
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20px; -fx-alignment: center-left;");

        ComboBox<Reservation> resCombo = new ComboBox<>();
        resCombo.setPromptText("Select Reservation to Check-Out...");
        resCombo.setStyle("-fx-font-size: 14px; -fx-background-color: #ecf0f1;");
        resCombo.setPrefWidth(450);

        for (Reservation res : HotelDatabase.reservations) {
            resCombo.getItems().add(res);
        }

        ComboBox<PaymentMethod> payCombo = new ComboBox<>();
        payCombo.getItems().add(PaymentMethod.CASH);
        payCombo.getItems().add(PaymentMethod.Credit_Card);
        payCombo.setPromptText("Select Payment Method");
        payCombo.setStyle("-fx-font-size: 14px; -fx-background-color: #ecf0f1;");
        payCombo.setPrefWidth(450);

        Button btnConfirm = new Button("Process Payment & Generate Invoice");
        btnConfirm.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10px;");
        btnConfirm.setPrefWidth(450);

        Label lblStatus = new Label();
        lblStatus.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextArea txtInvoice = new TextArea();
        txtInvoice.setEditable(false);
        txtInvoice.setPrefSize(450, 200);
        txtInvoice.setStyle("-fx-font-family: 'Monospaced'; -fx-font-size: 14px; -fx-control-inner-background: #f8f9fa; -fx-border-color: #bdc3c7;");

        btnConfirm.setOnAction(e -> {
            try {
                Reservation selectedRes = resCombo.getValue();
                if (selectedRes == null) {
                    throw new IllegalArgumentException("Select a reservation.");
                }

                PaymentMethod method = payCombo.getValue();
                if (method == null) {
                    throw new IllegalArgumentException("Select a payment method.");
                }

                currentReceptionist.manageCheckOut(selectedRes, method);
                lblStatus.setText("Payment successful! Guest checked out.");
                lblStatus.setStyle("-fx-text-fill: #27ae60;");

                // double total = selectedRes.CalculateTotalPrice();

                String invoice = "====================================\n" +
                        "          OFFICIAL INVOICE          \n" +
                        "====================================\n" +
                        "Guest Name:     " + selectedRes.getGuest().getUsername() + "\n" +
                        "Room Number:    " + selectedRes.getRoom().getRoomid() + "\n" +
                        "Payment Method: " + method + "\n" +
                        "------------------------------------\n" +
                        "TOTAL PAID:     $" + selectedRes.CalculateTotalPrice() + "\n" +
                        "====================================\n";
                txtInvoice.setText(invoice);
            } catch (Exception ex) {
                lblStatus.setText("Error: " + ex.getMessage());
                lblStatus.setStyle("-fx-text-fill: #e74c3c;");
            }
        });

        VBox formLayout = new VBox(20);
        formLayout.getChildren().add(resCombo);
        formLayout.getChildren().add(payCombo);
        formLayout.getChildren().add(btnConfirm);
        formLayout.getChildren().add(lblStatus);
        formLayout.getChildren().add(txtInvoice);

        formLayout.setAlignment(javafx.geometry.Pos.CENTER);
        formLayout.setStyle("-fx-background-color: #ffffff; -fx-padding: 40px;");

        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(formLayout);

        switchScene(event, layout);
    }

    @FXML
    public void handleViewGuests(javafx.event.ActionEvent event) {
        switchToDatabaseView(event, "Guest Database", HotelDatabase.guests);
    }

    @FXML
    public void handleViewRooms(javafx.event.ActionEvent event) {
        switchToDatabaseView(event, "Room Database", HotelDatabase.rooms);
    }

    @FXML
    public void handleViewReservations(javafx.event.ActionEvent event) {
        switchToDatabaseView(event, "Reservation Database", HotelDatabase.reservations);
    }

    private void switchToDatabaseView(javafx.event.ActionEvent event, String title, java.util.List<?> databaseList) {
        Label lblTitle = new Label(title);
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button btnBack = new Button("Back to Dashboard");
        btnBack.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8px 15px;");
        btnBack.setOnAction(e -> goBackToDashboard(e));

        HBox header = new HBox(20);
        header.getChildren().add(btnBack);
        header.getChildren().add(lblTitle);
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15px; -fx-alignment: center-left;");

        ListView<String> listView = new ListView<>();
        if (databaseList.isEmpty()) {
            listView.getItems().add("No records currently found in the system.");
        } else {
            // list el data
            for (Object item : databaseList) {
                listView.getItems().add(item.toString());
            }
        }

        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(listView);

        Scene newScene = new Scene(layout, 1024, 576);

        try {
            String cssPath = getClass().getResource("/GUI/CSS/styles.css").toExternalForm();
            newScene.getStylesheets().add(cssPath);
        } catch (NullPointerException ex) {
            System.out.println("CSS file not found ");
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
    }

    private void switchScene(javafx.event.ActionEvent event, BorderPane layout) {
        Scene newScene = new Scene(layout, 1024, 576);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
    }

    private void goBackToDashboard(javafx.event.ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/GUI/FXML/ReceptionistDashboard.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 576));
        } catch (Exception ex) {
        }
    }
}