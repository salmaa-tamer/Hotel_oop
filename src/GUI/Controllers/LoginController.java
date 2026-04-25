package GUI.Controllers;

import GuestandRoomSystem.Guest;
import GuestandRoomSystem.HotelDatabase;
import StaffSystem.Role;
import StaffSystem.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
  @FXML  private TextField txtUsername;
   @FXML private PasswordField txtPassword;
  @FXML  private Label lblError;
   @FXML private Button btnToggleGuest;
  @FXML  private Button btnToggleStaff;
  @FXML  private Button btnToggleRegister;
   @FXML private Button btnGoRegister;

    private boolean isGuestMode =true;

    @FXML
    public void initialize(){
        setActiveStyle(btnToggleGuest);
        setInactiveStyle(btnToggleStaff);
         btnGoRegister.setVisible(true);
    }
    @FXML
    public void switchToGuest(ActionEvent e){
        isGuestMode=true;
        setActiveStyle(btnToggleGuest);
        setInactiveStyle(btnToggleStaff);
        btnGoRegister.setVisible(true);
        lblError.setText("");
    }
    @FXML
    public void switchToStaff(ActionEvent e){
        isGuestMode=false;
        setActiveStyle(btnToggleStaff);
        setInactiveStyle(btnToggleGuest);
        btnGoRegister.setVisible(false);
        lblError.setText("");
    }
    // MAIN LOGIN HANDLER
    @FXML
     public void handleLogin(ActionEvent e){
        String username =txtUsername.getText().trim();
        String password= txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password.");
            return;
        }
        if(isGuestMode){
            LoginAsGuest(username,password ,e);
        }
        else {
            LoginAsStaff(username,password,e);
        }
     }

     //GUEST LOGIN
    private void LoginAsGuest(String username ,String password,ActionEvent e){
        try{
            Guest loggedIn =Guest.login(username, password);
            SessionManager.setCurrentGuest(loggedIn);
            SessionManager.setCurrentStaff(null);
            navigateTo(e,"/GUI/FXML/GuestDashboard.fxml", "Guest Dashboard");
        } catch (IllegalArgumentException ex){
            showError("Invalid username or password.");

        }
    }

    //STAFF LOGIN
    private void LoginAsStaff(String username,String password,ActionEvent e){
        try{
            Staff matched =null;
            for(Staff s : HotelDatabase.staff){
                if(s.getUsername().equals(username)){
                    s.login(username, password);
                    matched=s;
                    break;
                }
            }
            if(matched==null){
                showError("Staff member not found!");
                return;
            }
            SessionManager.setCurrentStaff(matched);
            SessionManager.setCurrentGuest(null);
            if(matched.getRole() ==Role.ADMIN){
                navigateTo(e, "/GUI/FXML/AdminDashboard.fxml", "Admin Dashboard");
            }
            else{
                navigateTo(e,"/GUI/FXML/ReceptionistDashboard.fxml", "Receptionist Dashboard");
            }
        }
        catch(IllegalArgumentException ex){
            showError("Invallid username or password.");
        }
    }

    //GO TO REGISTER
    @FXML
    public void goToRegister(ActionEvent e){
        navigateTo(e,"/GUI/FXML/RegisterScreen.fxml", "Register");
    }

    private void navigateTo(ActionEvent e,String path,String title){
        try{
            Parent root =FXMLLoader.load(getClass().getResource(path));
            Stage stage=(Stage)((javafx.scene.Node) e.getSource()).getScene().getWindow();
            Scene scene =new Scene(root,1024,576);
            scene.getStylesheets().add(getClass().getResource("/GUI/CSS/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
        }
        catch(Exception ex){
            showError("Screen is not ready yet.");
        }
    }

    private void showError(String msg){
        lblError.setText(msg);
        lblError.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }

    private void setActiveStyle(Button btn){
        btn.setStyle("-fx-background-color: #1a252f; -fx-text-fill: white;" +
                "-fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 10;");
    }

    private void setInactiveStyle(Button btn){
        btn.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #7f8c8d;" +
                "-fx-font-size: 14px; -fx-cursor: hand; -fx-padding: 10;");
    }
}
