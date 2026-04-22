package GUI.CODE;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage){
        Button manageRooms= new Button("Manage Rooms");
        Button manageAmenities= new Button(" Manage Amenities");
        Button manageRoomTypes = new Button ( " Manage Room Types");
        Button manageStaff = new Button(" Manage Staff");
        Button viewBills = new Button("View Hotel Bills");

        BorderPane root= new BorderPane();
        root.getChildren().addAll(manageAmenities,manageStaff,manageRooms,manageRoomTypes,viewBills);

        Scene scene= new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }



    public static void main(String[] args){
        launch(args);
    }


}