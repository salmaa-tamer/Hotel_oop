package GUI.Controllers;

import GUI.SceneManager;
import GuestandRoomSystem.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;


public class GuestDashboardController {
    @FXML
    private Label welcomelabel;
    @FXML
    private Label balancelabel;
    @FXML
    private TableView<Reservation> reservationtable;
    @FXML
    private TableColumn<Reservation, String> roomcol;
    @FXML
    private TableColumn<Reservation, String> checkindatecol;
    @FXML
    private TableColumn<Reservation, String> checkoutdatecol;
    @FXML
    private TableColumn<Reservation, String> statuscol;
    @FXML
    private Label usernamelabel;
    @FXML
    private Label doblabel;
    @FXML
    private Label addresslabel;
    @FXML
    private Label balanceprofilelabel;
    @FXML
    private Label genderlabel;
    @FXML
    private Label roompreflabel;
    @FXML
    private TableView<Room> roomtable;
    @FXML
    private TableColumn<Room, String> roomidcol;
    @FXML
    private TableColumn<Room, String> availabilitycol;
    @FXML
    private TableColumn<Room, String> floorcol;
    @FXML
    private TableColumn<Room, String> amenitiescol;
    @FXML
    private TableColumn<Room, String> pricecol;
    @FXML
    private Slider priceslider;
    @FXML
    private Label pricelabel;
    @FXML
    private ComboBox<String> roomtypefilter;
    @FXML
    private ComboBox<String> Amenityfilter;
    @FXML
    private VBox dashboardpanel;
    @FXML
    private VBox Guestprofilepanel;
    @FXML
    private VBox AvailableRoomspanel;
    @FXML
    private Label messagelabel;


    @FXML
    public void initialize() {
        currentguest = HotelDatabase.guests.get(0);

        setupTopBar();
        setupDashboardTable();
        setupRoomsPanel();

        dashboardpanel.setVisible(true);
        Guestprofilepanel.setVisible(false);
        AvailableRoomspanel.setVisible(false);
    }                                //will be removed after Hana's part

    private Guest currentguest;

    public void setguest(Guest guest) {
        this.currentguest = guest;
        setupTopBar();
        loadprofile();
        setupDashboardTable();
        setupRoomsPanel();

    }

    public void setupTopBar() {
        welcomelabel.setText("Welcome, " + currentguest.getUsername());
        balancelabel.setText("Balance: $" + currentguest.getBalance());
    }

    public void loadprofile() {
        usernamelabel.setText(currentguest.getUsername());
        doblabel.setText(currentguest.getDateOfBirth().toString());
        balanceprofilelabel.setText("$" + currentguest.getBalance());
        addresslabel.setText(currentguest.getAddress());
        genderlabel.setText(currentguest.getGender().toString());
        roompreflabel.setText(currentguest.getRoomPreference());
    }


