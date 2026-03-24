/**
 * Represents a guest's intent to book a room.
 */
class Reservation {
    private String guestName;
    private String roomType;
    private long timestamp;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.timestamp = System.currentTimeMillis(); // Capture arrival order
    }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + " | Room: " + roomType + "]";
    }
}