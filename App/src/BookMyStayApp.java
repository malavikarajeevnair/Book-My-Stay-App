import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Search Service: Handles read-only access to room data.
 * Does not modify the inventory state.
 */
class RoomSearchService {
    private RoomInventory inventory;
    private Map<String, Room> roomTemplates; // Stores room details (Price, Description)

    public RoomSearchService(RoomInventory inventory, Map<String, Room> roomTemplates) {
        this.inventory = inventory;
        this.roomTemplates = roomTemplates;
    }

    /**
     * Finds and displays only the rooms that have a count > 0.
     */
    public void searchAvailableRooms() {
        System.out.println("\n--- Searching for Available Rooms ---");
        boolean found = false;

        // Iterate through the inventory to find available types
        for (String type : inventory.getAllRoomTypes()) {
            int count = inventory.getAvailableCount(type);

            if (count > 0) {
                Room details = roomTemplates.get(type);
                System.out.println("-> " + type + " | Price: $" + details.pricePerNight +
                        " | Left: " + count + " | " + details.getAvailabilityStatus());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no rooms are currently available.");
        }
    }
}