import Views.*;
import Controllers.*;

/**
 * Main - Main class
 *
 */
public class Main {
	public static void main(String[] args) {
		ReservationOverview window = new ReservationOverview();
		ReservationOverviewController controller = new ReservationOverviewController(window);
	}
}
