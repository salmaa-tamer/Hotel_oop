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
        if (SessionManager.getCurrentStaff() instanceof Receptionist) {
            currentReceptionist = (Receptionist) SessionManager.getCurrentStaff();
        } else {
            currentReceptionist = (Receptionist) HotelDatabase.staff.get(0);
        }

        nameLabel.setText("Name: " + currentReceptionist.getUsername());
        roleLabel.setText("Role: " + currentReceptionist.getRole());
        hoursLabel.setText("Working Hours: " + currentReceptionist.getWorkingHours());
    }

    // Reem
    @FXML
    public void handleViewProfile(javafx.event.ActionEvent event) {
        Label lblTitle = new Label("View Profile");
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button btnBack = new Button("Back to Dashboard");
        btnBack.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8px 15px;");
        btnBack.setOnAction(e -> goBackToDashboard(e));

        HBox header = new HBox(20);
        header.getChildren().add(btnBack);
        header.getChildren().add(lblTitle);
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20px; -fx-alignment: center-left;");

        Label titleLbl = new Label("My Profile");
        titleLbl.setStyle("-fx-font-size: 28px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        Separator s1 = new Separator();

        Label usernameLbl = new Label("Username: " + currentReceptionist.getUsername());
        usernameLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50;");

        Label roleLbl = new Label("Role: " + currentReceptionist.getRole());
        roleLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50;");

        Label hoursLbl = new Label("Working Hours: " + currentReceptionist.getWorkingHours() + " hours/day");
        hoursLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50;");

        Label dobLbl = new Label("Date of Birth: " + currentReceptionist.getDateOfBirth());
        dobLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50;");

        Separator s2 = new Separator();

        Label changePasswordLbl = new Label("Change Password");
        changePasswordLbl.setStyle("-fx-font-size: 22px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        Separator s3 = new Separator();

        Label currentPassLbl = new Label("Current Password:");
        currentPassLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        PasswordField currentPassField = new PasswordField();
        currentPassField.setPromptText("Enter current password...");
        currentPassField.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
        currentPassField.setPrefWidth(450);

        Label newPassLbl = new Label("New Password:");
        newPassLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        PasswordField newPassField = new PasswordField();
        newPassField.setPromptText("Enter new password...");
        newPassField.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
        newPassField.setPrefWidth(450);

        Label confirmPassLbl = new Label("Confirm New Password:");
        confirmPassLbl.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        PasswordField confirmPassField = new PasswordField();
        confirmPassField.setPromptText("Confirm new password...");
        confirmPassField.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
        confirmPassField.setPrefWidth(450);

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button changePassBtn = new Button("CHANGE PASSWORD");
        changePassBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px 84px; -fx-cursor: hand;");
        changePassBtn.setPrefWidth(450);

        changePassBtn.setOnAction(e -> {
            try {
                String current = currentPassField.getText();
                String newPass = newPassField.getText();
                String confirm = confirmPassField.getText();

                if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled!");
                }

                if (!currentReceptionist.getPassword().equals(current)) {
                    throw new IllegalArgumentException("Current password is incorrect!");
                }

                if (!newPass.equals(confirm)) {
                    throw new IllegalArgumentException("New passwords do not match!");
                }

                if (newPass.length() < 8) {
                    throw new IllegalArgumentException("Password must be at least 8 characters!");
                }

                currentReceptionist.setPassword(newPass);

                statusLabel.setText("Password changed successfully!");
                statusLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px; -fx-font-weight: bold;");

                currentPassField.clear();
                newPassField.clear();
                confirmPassField.clear();

            } catch (IllegalArgumentException ex) {
                statusLabel.setText("ERROR: " + ex.getMessage());
                statusLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bold;");
            }
        });

        VBox formContent = new VBox(15);
        formContent.getChildren().addAll(
                titleLbl,
                s1,
                usernameLbl,
                roleLbl,
                hoursLbl,
                dobLbl,
                s2,
                changePasswordLbl,
                s3,
                currentPassLbl,
                currentPassField,
                newPassLbl,
                newPassField,
                confirmPassLbl,
                confirmPassField,
                changePassBtn,
                statusLabel
        );

        formContent.setPadding(new Insets(30));
        formContent.setStyle("-fx-background-color: transparent;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(formContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");

        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(scrollPane);

        switchScene(event, layout);
    }

    @FXML
    public void openCheckInScreen(javafx.event.ActionEvent event) {
        Label lblTitle = new Label("Process Guest Check-In");
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button btnBack = new Button("Back to Dashboard");
        btnBack.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8px 15px;");
        btnBack.setOnAction(e -> goBackToDashboard(e));

        HBox header = new HBox(20);
        header.getChildren().add(btnBack);
        header.getChildren().add(lblTitle);
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20px; -fx-alignment: center-left;");

        ComboBox<Reservation> resCombo = new ComboBox<>();
        resCombo.setPromptText("Select a Reservation...");
        resCombo.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
        resCombo.setPrefWidth(450);

        for (Reservation res : HotelDatabase.reservations) {
            resCombo.getItems().add(res);
        }

        Button btnConfirm = new Button("Confirm Check-In");
        btnConfirm.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10px;");
        btnConfirm.setPrefWidth(450);

        Label lblStatus = new Label();
        lblStatus.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        btnConfirm.setOnAction(e -> {
            try {
                Reservation selectedRes = resCombo.getValue();

                if (selectedRes == null) {
                    throw new IllegalArgumentException("Please select a reservation first!");
                }

                currentReceptionist.manageCheckIn(selectedRes);

                lblStatus.setText("Success! " + selectedRes.getGuest().getUsername() + " has been checked in.");
                lblStatus.setStyle("-fx-text-fill: #27ae60;");

            } catch (IllegalArgumentException ex) {
                lblStatus.setText("Error: " + ex.getMessage());
                lblStatus.setStyle("-fx-text-fill: #e74c3c;");

            } catch (Exception ex) {
                lblStatus.setText("Unexpected error occurred.");
                lblStatus.setStyle("-fx-text-fill: #e74c3c;");
                ex.printStackTrace();
            }
        });

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

    @FXML
    public void HandleLogout(javafx.event.ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(
                    getClass().getResource("/GUI/FXML/LoginScreen.fxml")
            );

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            double width = stage.getScene().getWidth();
            double height = stage.getScene().getHeight();

            stage.setScene(new Scene(root, width, height));
            stage.setMaximized(true);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            for (Object item : databaseList) {
                listView.getItems().add(item.toString());
            }
        }

        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(listView);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        double width = stage.getScene().getWidth();
        double height = stage.getScene().getHeight();

        layout.setPrefSize(width, height);

        Scene newScene = new Scene(layout, width, height);

        try {
            String cssPath = getClass().getResource("/GUI/CSS/styles.css").toExternalForm();
            newScene.getStylesheets().add(cssPath);
        } catch (NullPointerException ex) {
            System.out.println("CSS file not found ");
        }

        stage.setScene(newScene);
        stage.setMaximized(true);
    }

    private void switchScene(javafx.event.ActionEvent event, BorderPane layout) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        double width = stage.getScene().getWidth();
        double height = stage.getScene().getHeight();

        layout.setPrefSize(width, height);

        Scene newScene = new Scene(layout, width, height);

        try {
            String cssPath = getClass().getResource("/GUI/CSS/styles.css").toExternalForm();
            newScene.getStylesheets().add(cssPath);
        } catch (NullPointerException ex) {
            System.out.println("CSS file not found ");
        }

        stage.setScene(newScene);
        stage.setMaximized(true);
    }

    private void goBackToDashboard(javafx.event.ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(
                    getClass().getResource("/GUI/FXML/ReceptionistDashboard.fxml")
            );

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            double width = stage.getScene().getWidth();
            double height = stage.getScene().getHeight();

            stage.setScene(new Scene(root, width, height));
            stage.setMaximized(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}