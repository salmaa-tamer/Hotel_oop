package GUI.Controllers;

import GuestandRoomSystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

    public class CheckoutPaymentController {
        @FXML
        private ComboBox<Reservation> cmbReservations;
        @FXML
        private ComboBox<PaymentMethod> cmbPaymentMethod;
        @FXML
        private Label lblGuestName;
        @FXML
        private Label lblRoomNumber;
        @FXML
        private Label lblCheckIn;
        @FXML
        private Label lblCheckOut;
        @FXML
        private Label lblTotalAmount;
        @FXML
        private Label lblPaymentMethod;
        @FXML
        private Label lblMessage;
        @FXML
        private Button btnConfirmPayment;
        private Guest currentGuest;
        private Reservation selectedReservation;
        @FXML private Label welcomelabel;
        @FXML private Label balancelabel;

        @FXML
        public void initialize() {
            currentGuest = HotelDatabase.currentGuest;

            if (currentGuest == null) {
                lblMessage.setText("Error: No guest logged in.");
                return;
            }
            welcomelabel.setText("Welcome, " + currentGuest.getUsername());
            balancelabel.setText("Balance: $" + currentGuest.getBalance());

            // load only CONFIRMED reservations
            for (Reservation r : HotelDatabase.reservations) {
                if (r.getGuest().getUsername().equals(currentGuest.getUsername())
                        && r.getStatus() == ReservationStatus.CONFIRMED) {
                    cmbReservations.getItems().add(r);
                }
            }

            if (cmbReservations.getItems().isEmpty()) {
                lblMessage.setText("No confirmed reservations found.");
                btnConfirmPayment.setDisable(true);
            }

            // load payment methods
            cmbPaymentMethod.getItems().addAll(
                    PaymentMethod.CASH,
                    PaymentMethod.Credit_Card,
                    PaymentMethod.ONLINE
            );

            // when reservation is selected show bill preview
            cmbReservations.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    selectedReservation = newVal;
                    showBillPreview(newVal);
                }
            });
        }
        private void showBillPreview(Reservation r) {
            lblGuestName.setText(r.getGuest().getUsername());
            lblRoomNumber.setText("Room " + r.getRoom().getRoomid());
            lblCheckIn.setText(r.getCheckInDate().toString());
            lblCheckOut.setText(r.getCheckOutDate().toString());
            lblTotalAmount.setText("$" + r.CalculateTotalPrice());
            lblPaymentMethod.setText("—");
            lblMessage.setText("");
        }
        @FXML
        public void handleConfirmPayment(ActionEvent e) {
            if (selectedReservation == null) {
                showMessage("Please select a reservation first.", false);
                return;
            }

            PaymentMethod method = cmbPaymentMethod.getValue();
            if (method == null) {
                showMessage("Please select a payment method.", false);
                return;
            }

            try {
                if (method == PaymentMethod.ONLINE) {
                    currentGuest.onlineCheckout(selectedReservation, method);
                } else {
                    currentGuest.inPersonCheckout(selectedReservation, method);
                }

                lblPaymentMethod.setText(method.toString());
                showMessage("Payment successful! Thank you for staying with us.", true);
                btnConfirmPayment.setDisable(true);
                cmbReservations.setDisable(true);
                cmbPaymentMethod.setDisable(true);

            } catch (Exception ex) {
                showMessage("Error: " + ex.getMessage(), false);
            }
        }
        @FXML
        public void goToDashboard(ActionEvent e) {
            try {
                Parent root = FXMLLoader.load(
                        getClass().getResource("/GUI/FXML/GuestDashboard.fxml")
                );
                Stage stage = (Stage)((javafx.scene.Node) e.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);
                scene.getStylesheets().add(
                        getClass().getResource("/GUI/CSS/styles.css").toExternalForm());
                stage.setScene(scene);
                stage.setTitle("Guest Dashboard");
                stage.setMaximized(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage("Could not navigate back.", false);
            }
        }

        private void showMessage(String msg, boolean success) {
            lblMessage.setText(msg);
            lblMessage.setStyle(success
                    ? "-fx-text-fill: #27ae60; -fx-font-weight: bold;"
                    : "-fx-text-fill: #e74c3c; -fx-font-weight: bold;"
            );
        }
    }


