package exceptions;

public class InvalidReservationStatus extends RuntimeException {
    public InvalidReservationStatus(String message) {
        super(message);
    }
}
