import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Booking Service: The "Processor" that allocates rooms and updates inventory.
 */
class BookingService {
    private RoomInventory inventory;
    private Set<String> assignedRoomIds; // Tracks unique IDs to prevent reuse

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.assignedRoomIds = new HashSet<>();
    }

    public void processNextRequest(BookingRequestQueue queue) {
        if (queue.isEmpty()) {
            System.out.println("No pending requests to process.");
            return;
        }

        // 1. Dequeue the request (FIFO)
        Reservation request = queue.nextRequest();
        String type = request.getRoomType();

        System.out.println("\nProcessing: " + request);

        // 2. Check Availability and 5. Decrement Inventory
        if (inventory.bookRoom(type)) {
            // 3. Generate Unique Room ID
            String roomId = "ROOM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // 4. Record the ID
            assignedRoomIds.add(roomId);

            // 6. Confirm Reservation
            System.out.println("SUCCESS: Room Allocated! ID: " + roomId);
            System.out.println("Guest " + request.getGuestName() + " is confirmed for a " + type + ".");
        } else {
            System.out.println("FAILED: No " + type + " rooms left for " + request.getGuestName() + ".");
        }
    }
}