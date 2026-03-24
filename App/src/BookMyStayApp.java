/**
 * Custom Exception for Hotel Domain errors.
 */
class BookingValidationException extends Exception {
    public BookingValidationException(String message) {
        super(message);
    }
}