    public void setupDashboardTable() {
        roomcol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getRoom().getRoomid())));

        statuscol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getStatus().toString()));

        checkindatecol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCheckInDate().toString()));

        checkoutdatecol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCheckOutDate().toString()));

        ObservableList<Reservation> list = FXCollections.observableArrayList();

        for (Reservation r : HotelDatabase.reservations) {
            boolean isguest = currentguest.getUsername().equals(r.getGuest().getUsername());
            boolean isactive = r.getStatus() == ReservationStatus.PENDING || r.getStatus() == ReservationStatus.CONFIRMED;
            if (isactive & isguest) {
                list.add(r);
            }

        }
        reservationtable.setItems(list);
    }

    public void applyfilters() {
        String selectedType = roomtypefilter.getValue();
        String selectedAmenity = Amenityfilter.getValue();
        double maxPrice = priceslider.getValue();

        ObservableList<Room> filtered = FXCollections.observableArrayList();

        for (Room room : HotelDatabase.rooms) {
            if (!room.Isavailable()) continue;

            boolean typeMatch = selectedType == null
                    || selectedType.equals("All")
                    || room.getRoomtype().getName().equals(selectedType);

            boolean priceMatch = room.getPricepernight() <= maxPrice;

            boolean amenityMatch = selectedAmenity == null
                    || selectedAmenity.equals("All");
            if (!amenityMatch) {
                for (Amenity a : room.getAmenities()) {
                    if (a.getName().equals(selectedAmenity)) {
                        amenityMatch = true;
                        break;
                    }
                }
            }

            if (typeMatch && priceMatch && amenityMatch) {
                filtered.add(room);
            }
        }
        roomtable.setItems(filtered);

    }

    public void setupRoomsPanel() {//
        // fill room type combobox
        ObservableList<String> types = FXCollections.observableArrayList("All");
        for (RoomType rt : HotelDatabase.roomTypes) {
            types.add(rt.getName());
        }
        roomtypefilter.setItems(types);
        roomtypefilter.setValue("All");

        // fill amenity combobox
        ObservableList<String> amenities = FXCollections.observableArrayList("All");
        for (Amenity a : HotelDatabase.amenities) {
            amenities.add(a.getName());
        }
        Amenityfilter.setItems(amenities);
        Amenityfilter.setValue("All");


        priceslider.setMin(0);
        priceslider.setMax(5000);
        priceslider.setValue(5000);
        pricelabel.setText("Max Price: $5000");

        priceslider.valueProperty().addListener((obs, oldVal, newVal) -> {
            pricelabel.setText("Max Price: $" + String.format("%.0f", newVal));
            applyfilters();
        });
        roomtypefilter.valueProperty().addListener((obs, o, n) -> applyfilters());
        Amenityfilter.valueProperty().addListener((obs, o, n) -> applyfilters());

        roomidcol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getRoomid())));

        availabilitycol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().Isavailable() ? "YES" : "NO"));

        floorcol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getFloor())));

        amenitiescol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getAmenities().stream()
                                .map(Amenity::getName)
                                .collect(Collectors.joining(", "))));

        pricecol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getPricepernight())));

        applyfilters(); // load rooms initially

        //Button bookbutton =new Button("Book Now");

    }

    @FXML
    public void showDashboardPanel() {
        dashboardpanel.setVisible(true);
        Guestprofilepanel.setVisible(false);
        AvailableRoomspanel.setVisible(false);
    }

    @FXML
    public void showProfilePanel() {
        loadprofile();
        dashboardpanel.setVisible(false);
        Guestprofilepanel.setVisible(true);
        AvailableRoomspanel.setVisible(false);
    }

    @FXML
    public void showRoomsPanel() {
        dashboardpanel.setVisible(false);
        Guestprofilepanel.setVisible(false);
        AvailableRoomspanel.setVisible(true);
    }

    @FXML
    public void goToReservations() {
        // go to salma's My Reservations screen
        System.out.println("Going to reservations...");
       /* MyReservationsController controller =
                SceneManager.navigateToWithController("MyReservations.fxml");
        controller.setGuest(currentguest);*/
    }

    @FXML
    public void goToCheckout() {
        // go to salma's Checkout screen
        System.out.println("Going to checkout...");
       /* CheckoutController controller =
                SceneManager.navigateToWithController("Checkout.fxml");
        controller.setGuest(currentguest);*/
    }

    // go to salma's Make Reservation screen
    @FXML
    public void onBookNowClicked() {
        try {
            Room selectedRoom = roomtable.getSelectionModel().getSelectedItem();

            if (selectedRoom == null) {
                throw new Exception("Please select a room first!");
            }

            System.out.println("Room selected: " + selectedRoom.getRoomid());

        } catch (Exception e) {
            messagelabel.setText(e.getMessage());
        }
    }
    /*  MakeReservationController controller =
                    SceneManager.navigateToWithController("MakeReservation.fxml");
            controller.setGuest(currentguest);
            controller.setRoom(selectedRoom);*/
}


