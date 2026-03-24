import java.util.HashMap;
import java.util.Map;

/**
 * Manager class responsible for centralized state management.
 * Uses HashMap for O(1) retrieval of room counts.
 */
class RoomInventory {
    // Key: Room Type (String) | Value: Count (Integer)
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    // Register room types and their initial counts
    public void registerRoomType(String type, int initialCount) {
        inventory.put(type, initialCount);
    }

    // Controlled update: Reduce count when a booking happens
    public boolean bookRoom(String type) {
        if (inventory.containsKey(type) && inventory.get(type) > 0) {
            inventory.put(type, inventory.get(type) - 1);
            return true;
        }
        return false;
    }

    // Display current state
    public void displayInventory() {
        System.out.println("--- Current Room Inventory Status ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room: " + entry.getKey() + " | Available: " + entry.getValue());
        }
        System.out.println("-------------------------------------");
    }
}