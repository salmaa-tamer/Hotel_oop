package GUI.CODE;

import GuestandRoomSystem.HotelDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminTestMain extends Application {

        @Override
        public void start(Stage stage) throws Exception {

            HotelDatabase.loadDummyData();
            Parent root = FXMLLoader.load(
                    getClass().getResource("/GUI/FXML/AdminDashboard.fxml")
            );
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        public static void main(String[] args) {
            launch(args);
        }

}
