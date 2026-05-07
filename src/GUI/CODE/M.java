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

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ReceptionistDashboard.fxml"));

        window.setTitle("Hotel Management System - Receptionist Module");

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.setMaximized(true);

        window.show();
    }

    public static void main(String[] args) {
        HotelDatabase.loadDummyData();
        launch(args);
    }
}