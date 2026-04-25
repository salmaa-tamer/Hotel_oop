package GUI.Controllers;

import GuestandRoomSystem.Gender;
import GuestandRoomSystem.Guest;
import GuestandRoomSystem.HotelDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegisterController {
   @FXML private TextField txtUsername;
   @FXML private PasswordField txtPassword;
   @FXML private TextField txtDob;
   @FXML private TextField txtBalance;
   @FXML private TextField txtAddress;
   @FXML private ComboBox<String> cmbGender;
   @FXML private ComboBox<String> cmbRoomPreference;
   @FXML private Label lblMessage;

   @FXML
    public void initialize(){
        cmbGender.getItems().addAll("MALE","FEMALE");
        for (var rt: HotelDatabase.roomTypes){
            cmbRoomPreference.getItems().add(rt.getName());
        }
        lblMessage.setText("");
    }

    //REGISTER HANDLER
    @FXML
    public void handleRegister(ActionEvent e){
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String dobText = txtDob.getText().trim();
        String balText = txtBalance.getText().trim();
        String address = txtAddress.getText().trim();
        String genderTxt = cmbGender.getValue();
        String roomPref = cmbRoomPreference.getValue();


        //EMPTY CHECK
        if(username.isEmpty() || password.isEmpty() || dobText.isEmpty() || balText.isEmpty() || address.isEmpty()
        || genderTxt == null || roomPref ==null){
            showMessage("Please fill in all fields.",false );
            return;
        }

        //parse date
        LocalDate dob;
        try{
            dob=LocalDate.parse(dobText);
        }
        catch(DateTimeParseException ex){
            showMessage("Date must be YYYY-MM-DD format.",false);
            return;
        }

        //parse balance
        double balance;
        try{
            balance = Double.parseDouble(balText);
        }
        catch (NumberFormatException ex){
            showMessage("Balance must be a number.",false);
            return;
        }

        //check username is not taken
        for(Guest g : HotelDatabase.guests){
            if (g.getUsername().equalsIgnoreCase(username)){
                showMessage("Username is already taken.",false);
                return;
            }
        }

        //CREATE GUEST
        try{
            Gender gender =Gender.valueOf(genderTxt);
            Guest newGuest=new Guest(username,password,dob,balance,address, gender ,roomPref);

            newGuest.register();
            SessionManager.setCurrentGuest(newGuest);
            SessionManager.setCurrentStaff(null);
            showMessage("Acount created! Redirecting...",true);
            navigateTo(e,"/GUI/FXML/GuestDashboard.fxml", "Guest Dashboard");
        }
        catch(IllegalArgumentException ex){
            showMessage("Error: "+ ex.getMessage(),false);
        }
    }

    @FXML
    public void goToLogin(ActionEvent e){
        navigateTo(e,"/GUI/FXML/LoginScreen.fxml", "Login");
    }

    private void navigateTo(ActionEvent e,String path,String title){
        try{
            Parent root=FXMLLoader.load(getClass().getResource(path));
            Stage stage =(Stage)((javafx.scene.Node) e.getSource()).getScene().getWindow();
            Scene scene =new Scene(root,1024,576);
            scene.getStylesheets().add(
                    getClass().getResource("/GUI/CSS/styles.css").toExternalForm()
            );
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
        }
        catch(Exception ex){
            showMessage("Couldn't navigate.", false );
        }
    }
    private void showMessage(String msg ,boolean success ){
        lblMessage.setText(msg);
        lblMessage.setStyle(success? "-fx-text-fill: #27ae60; -fx-font-weight: bold;"
                : "-fx-text-fill: #e74c3c; -fx-font-weight: bold;" );
    }

}
