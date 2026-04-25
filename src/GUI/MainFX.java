package GUI;

import GuestandRoomSystem.HotelDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/GuestDashboard.fxml"));
        stage.setTitle("The Nile Atelier-Guest");
       Scene scene= new Scene(root, 1024, 576);
        String css = getClass().getResource("/GUI/CSS/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        HotelDatabase.loadDummyData();
        launch(args);
    }
}
