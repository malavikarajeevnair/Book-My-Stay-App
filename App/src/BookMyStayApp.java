// Abstract class defining the core properties of any Hotel Room
abstract class Room {
    protected String roomType;
    protected double pricePerNight;
    protected boolean isAvailable;

    public Room(String roomType, double pricePerNight, boolean isAvailable) {
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    // Abstract method: Every subclass must implement its own description
    public abstract void displayDetails();

    public String getAvailabilityStatus() {
        return isAvailable ? "Available" : "Occupied";
    }
}