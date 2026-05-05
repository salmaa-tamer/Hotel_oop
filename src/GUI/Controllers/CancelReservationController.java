package GUI.Controllers;
import GuestandRoomSystem.HotelDatabase;
import GuestandRoomSystem.Reservation;
import GuestandRoomSystem.ReservationStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import GUI.Controllers.SessionManager;
public class CancelReservationController {
    @FXML
    private VBox ReservationContainer;
    @FXML
    private Label StatusLabel;

    private String getStatusLabelcolour(ReservationStatus status) {
        switch (status) {
            case PENDING:
                return "#fef9e7";
            case CONFIRMED:
                return "#eafaf1";
            case CANCELLED:
                return "#fdedec";
            case COMPLETED:
                return "#eaf3fb";
            default:
                return "#ffffff";
        }
    }

    private String getStatusLabelTextColour(ReservationStatus status) {
        switch (status) {
            case PENDING:
                return "#d4ac0d";
            case CONFIRMED:
                return "#1e8449";
            case CANCELLED:
                return "#922b21";
            case COMPLETED:
                return "#1a5276";
            default:
                return "#000000";
        }
    }

    @FXML
    public void initialize() {
        if (SessionManager.getCurrentGuest()==null){
         HotelDatabase.currentGuest = HotelDatabase.guests.get(0);
        }
        loadReservation();
    }

    private void loadReservation() {
        ReservationContainer.getChildren().clear();
        for (Reservation reservation : HotelDatabase.reservations) {
            boolean belongsToGuest = reservation.getGuest().getUsername().equals(SessionManager.getCurrentGuest().getUsername());
            if (belongsToGuest) {
                //viewing room name,id type in a card form
                Label roomLabel = new Label("Room" + reservation.getRoom().getRoomid() + "- - - -" + reservation.getRoom().getRoomtype().getName());
                roomLabel.setStyle(("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;"));
                //display check in and check out dates
                Label roomDateLabel = new Label("Check in:" + reservation.getCheckInDate() + " | Check out: " + reservation.getCheckOutDate());
                roomDateLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #7f8c8d;");
                //setting room info to be left alligned
                VBox leftSide = new VBox(4, roomLabel, roomDateLabel);
                // viewing room status
                Label badgeLabel = new Label(reservation.getStatus().toString());
                badgeLabel.setStyle("-fx-background-color: " + getStatusLabelcolour(reservation.getStatus()) + "; -fx-text-fill: " + getStatusLabelTextColour(reservation.getStatus()) + "; -fx-padding: 4px 12px; -fx-background-radius: 20;");
                HBox RoomCard = new HBox();
                RoomCard.setStyle("-fx-border-color: #ecf0f1; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 14px 18px;");
                RoomCard.setAlignment(Pos.CENTER_LEFT);
                HBox.setHgrow(leftSide, javafx.scene.layout.Priority.ALWAYS);
                RoomCard.getChildren().addAll(leftSide, badgeLabel);
                ReservationContainer.getChildren().add(RoomCard);
                if (reservation.getStatus() == ReservationStatus.PENDING ||
                        reservation.getStatus() == ReservationStatus.CONFIRMED) {
                    //clickable cards that can be confirmed
                    RoomCard.setOnMouseClicked(e -> ShowCancelPOPUP(reservation));
                } else {
                    //grey unclickabel card,cant be confirmed
                    RoomCard.setOpacity(0.5);
                    RoomCard.setStyle(RoomCard.getStyle() + "-fx-cursor: not-allowed;");
                    RoomCard.setOnMouseClicked(e -> {
                                if (reservation.getStatus() == ReservationStatus.COMPLETED) {
                                    StatusLabel.setText("Can't cancel a completed reservation.");
                                } else {
                                    StatusLabel.setText("Can't cancel a cancelled reservation.");
                                }
                                StatusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px;");
                            }
                    );
                }
            }
        }

    }

    private void ShowCancelPOPUP(Reservation reservation) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel reservarion");
        alert.setHeaderText("Are you sure you want to cancel this reservation");
        alert.setContentText("This action can't be undone");
        ButtonType YesBtn = new ButtonType("Yes");
        ButtonType NoBtn = new ButtonType("No");
        alert.getButtonTypes().setAll(YesBtn,NoBtn);
        alert.showAndWait().ifPresent(response -> {
            if (response == YesBtn) {
                reservation.getGuest().cancelReservation(reservation);
                StatusLabel.setText("Your reservation has been cancelled. We hope to welcome you back to The Nile Atelier soon! 🌿");
                StatusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 16px; -fx-font-weight: bold;");
            loadReservation();
            reservation.getRoom().setAvailablity(true);
            }

        });




    }

    @FXML
    public void GoToGuestDashboard(javafx.event.ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/GUI/FXML/GuestDashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 576));
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }





