
    package GUI.Controllers;
import GuestandRoomSystem.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MakeReservationController {
    @FXML
    private ComboBox<Room> roomComboBox;
    @FXML
    private DatePicker checkInPicker;
    @FXML
    private DatePicker checkOutPicker;
    @FXML
    private VBox ChosenRoomInfo;
    @FXML
    private Label roomDetailsLabel;
    @FXML
    private Label totalAmountLabel;
    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        totalAmountLabel.setWrapText(true);
        for (Room room : HotelDatabase.rooms) {
            if (room.Isavailable()) {
                roomComboBox.getItems().add(room);
            }
        }

        roomComboBox.setOnAction(e ->

                {
                    Room selectedRoom = roomComboBox.getValue();
                    if (selectedRoom != null) {
                        roomDetailsLabel.setText(selectedRoom.toString());
                        ChosenRoomInfo.setVisible(true);
                        ChosenRoomInfo.setManaged(true);
                        totalAmountLabel.setText("Please select dates to calculate total amount");
                    }
                }
        );
        checkInPicker.valueProperty().addListener((obs,oldVal, newVal)->updateTotalAmount());
        checkOutPicker.valueProperty().addListener((obs,oldVal, newVal)->updateTotalAmount());
    }
        @FXML
        public void handleConfirm (javafx.event.ActionEvent event){
            Room selectedRoom = roomComboBox.getValue();
            LocalDate checkin = checkInPicker.getValue();
            LocalDate checkout = checkOutPicker.getValue();
            if (selectedRoom == null ) {
                statusLabel.setText("Please select a room");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
                return;
            }
            if (checkin==null){
                statusLabel.setText("Please select a check-in date");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
                return;
            }
            if (checkout==null){
                statusLabel.setText("Please select a check-out date");
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
                return;
            }
            try{
HotelDatabase.currentGuest.makeReservation(selectedRoom,checkin,checkout);
                statusLabel.setText("Reservation created successfully!\n Thank you for choosing The Nile Atelier♡");
                statusLabel.setStyle("-fx-text-fill: #27ae60;");
            } catch (Exception e) {
                statusLabel.setText("Error: " + e.getMessage());
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
        }
    }
@FXML
public void GoBack(javafx.event.ActionEvent event){
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/GUI/FXML/GuestDashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 576));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateTotalAmount(){
        Room selectedRoom = roomComboBox.getValue();
        LocalDate checkindate = checkInPicker.getValue();
        LocalDate checkoutdate = checkOutPicker.getValue();
        if (selectedRoom!=null && checkindate!=null && checkoutdate!=null) {
            if (checkoutdate.isAfter(checkindate)){
                long nights = checkoutdate.toEpochDay()- checkindate.toEpochDay();
                double Total= selectedRoom.CalculateTotalPrice((int)nights);
                totalAmountLabel.setText("Total price: "+ Total +"LE for "+ nights + "night(s)" );
            }
            else {
                totalAmountLabel.setText("CheckOut date must be after checkIn date");
            }
        }




    }
}



