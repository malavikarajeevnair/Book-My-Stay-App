import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Booking History: Maintains a persistent record of all successful allocations.
 * Follows insertion order to preserve the audit trail.
 */
class BookingHistory {
    private List<ReservationRecord> history;

    public BookingHistory() {
        this.history = new ArrayList<>();
    }

    public void recordBooking(ReservationRecord record) {
        history.add(record);
    }

    public List<ReservationRecord> getFullHistory() {
        // Return an unmodifiable list to protect history from accidental changes
        return Collections.unmodifiableList(history);
    }
}

/**
 * A data-transfer object representing a completed transaction.
 */
class ReservationRecord {
    String guestName;
    String roomType;
    String roomId;
    double totalCost;

    public ReservationRecord(String name, String type, String id, double cost) {
        this.guestName = name;
        this.roomType = type;
        this.roomId = id;
        this.totalCost = cost;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-12s | %-15s | $%-8.2f |",
                guestName, roomType, roomId, totalCost);
    }
}