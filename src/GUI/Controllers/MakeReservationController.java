
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
    private Label checkInError;
    @FXML
    private Label checkOutError;
    @FXML
    private Label roomError;

    @FXML
    public void initialize() {
        if (HotelDatabase.selectedRoom!=null){
            roomComboBox.setValue(HotelDatabase.selectedRoom);
            HotelDatabase.selectedRoom=null;
            Room selected = roomComboBox.getValue();
            if (selected != null){
                roomDetailsLabel.setText(selected.toString());
                ChosenRoomInfo.setVisible(true);
                ChosenRoomInfo.setManaged(true);
                updateTotalAmount();
            }
        }

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
                        updateTotalAmount();
                    }
                }
        );
        //to make the date picke open on click
        checkInPicker.getEditor().setOnMouseClicked(e -> checkInPicker.show());
        checkOutPicker.getEditor().setOnMouseClicked(e -> checkOutPicker.show());

        checkInPicker.valueProperty().addListener((obs,oldVal, newVal)->updateTotalAmount());
        checkOutPicker.valueProperty().addListener((obs,oldVal, newVal)->updateTotalAmount());
    }
        @FXML
        public void handleConfirm (javafx.event.ActionEvent event){
            Room selectedRoom = roomComboBox.getValue();
            LocalDate checkin = checkInPicker.getValue();
            LocalDate checkout = checkOutPicker.getValue();
            if (selectedRoom == null ) {
               roomError.setText("Please select a room");
               roomError.setVisible(true);
               roomError.setManaged(true);
                return;
            }
            if (checkin==null){
                checkInError.setText("Please select a check-in date");
                checkInError.setVisible(true);
                checkInError.setManaged(true);
                return;
            }
            if (checkout==null){
                checkOutError.setText("Please select a check-out date");
                checkOutError.setVisible(true);
                checkOutError.setManaged(true);
                return;
            }
            try{
HotelDatabase.currentGuest.makeReservation(selectedRoom,checkin,checkout);
                statusLabel.setText("Reservation created successfully!\n Thank you for choosing The Nile Atelier♡");
                statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 20px; -fx-font-weight: bold;");
                statusLabel.setAlignment(javafx.geometry.Pos.CENTER);
                statusLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            } catch (Exception e) {
                statusLabel.setText("Error: " + e.getMessage());
                statusLabel.setStyle("-fx-text-fill: #e74c3c;");
        }
    }
@FXML
public void GoBackToDashboard(javafx.event.ActionEvent event){
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/GUI/FXML/GuestDashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 576));
            stage.setMaximized(true);
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
                totalAmountLabel.setText("Total price: "+ Total +" LE for "+ nights + " night(s)" );
                totalAmountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #3498db;");
            }
            else {
                totalAmountLabel.setText("Check-out date must be after check-in date");
                totalAmountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");
            }
        }




    }
}



