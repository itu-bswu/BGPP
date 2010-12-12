import Views.*;
import Controllers.*;

/**
 * Main - Main class
 */
public class Main {
	public static void main(String[] args) {
		ReservationOverview window = ReservationOverview.getInstance();
		ReservationOverviewController controller = new ReservationOverviewController(window);
		window.setController(controller);
	}
}
