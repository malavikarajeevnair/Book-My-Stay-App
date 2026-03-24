import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Project: BookMyStay - Durable & Concurrent Edition
 * Goal: Demonstrate Persistence (Durable State) and Thread-Safety.
 */

// 1. THE DURABLE RESOURCE: Must implement Serializable
class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L; // Version control for serialization

    // Use a Map to store room counts
    private Map<String, Integer> inventory = new ConcurrentHashMap<>();

    public void registerRoomType(String type, int count) {
        inventory.put(type, count);
    }

    /**
     * Synchronized to prevent race conditions during concurrent bookings.
     */
    public synchronized boolean bookRoom(String type, String guestName) {
        int currentCount = inventory.getOrDefault(type, 0);
        if (currentCount > 0) {
            System.out.println("[Process] " + guestName + " is securing 1 " + type + " room...");
            inventory.put(type, currentCount - 1);
            return true;
        }
        return false;
    }

    public int getCount(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// 2. THE PERSISTENCE SERVICE: Handles Disk I/O
class PersistenceService {
    private static final String FILE_NAME = "hotel_state.ser";

    public void save(RoomInventory data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(data);
            System.out.println(">>> [STORAGE] System state saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Save Error: " + e.getMessage());
        }
    }

    public RoomInventory load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (RoomInventory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Load Error: " + e.getMessage());
            return null;
        }
    }
}

// 3. THE MAIN APPLICATION
public class Book