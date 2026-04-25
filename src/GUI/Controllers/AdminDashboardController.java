package GUI.Controllers;
import GuestandRoomSystem.*;
import StaffSystem.Admin;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;

public class AdminDashboardController {
    private Admin admin;

    @FXML private Label nameLabel;
    @FXML private Label workingHoursLabel;
    @FXML private Label titleLabel;
    @FXML private VBox sideBar;
    @FXML private VBox centralBox;

    @FXML
    public void initialize(){
        admin= (Admin) HotelDatabase.staff.get(3);    //replace after staff login is complete
        nameLabel.setText("Name: "+ admin.getUsername());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
        workingHoursLabel.setText(("Working Hours:" + admin.getWorkingHours()));
        workingHoursLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------
// MANAGE ROOMS

    @FXML private Button manageRoomsButton;
    @FXML
    public void handleManageRooms() {
        titleLabel.setText("Manage Rooms");
        sideBar.getChildren().clear();
        Label adminLabel = new Label("ADMIN");
        adminLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        Separator separator0 = new Separator();
        Separator separator1 =new Separator();
        Label controlsLabel=new Label("Controls");
        controlsLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        Separator separator2 = new Separator();
        Separator separator3= new Separator();


        // ADD ROOM OPERATION

        Button addRoomButton = new Button("Add Room");
        addRoomButton.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px 84px;");
        addRoomButton.setPrefWidth(450);
        addRoomButton.setOnAction(e -> {
            centralBox.getChildren().clear();
            Label addRoomsLabel = new Label("Adding a New Room");
            addRoomsLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            Separator s1 = new Separator();
            centralBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            centralBox.setPadding(new javafx.geometry.Insets(20));

            //ROOM ID
            Label roomIdLabel = new Label("Room ID:");
            roomIdLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox<Integer> roomIdComboBox= new ComboBox<>();
            //roomIdComboBox.getItems().addAll(104,105,204,205,304,305,401,402,403,404,405,501,502,503,504,505);
            for(Room r: HotelDatabase.unaddedRooms){
                roomIdComboBox.getItems().add(r.getRoomid());
            }
            roomIdComboBox.setPromptText("Select a Room ID");
            roomIdComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            roomIdComboBox.setPrefWidth(300);


            //ROOM PRICE
            Separator s2 = new Separator();
            Label priceLabel = new Label("Price Per Night:");
            priceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            Slider priceSlider= new Slider();
            priceSlider.setMin(100.0);
            priceSlider.setMax(5000.0);
            priceSlider.setMajorTickUnit(50);
            priceSlider.setMinorTickCount(0);
            priceSlider.setSnapToTicks(true);
            priceSlider.setBlockIncrement(5);
            Label priceValue=new Label();
            priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    int price= (int)priceSlider.getValue();
                    priceValue.setText(Integer.toString(price) + "$");
                }
            });
            priceSlider.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");

            //ROOM TYPE
            Separator s3 = new Separator();
            Label roomTypeLabel = new Label("Room Type:");
            roomTypeLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox<RoomType> roomTypeComboBox = new ComboBox<>();
            roomTypeComboBox.getItems().addAll(HotelDatabase.roomTypes);
            roomTypeComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            roomTypeComboBox.setPromptText("Select a Room Type");
            roomTypeComboBox.setPrefWidth(300);

            //FLOOR
            Separator s4 = new Separator();
            Label roomFloorLabel = new Label("Room Floor:");
            roomFloorLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox<Integer> floorComboBox = new ComboBox<>();
            floorComboBox.getItems().addAll(1, 2, 3, 4);
            floorComboBox.setPromptText("Select The Room Floor");
            floorComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            floorComboBox.setPrefWidth(300);

            //AVAILABILITY
            Separator s5 = new Separator();
            Label availabilityLabel = new Label("Room Availability:");
            availabilityLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox<Boolean> availabilityComboBox = new ComboBox<>();
            availabilityComboBox.getItems().addAll(true, false);
            availabilityComboBox.setPromptText("Available?");
            availabilityComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            availabilityComboBox.setPrefWidth(300);

            Separator s6 = new Separator();
            Label amenityLabel = new Label("Room Amenities:");
            amenityLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ListView<Amenity> amenityListView = new ListView<>();
            amenityListView.getItems().addAll(HotelDatabase.amenities);
            amenityListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            amenityListView.setPrefHeight(100);
            amenityListView.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            amenityListView.setPrefHeight(150);
            amenityListView.setPrefWidth(300);

            Separator s7 = new Separator();
            //BUTTON TO ADD THE ROOM
            Button addBtn = new Button("ADD");
            addBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px 84px;");
            addBtn.setPrefWidth(300);
            addBtn.setOnAction(addEvent -> {
                try {
                    Integer roomId = roomIdComboBox.getValue();
                    Double roomPrice = priceSlider.getValue();
                    Integer roomFloor = floorComboBox.getValue();
                    RoomType roomType = roomTypeComboBox.getValue();
                    Boolean availability = availabilityComboBox.getValue();
                    ArrayList<Amenity> amenities = new ArrayList<>(amenityListView.getSelectionModel().getSelectedItems());

                    if (roomId == null || roomPrice == null || roomFloor == null || roomType == null || availability == null) {
                        throw new IllegalArgumentException("All data fields must be filled");
                    }

                    Room newRoom= new Room(roomId,availability, roomFloor, roomType,amenities,roomPrice);
                    admin.createRoom(newRoom);
                    Label added= new Label("Room Added Successfully!");
                    added.setStyle("-fx-font-size: 12px; -fx-text-fill: green; -fx-font-weight: bold;");
                    centralBox.getChildren().add(added);

                    floorComboBox.setValue(null);
                    roomTypeComboBox.setValue(null);
                    availabilityComboBox.setValue(null);
                    amenityListView.getSelectionModel().clearSelection();

                    roomIdComboBox.getItems().remove(roomId);
                    roomIdComboBox.setValue(null);

                } catch (IllegalArgumentException e1) {
                    Label error = new Label("ERROR: Please fill all data fields!");
                    error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                    centralBox.getChildren().add(error);
                }
            });

            ScrollPane scrollPane = new ScrollPane();
            VBox formContent= new VBox(10);
            formContent.getChildren().addAll(addRoomsLabel,s1,roomIdLabel, roomIdComboBox,s2,priceLabel,priceSlider,priceValue, s3,roomTypeLabel,roomTypeComboBox,s4,roomFloorLabel,floorComboBox, s5,availabilityLabel,availabilityComboBox,s6,amenityLabel,amenityListView,s7, addBtn);
            scrollPane.setContent(formContent);
            centralBox.getChildren().add(scrollPane);
            scrollPane.setFitToWidth(true);
            formContent.setPadding(new javafx.geometry.Insets(30));
            formContent.setSpacing(15);
            scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");
            scrollPane.setFitToWidth(true);
            formContent.setStyle("-fx-background-color: transparent;");

        });


        // VIEW ROOMS OPERATION

        Button viewRoomsBtn = new Button("View Rooms");
        viewRoomsBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        viewRoomsBtn.setPrefWidth(450);
        viewRoomsBtn.setOnAction(e->{switchToDatabaseView(e, "Room Database", HotelDatabase.rooms);});

        // DELETE ROOM OPERATION

        Button deleteRoomBtn = new Button("Delete Room");
        deleteRoomBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        deleteRoomBtn.setPrefWidth(450);
        deleteRoomBtn.setOnAction(e->{

            centralBox.getChildren().clear();
            centralBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            centralBox.setPadding(new javafx.geometry.Insets(20));
            Label deleteRoomLabel = new Label("Deleting an Existing Room");
            deleteRoomLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

            Separator s1=new Separator();

            Label roomToDelete = new Label("Room To Be Deleted:");
            roomToDelete.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox <Room> roomComboBox  = new ComboBox<>();
            roomComboBox.setPromptText("Select the room you would like to delete");
            for (Room r : HotelDatabase.rooms){
                roomComboBox.getItems().add(r);
            }
            roomComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            roomComboBox.setPrefWidth(450);

            Separator s2=new Separator();

            Button deleteBtn = new Button("DELETE");
            deleteBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
            deleteBtn.setPrefWidth(300);
            deleteBtn.setOnAction(deleteEvent->{
            try {
                if (roomComboBox.getValue()==null){
                    throw new IllegalArgumentException("No Room Selected.");
                }
                if (roomComboBox.getValue().Isavailable() == false) {
                    throw new IllegalArgumentException("Unavailable rooms cannot be deleted.");
                }

                admin.deleteRoom(roomComboBox.getValue());
                Label deleted = new Label("        Room Deleted Successfully!");
                deleted.setStyle("-fx-font-size: 12px; -fx-text-fill: green; -fx-font-weight: bold;");
                centralBox.getChildren().add(deleted);
                roomComboBox.getItems().remove(roomComboBox.getValue());

                }catch (IllegalArgumentException ex1){
                if (roomComboBox.getValue()==null){
                    Label error = new Label("        ERROR: No Room Selected!");
                    error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                    centralBox.getChildren().add(error);
                }
               else if (roomComboBox.getValue().Isavailable() == false){
                Label error = new Label("        ERROR: Unavailable Rooms Cannot be Deleted!");
                error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                centralBox.getChildren().add(error);
                }

                }
                roomComboBox.setValue(null);
            });


            ScrollPane scrollPane = new ScrollPane();
            VBox formContent= new VBox(10);
            formContent.getChildren().addAll(deleteRoomLabel,s1,roomToDelete,roomComboBox,s2,deleteBtn);
            scrollPane.setContent(formContent);
            centralBox.getChildren().add(scrollPane);
            scrollPane.setFitToWidth(true);
            formContent.setPadding(new javafx.geometry.Insets(30));
            formContent.setSpacing(15);
            scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");
            scrollPane.setFitToWidth(true);
            formContent.setStyle("-fx-background-color: transparent;");

        });

        //UPDATE ROOM

        Button updateRoomBtn = new Button("Update Room");
        updateRoomBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        updateRoomBtn.setPrefWidth(450);

         updateRoomBtn.setOnAction(e->{
             centralBox.getChildren().clear();
             centralBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
             centralBox.setPadding(new javafx.geometry.Insets(20));

             Label updateRoomLabel = new Label("Updating an Existing Room");
             updateRoomLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
              Separator s1 = new Separator();

              //UPDATE ROOM PRICE
             Label updateRoomPriceLabel = new Label("Updating a Room's Price");
             updateRoomPriceLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
             Label roomToUpdateP = new Label("Room Whose Price Will be Updated");
             roomToUpdateP.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
             ComboBox<Room>roomToUpdatePComboBox = new ComboBox<>();
             for (Room r : HotelDatabase.rooms){
                 roomToUpdatePComboBox.getItems().add(r);
             }
             roomToUpdatePComboBox.setPromptText("Select a Room to Update");
             roomToUpdatePComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
             roomToUpdatePComboBox.setPrefWidth(450);

             Separator s2 = new Separator();

             Label newPriceLabel = new Label("New Price");
             newPriceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
             Slider priceSlider= new Slider();
             priceSlider.setMin(100.0);
             priceSlider.setMax(5000.0);
             priceSlider.setMajorTickUnit(5);
             priceSlider.setMinorTickCount(0);
             priceSlider.setSnapToTicks(true);
             priceSlider.setBlockIncrement(100);
             Label priceValue=new Label();
             priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
                 @Override
                 public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                     int price= (int)priceSlider.getValue();
                     priceValue.setText(Integer.toString(price) + "$");
                 }
             });
             priceSlider.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");

             Separator s3 = new Separator();

             Button updatePriceBtn = new Button("UPDATE PRICE");
             updatePriceBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
             updatePriceBtn.setPrefWidth(450);
             updatePriceBtn.setOnAction(updatePriceEvent->{
               try{
                   if(roomToUpdatePComboBox.getValue()==null){
                       throw new IllegalArgumentException("No Room Selected");
                   } else if (roomToUpdatePComboBox.getValue().getPricepernight()==priceSlider.getValue()) {
                       throw new IllegalArgumentException("Price Not Updated");
                   }

                   admin.updateRoomPrice(roomToUpdatePComboBox.getValue(),priceSlider.getValue());
                   Label priceUpdated = new Label("        Room Price Updated Successfully!");
                   priceUpdated.setStyle("-fx-font-size: 12px; -fx-text-fill: green; -fx-font-weight: bold;");
                   centralBox.getChildren().add(priceUpdated);

               } catch (IllegalArgumentException ex) {
                   if (roomToUpdatePComboBox.getValue()==null){
                       Label error = new Label("        ERROR: No Room Selected!");
                       error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                       centralBox.getChildren().add(error);
                   }else if (roomToUpdatePComboBox.getValue().getPricepernight()==priceSlider.getValue()){
                       Label error = new Label("        ERROR: Price Must Be Updated!");
                       error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                       centralBox.getChildren().add(error);
                   }
               }

               roomToUpdatePComboBox.setValue(null);
             });

             Separator s4= new Separator();
             Separator s5= new Separator();

             // UPDATE ROOM AMENITIES
             Label updateRoomAmenitiesLabel = new Label("Updating a Room's Amenities");
             updateRoomAmenitiesLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
             Label roomToUpdateA = new Label("Room Whose Amenities Will be Updated");
             roomToUpdateA.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
             ComboBox<Room>roomToUpdateAComboBox = new ComboBox<>();
             for (Room r : HotelDatabase.rooms){
                 roomToUpdateAComboBox.getItems().add(r);
             }
            roomToUpdateAComboBox.setPrefWidth(450);
             roomToUpdateAComboBox.setPromptText("Select a Room to Update");
             roomToUpdateAComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");

             Separator s6 = new Separator();

             Label amenityLabel = new Label("New Amenities:");
             amenityLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
             ListView<Amenity> amenityListView = new ListView<>();
             amenityListView.getItems().addAll(HotelDatabase.amenities);
             amenityListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
             amenityListView.setPrefHeight(100);
             amenityListView.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
             amenityListView.setPrefHeight(150);
             amenityListView.setPrefWidth(450);

             Separator s7= new Separator();
              Button updateAmenitiesBtn =new Button("UPDATE AMENITIES");
             updateAmenitiesBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
             updateAmenitiesBtn.setPrefWidth(450);
             updateAmenitiesBtn.setOnAction(updateAmenitiesEvent->{
                 try{
                     if(roomToUpdateAComboBox.getValue()==null) {
                         throw new IllegalArgumentException("No Room Selected");
                     }
                     ArrayList<Amenity> amenities = new ArrayList<>(amenityListView.getSelectionModel().getSelectedItems());
                     admin.updateRoomAmenities(roomToUpdateAComboBox.getValue(),amenities);
                     Label amenitiesUpdated = new Label("        Amenities Updated Successfully!");
                    amenitiesUpdated.setStyle("-fx-font-size: 12px; -fx-text-fill: green; -fx-font-weight: bold;");
                     centralBox.getChildren().add(amenitiesUpdated);
                 } catch (IllegalArgumentException ex) {
                     if (roomToUpdateAComboBox.getValue()==null) {
                     Label error = new Label("        ERROR: No Room Selected!");
                     error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                     centralBox.getChildren().add(error);
                     }

                 }
                 roomToUpdateAComboBox.setValue(null);

             });
             ScrollPane scrollPane = new ScrollPane();
             VBox formContent= new VBox(10);
             formContent.getChildren().addAll(updateRoomLabel, s1, updateRoomPriceLabel, roomToUpdateP, roomToUpdatePComboBox, s2, newPriceLabel, priceSlider, priceValue,
                     s3, updatePriceBtn, s4, s5, updateRoomAmenitiesLabel, roomToUpdateA, roomToUpdateAComboBox, s6, amenityLabel, amenityListView, s7, updateAmenitiesBtn);
             scrollPane.setContent(formContent);
             centralBox.getChildren().add(scrollPane);
             scrollPane.setFitToWidth(true);
             formContent.setPadding(new javafx.geometry.Insets(30));
             formContent.setSpacing(15);
             scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");
             scrollPane.setFitToWidth(true);
             formContent.setStyle("-fx-background-color: transparent;");

         });
         Separator separator4 = new Separator();
         Separator separator5 = new Separator();

         Button backBtn = new Button("BACK");
        backBtn.setStyle("-fx-background-color: white; -fx-text-fill: #7d97b1; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        backBtn.setPrefWidth(450);
        backBtn.setOnAction(e->goBackToDashboard(e));

        Separator separator6 = new Separator();
        Separator separator7=new Separator();

        sideBar.getChildren().addAll(adminLabel, separator0,separator1,controlsLabel,separator2,separator3, addRoomButton,viewRoomsBtn,deleteRoomBtn,updateRoomBtn,separator4,separator5,backBtn,separator6,separator7);
    }

// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //MANAGE ROOM TYPES

    @FXML private Button manageRoomTypesButton;
    @FXML
    public void handleManageRoomTypes(){
        titleLabel.setText("Manage Room Types");
        sideBar.getChildren().clear();
        Label adminLabel = new Label("ADMIN");
        adminLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        Separator separator0 = new Separator();
        Separator separator1 =new Separator();
        Label controlsLabel=new Label("Controls");
        controlsLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        Separator separator2 = new Separator();
        Separator separator3= new Separator();

        //ADD ROOM TYPE OPERATION

        Button addRoomTypeBtn = new Button("Add Room Type");
        addRoomTypeBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        addRoomTypeBtn.setPrefWidth(450);
        addRoomTypeBtn.setOnAction(e->{
            centralBox.getChildren().clear();
            centralBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            centralBox.setPadding(new javafx.geometry.Insets(20));

            //ROOM TYPE NAME
            Label addRoomTypeLabel = new Label("Adding a New Room Type");
            addRoomTypeLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            Separator s1 = new Separator();
            Label rtLabel= new Label("Room Type:");
            rtLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox<String> roomTypeName = new ComboBox<>();
            for(RoomType rt : HotelDatabase.unaddedRoomTypes){
                roomTypeName.getItems().add(rt.getName());
            }
            roomTypeName.setPromptText("Select a Room ID");
            roomTypeName.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            roomTypeName.setPrefWidth(450);

            Separator s2 = new Separator();

            //ROOM TYPE CAPACITY
            Label capacityLabel = new Label("Capacity:");
            capacityLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            Slider capacitySlider= new Slider();
            capacitySlider.setMin(1);
            capacitySlider.setMax(7);
            capacitySlider.setMajorTickUnit(1);
            capacitySlider.setMinorTickCount(0);
            capacitySlider.setSnapToTicks(true);
            capacitySlider.setBlockIncrement(0);
            Label capacityVal=new Label();
            capacitySlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    int price= (int)capacitySlider.getValue();
                    capacityVal.setText(Integer.toString(price) + " Individual(s)");
                }
            });
            capacitySlider.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");

            Separator s3 = new Separator();

            //BASE PRICE
            Label basePriceLabel = new Label("Base Price: ");
            basePriceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            Slider priceSlider= new Slider();
            priceSlider.setMin(100.0);
            priceSlider.setMax(2500.0);
            priceSlider.setMajorTickUnit(5);
            priceSlider.setMinorTickCount(0);
            priceSlider.setSnapToTicks(true);
            priceSlider.setBlockIncrement(100);
            Label priceValue=new Label();
            priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    int price= (int)priceSlider.getValue();
                    priceValue.setText(Integer.toString(price) + "$");
                }
            });
            priceSlider.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");

            Separator s4 = new Separator();

            Button addBtn = new Button("ADD");
            addBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px 84px;");
            addBtn.setPrefWidth(450);
            addBtn.setOnAction(addEvent->{
                try {
                    String name = roomTypeName.getValue();
                    if (name == null) {
                        throw new IllegalArgumentException("No Room Selected");
                    }
                    int capacity = (int) capacitySlider.getValue();
                    double price = priceSlider.getValue();
                    for(RoomType rt : HotelDatabase.unaddedRoomTypes){
                        int id;
                        if(name.equals(rt.getName())){
                            id=rt.getRoomtypeid();
                            RoomType newRoomType= new RoomType(name,capacity,price,id);
                            admin.createRoomType(newRoomType);
                            Label rtAddedLabel = new Label("Room Type Added Successfully!");
                            rtAddedLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: green; -fx-font-weight: bold;");
                            Label idLabel= new Label("Room Type Id : "+ id);
                            idLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7d97b1; -fx-font-weight: bold;");
                            centralBox.getChildren().add(rtAddedLabel);
                            centralBox.getChildren().add(idLabel);
                        }
                    }

                }catch(IllegalArgumentException ex){
                            Label error = new Label("ERROR: No Room Type Selected!");
                            error.setStyle("-fx-font-size: 16px; -fx-text-fill: red; -fx-font-weight: bold;");
                            centralBox.getChildren().add(error);
                }

            });
            ScrollPane scrollPane = new ScrollPane();
            VBox formContent= new VBox(10);
            formContent.getChildren().addAll(addRoomTypeLabel, s1, rtLabel, roomTypeName, s2, capacityLabel, capacitySlider, capacityVal, s3, basePriceLabel, priceSlider, priceValue, s4, addBtn);
            scrollPane.setContent(formContent);
            centralBox.getChildren().add(scrollPane);
            scrollPane.setFitToWidth(true);
            formContent.setPadding(new javafx.geometry.Insets(30));
            formContent.setSpacing(15);
            scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");
            scrollPane.setFitToWidth(true);
            formContent.setStyle("-fx-background-color: transparent;");

        });

        // VIEW ROOM TYPES

        Button viewRoomTypesBtn = new Button("View Room Types");
        viewRoomTypesBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        viewRoomTypesBtn.setPrefWidth(450);
        viewRoomTypesBtn.setOnAction(viewEvent->{switchToDatabaseView(viewEvent, " Viewing All Room Types ", HotelDatabase.roomTypes);});

        //DELETE ROOM TYPES
        Button deleteRoomTypeBtn = new Button("Delete Room Type");
        deleteRoomTypeBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        deleteRoomTypeBtn.setPrefWidth(450);
        deleteRoomTypeBtn.setOnAction(e->{

            centralBox.getChildren().clear();
            centralBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            centralBox.setPadding(new javafx.geometry.Insets(20));
            Label deleteRoomLabel = new Label("Deleting an Existing Room Type");
            deleteRoomLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

            Separator s1=new Separator();

            Label roomTypeToDelete = new Label("Room Type To Be Deleted:");
            roomTypeToDelete.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
            ComboBox <RoomType> roomTypeComboBox  = new ComboBox<>();
            roomTypeComboBox.setPromptText("Select the room type you would like to delete");
            for (RoomType rt : HotelDatabase.roomTypes){
                roomTypeComboBox.getItems().add(rt);
            }
            roomTypeComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
            roomTypeComboBox.setPrefWidth(450);

            Separator s2=new Separator();

            Button deleteBtn = new Button("DELETE");
            deleteBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
            deleteBtn.setPrefWidth(300);
            deleteBtn.setOnAction(deleteEvent->{
                try {
                    if (roomTypeComboBox.getValue()==null){
                        throw new IllegalArgumentException("No Room Type Selected.");
                    }


                    admin.deleteRoomTypes(roomTypeComboBox.getValue());
                    Label deleted = new Label("Room Type Deleted Successfully!");
                    deleted.setStyle("-fx-font-size: 16px; -fx-text-fill: green; -fx-font-weight: bold;");
                    centralBox.getChildren().add(deleted);
                    roomTypeComboBox.getItems().remove(roomTypeComboBox.getValue());

                }catch (IllegalArgumentException ex1){
                    if (roomTypeComboBox.getValue()==null){
                        Label error = new Label("ERROR: No Room Type Selected!");
                        error.setStyle("-fx-font-size: 16px; -fx-text-fill: red; -fx-font-weight: bold;");
                        centralBox.getChildren().add(error);
                    }
                }
                roomTypeComboBox.setValue(null);
            });


            ScrollPane scrollPane = new ScrollPane();
            VBox formContent= new VBox(10);
            formContent.getChildren().addAll(deleteRoomLabel,s1,roomTypeToDelete,roomTypeComboBox,s2,deleteBtn);
            scrollPane.setContent(formContent);
            centralBox.getChildren().add(scrollPane);
            scrollPane.setFitToWidth(true);
            formContent.setPadding(new javafx.geometry.Insets(30));
            formContent.setSpacing(15);
            scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");
            scrollPane.setFitToWidth(true);
            formContent.setStyle("-fx-background-color: transparent;");

        });


        //UPDATE ROOM TYPES
        Button updateRoomTypeBtn = new Button("Update Room Type");
        updateRoomTypeBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
       updateRoomTypeBtn.setPrefWidth(450);
        updateRoomTypeBtn.setOnAction(e-> {
                    //UPDATE PRICE
                     centralBox.getChildren().clear();
                     centralBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
                     centralBox.setPadding(new javafx.geometry.Insets(20));
                    Label updateRoomPriceLabel = new Label("Updating a Room Types's Price");
                    updateRoomPriceLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
                    Separator s1= new Separator();
                    Label roomTypeToUpdateP = new Label("Room Type Whose Price Will be Updated");
                    roomTypeToUpdateP.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
                    ComboBox<RoomType> rtToUpdatePComboBox = new ComboBox<>();
                    for (RoomType rt : HotelDatabase.roomTypes) {
                        rtToUpdatePComboBox.getItems().add(rt);
                    }
                    rtToUpdatePComboBox.setPromptText("Select a Room Type to Update");
                    rtToUpdatePComboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");
                    rtToUpdatePComboBox.setPrefWidth(450);

                    Separator s2 = new Separator();

                    Label newPriceLabel = new Label("New Price");
                    newPriceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");
                    Slider priceSlider = new Slider();
                    priceSlider.setMin(100.0);
                    priceSlider.setMax(2500.0);
                    priceSlider.setMajorTickUnit(5);
                    priceSlider.setMinorTickCount(0);
                    priceSlider.setSnapToTicks(true);
                    priceSlider.setBlockIncrement(100);
                    Label priceValue = new Label();
                    priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                            int price = (int) priceSlider.getValue();
                            priceValue.setText(Integer.toString(price) + "$");
                        }
                    });
                    priceSlider.setStyle("-fx-font-size: 16px; -fx-background-color: #ecf0f1;");

                    Separator s3 = new Separator();

                    Button updatePriceBtn = new Button("UPDATE PRICE");
                    updatePriceBtn.setStyle("-fx-background-color: #7d97b1; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
                    updatePriceBtn.setPrefWidth(450);
                    updatePriceBtn.setOnAction(updatePriceEvent -> {
                        try {
                            if (rtToUpdatePComboBox.getValue() == null) {
                                throw new IllegalArgumentException("No Room Selected");
                            } else if (rtToUpdatePComboBox.getValue().getBaseprice() == priceSlider.getValue()) {
                                throw new IllegalArgumentException("Price Not Updated");
                            }

                            admin.updateRoomTypePrice(rtToUpdatePComboBox.getValue(), priceSlider.getValue());
                            Label priceUpdated = new Label("        Room Price Updated Successfully!");
                            priceUpdated.setStyle("-fx-font-size: 12px; -fx-text-fill: green; -fx-font-weight: bold;");
                            centralBox.getChildren().add(priceUpdated);

                        } catch (IllegalArgumentException ex) {
                            if (rtToUpdatePComboBox.getValue() == null) {
                                Label error = new Label("        ERROR: No Room Selected!");
                                error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                                centralBox.getChildren().add(error);
                            } else if (rtToUpdatePComboBox.getValue().getBaseprice() == priceSlider.getValue()) {
                                Label error = new Label("        ERROR: Price Must Be Updated!");
                                error.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold;");
                                centralBox.getChildren().add(error);
                            }
                        }

                        rtToUpdatePComboBox.setValue(null);
                    });
            ScrollPane scrollPane = new ScrollPane();
            VBox formContent= new VBox(10);
            formContent.getChildren().addAll(updateRoomPriceLabel,s1, roomTypeToUpdateP, rtToUpdatePComboBox, s2, newPriceLabel, priceSlider, priceValue,
                    s3, updatePriceBtn);
            scrollPane.setContent(formContent);
            centralBox.getChildren().add(scrollPane);
            scrollPane.setFitToWidth(true);
            formContent.setPadding(new javafx.geometry.Insets(30));
            formContent.setSpacing(15);
            scrollPane.setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: transparent; -fx-focus-color: transparent;");
            scrollPane.setFitToWidth(true);
            formContent.setStyle("-fx-background-color: transparent;");


        });

        Separator separator4 = new Separator();
        Separator separator5=new Separator();

        Button backBtn = new Button("BACK");
        backBtn.setStyle("-fx-background-color: white; -fx-text-fill: #7d97b1; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8px ;");
        backBtn.setPrefWidth(450);
        backBtn.setOnAction(e->goBackToDashboard(e));

        Separator separator6 = new Separator();
        Separator separator7=new Separator();

        sideBar.getChildren().addAll(adminLabel, separator0,separator1,controlsLabel,separator2,separator3,addRoomTypeBtn,viewRoomTypesBtn,deleteRoomTypeBtn,updateRoomTypeBtn, separator4, separator5,backBtn,separator6,separator7);
    }


    @FXML private Button manageAmenitiesButton;

    @FXML
    private Button registerStaffButton;


    //VIEW GUESTS
    //Mohamed
    @FXML private Button viewGuestsButton;

    @FXML
    public void handleViewGuests(javafx.event.ActionEvent event) {
            switchToDatabaseView(event, "Guest Database", HotelDatabase.guests);
    }

    //VIEW RESERVATIONS
    //Mohamed
    @FXML private Button viewReservationsButton;
    @FXML
    public void handleViewReservations(javafx.event.ActionEvent event) {
        switchToDatabaseView(event, "Reservation Database", HotelDatabase.reservations);
    }

    // VIEW STAFF
    //Mohamed
    @FXML private Button viewStaffButton;
    @FXML
    public void handleViewStaff(javafx.event.ActionEvent event){
        switchToDatabaseView(event, "Staff Database", HotelDatabase.staff);
    }

    // VIEW INVOICES
    //Mohamed
    @FXML private Button viewInvoicesButton;
    @FXML
    public void handleViewInvoices(javafx.event.ActionEvent event)
    {switchToDatabaseView(event, "Bill Database", HotelDatabase.bills);}

    @FXML
    public void handleManageAmenities(){}

    @FXML
    public void handleRegisterStaff(){}

    //Mohamed
    private void switchToDatabaseView(javafx.event.ActionEvent event, String title, java.util.List<?> databaseList) {
        Label lblTitle = new Label(title);
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button btnBack = new Button("Back to Dashboard");
        btnBack.setStyle("-fx-background-color:#7d97b1; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8px 15px;");
        btnBack.setOnAction(e -> goBackToDashboard(e));

        HBox header = new HBox(20);
        header.getChildren().add(btnBack);
        header.getChildren().add(lblTitle);
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15px; -fx-alignment: center-left;");

        ListView<String> listView = new ListView<>();
        if (databaseList.isEmpty()) {
            listView.getItems().add("No records currently found in the system.");
        } else {
            // list el data
            for (Object item : databaseList) {
                listView.getItems().add(item.toString());
            }
        }

        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setCenter(listView);

        Scene newScene = new Scene(layout,1250,750);

        try {
            String cssPath = getClass().getResource("/GUI/CSS/styles.css").toExternalForm();
            newScene.getStylesheets().add(cssPath);
        } catch (NullPointerException ex) {
            System.out.println("CSS file not found ");
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
    }

    //Mohamed
    private void switchScene(javafx.event.ActionEvent event, BorderPane layout) {
        Scene newScene = new Scene(layout);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
    }

    //Mohamed
    private void goBackToDashboard(javafx.event.ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/GUI/FXML/AdminDashboard.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
        }
    }
}

