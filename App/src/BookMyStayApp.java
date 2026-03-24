import java.util.Optional;

/**
 * Cancellation Service: Handles the logic for reversing a confirmed booking.
 */
class CancellationService {
    private RoomInventory inventory;
    private BookingHistory history;

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    /**
     * Performs a controlled rollback of a specific booking.
     */
    public boolean cancelBooking(String roomId) {
        System.out.println("\n--- Initiating Cancellation for: " + roomId + " ---");

        // 1. Validate: Does this reservation exist in history?
        Optional<ReservationRecord> recordOpt = history.getFullHistory().stream()
                .filter(r -> r.roomId.equals(roomId))
                .findFirst();

        if (recordOpt.isEmpty()) {
            System.err.println("CANCELLATION ERROR: Room ID not found in system.");
            return false;
        }

        ReservationRecord record = recordOpt.get();

        // 2. Increment Inventory (Rollback State)
        inventory.registerRoomType(record.roomType, inventory.getAvailableCount(record.roomType) + 1);

        // 3. Mark History (In this simple model, we tag or remove)
        history.markAsCancelled(roomId);

        System.out.println("SUCCESS: " + record.roomType + " has been returned to inventory.");
        return true;
    }
}