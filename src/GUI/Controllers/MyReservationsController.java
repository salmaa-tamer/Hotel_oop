package GUI.Controllers;
import GuestandRoomSystem.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MyReservationsController {
    @FXML
    private VBox Reservationlist;
    @FXML
    private Button btnAll;
    @FXML
    private Button btnPending;
    @FXML
    private Button btnConfirmed;
    @FXML
    private Button btnCancelled;
    @FXML
    private Button btnCompleted;
    @FXML
    public void FilterAll(javafx.event.ActionEvent event){
        setActiveFilter(btnAll);
        loadReservations(null);;}

    @FXML
    public void FilterPending(javafx.event.ActionEvent event){
        setActiveFilter(btnPending);
        loadReservations(ReservationStatus.PENDING);}
    @FXML
    public void FilterConfirmed(javafx.event.ActionEvent event){
        setActiveFilter(btnConfirmed);
        loadReservations(ReservationStatus.CONFIRMED);}
    @FXML
    public void FilterCancelled(javafx.event.ActionEvent event){
        setActiveFilter(btnCancelled);
        loadReservations(ReservationStatus.CANCELLED);}
    @FXML
    public void FilterCompleted(javafx.event.ActionEvent event){
        setActiveFilter(btnCompleted);
        loadReservations(ReservationStatus.COMPLETED);}
    @FXML
    public void initialize(){
        Button[] allButtons = {btnAll, btnPending, btnConfirmed, btnCancelled, btnCompleted};
        for (Button btn : allButtons) {
            btn.setOnMouseEntered(e -> {
                if (!btn.getStyle().contains("#27ae60")) {
                    btn.setStyle("-fx-background-color: #f2f3f4; -fx-text-fill: #2c3e50; -fx-border-color: #bdc3c7; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5px 14px; -fx-cursor: hand;");
                }
            });
            btn.setOnMouseExited(e -> {
                if (!btn.getStyle().contains("#27ae60")) {
                    btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #2c3e50; -fx-border-color: #bdc3c7; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5px 14px; -fx-cursor: hand;");
                }
            });
        }
        setActiveFilter(btnAll);
        loadReservations(null); }

    private void loadReservations(ReservationStatus filter){
        Reservationlist.getChildren().clear();
        for (Reservation reservation:HotelDatabase.reservations){
            boolean BelongsToGuest= reservation.getGuest().getUsername().equals(HotelDatabase.currentGuest.getUsername());
            boolean MatchesStatusFilter=(filter==null || reservation.getStatus()==filter);
            if(BelongsToGuest && MatchesStatusFilter){
                // viewing the room id and type in the room card
                Label roomLabel = new Label ("Room" + reservation.getRoom().getRoomid() + "----" + reservation.getRoom().getRoomtype().getName());
                roomLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
                // viewing check in and check out date
                Label RoomDateLabel = new Label("Check-in: " + reservation.getCheckInDate() + "   |   Check-out: " + reservation.getCheckOutDate());
          RoomDateLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #7f8c8d;");
//setting the room info and dates to be left alligned
          VBox leftSide = new VBox(4, roomLabel,RoomDateLabel);
          // viewing the room status
                Label StatusLabel = new Label(reservation.getStatus().toString());
                StatusLabel.setStyle("-fx-background-color: " + getStatusLabelcolour(reservation.getStatus()) + "; -fx-text-fill: " + getStatusLabelTextColour(reservation.getStatus()) + "; -fx-padding: 4px 12px; -fx-background-radius: 20;");
                HBox RoomCard = new HBox();
                RoomCard.setStyle("-fx-border-color: #ecf0f1; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 14px 18px;");
                RoomCard.setAlignment(Pos.CENTER_LEFT);
                HBox.setHgrow(leftSide,javafx.scene.layout.Priority.ALWAYS);
                RoomCard.getChildren().addAll(leftSide,StatusLabel);
                Reservationlist.getChildren().add(RoomCard);
            }

        }
    }
    private String getStatusLabelcolour (ReservationStatus status) {
        switch (status) {
            case PENDING: return  "#fef9e7";
            case CONFIRMED:return "#eafaf1";
            case CANCELLED:return "#fdedec";
            case COMPLETED:return "#eaf3fb";
            default : return "#ffffff";
        }
    }
    private String getStatusLabelTextColour(ReservationStatus status){
        switch (status){
            case PENDING: return  "#d4ac0d";
            case CONFIRMED:return "#1e8449";
            case CANCELLED:return "#922b21";
            case COMPLETED:return "#1a5276";
            default : return "#000000";
        }
    }
    @FXML
    public void GoBackToDashboard(javafx.event.ActionEvent event){
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/GUI/FXML/GuestDashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 576));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setActiveFilter(Button activeButton) {
        Button[] allButtons = {btnAll, btnPending, btnConfirmed, btnCancelled, btnCompleted};
        for (Button btn : allButtons) {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #2c3e50; -fx-border-color: #bdc3c7; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5px 14px; -fx-cursor: hand;");
        }
        activeButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-border-color: #27ae60; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5px 14px; -fx-cursor: hand;");
    }

}
