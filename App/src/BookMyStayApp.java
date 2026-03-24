/**
 * Project: BookMyStay - Hotel Booking Application
 * Version: 1.0.0
 * Actor: User (Command Line/IDE)
 */
public class BookMyStayApp {

    // Application Metadata
    private static final String APP_NAME = "BookMyStay";
    private static final String VERSION = "v1.0.0";

    /**
     * The JVM invokes this method to begin application execution.
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        // 1. Initializing the Application
        System.out.println("Initializing " + APP_NAME + "...");
        System.out.println("------------------------------------------------");

        // 2. Display Welcome Message and Version
        displayWelcomeHeader();

        // 3. Application logic would reside here (e.g., Room Selection, Booking)
        System.out.println("Status: System is ready for user input.");

        // 4. Termination
        terminate();
    }

    /**
     * Prints the branded welcome header to the console.
     */
    private static void displayWelcomeHeader() {
        System.out.println("************************************************");
        System.out.println("      Welcome to the " + APP_NAME + " App!      ");
        System.out.println("              Version: " + VERSION);
        System.out.println("************************************************");
    }

    /**
     * Handles clean-up and final output before the JVM exits.
     */
    private static void terminate() {
        System.out.println("------------------------------------------------");
        System.out.println("Shutting down " + APP_NAME + "...");
        System.out.println("Thank you for choosing us for your stay!");
    }
}
