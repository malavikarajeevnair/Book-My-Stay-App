import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: BookMyStay - Concurrent Edition
 * Goal: Demonstrate Thread-Safety and Synchronization in a Hotel Domain.
 */

// 1. THE SHARED RESOURCE: Thread-Safe Room Inventory
class RoomInventory {
    // ConcurrentHashMap provides thread-safe bucket access
    private Map<String, Integer> inventory = new ConcurrentHashMap<>();

    public void registerRoomType(String type, int count) {
        inventory.put(type, count);
    }

    /**
     * CRITICAL SECTION:
     * The 'synchronized' keyword ensures that only one thread can
     * check availability and decrement the count at any given time.
     */
    public synchronized boolean bookRoom(String type, String guestName) {
        int currentCount = inventory.getOrDefault(type, 0);

        System.out.println("[Checking] " + guestName + " sees " + currentCount + " " + type + " rooms.");

        if (currentCount > 0) {
            // Artificial delay to simulate network latency or database processing
            // This exposes race conditions if 'synchronized' is removed!
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            inventory.put(type, currentCount - 1);
            return true;
        }
        return false;
    }

    public int getCount(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// 2. THE ACTOR: Concurrent Booking Request (Runnable)
class BookingRequest implements Runnable {
    private String guestName;
    private String roomType;
    private RoomInventory inventory;

    public BookingRequest(String name, String type, RoomInventory inv) {
        this.guestName = name;
        this.roomType = type;
        this.inventory = inv;
    }

    @Override
    public void run() {
        if (inventory.bookRoom(roomType, guestName)) {
            System.out.println(">>> [SUCCESS] " + guestName + " successfully booked the " + roomType + " room!");
        } else {
            System.out.println("XXX [FAILED] " + guestName + " - Sorry, " + roomType +