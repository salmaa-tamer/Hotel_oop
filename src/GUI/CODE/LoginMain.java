package GUI.CODE;

import GuestandRoomSystem.HotelDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        HotelDatabase.loadDummyData();

        Parent root = FXMLLoader.load(
                getClass().getResource("/GUI/FXML/LoginScreen.fxml")
        );

        Scene scene = new Scene(root, 1024, 576);
        scene.getStylesheets().add(
                getClass().getResource("/GUI/CSS/styles.css").toExternalForm()
        );

        stage.setTitle("The Nile Atelier - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}