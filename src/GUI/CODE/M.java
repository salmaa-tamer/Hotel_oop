package GUI.CODE;

import GuestandRoomSystem.HotelDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class M extends Application {

    @Override
    public void start(Stage window) throws Exception {

        // Load the main FXML file
        // Ensure the path matches your actual project structure!
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ReceptionistDashboard.fxml"));

        window.setTitle("Hotel Management System - Receptionist Module");

        // Setting the scene to your preferred 16:9 widescreen dimensions
        Scene scene = new Scene(root, 1024, 576);

        window.setScene(scene);

        // This prevents the professor or user from dragging the window corners
        // Keeping your UI elements exactly where you placed them
        window.setResizable(false);

        window.show();
    }

    public static void main(String[] args) {
        HotelDatabase.loadDummyData();
        launch(args);
    }
}