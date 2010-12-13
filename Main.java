import Views.*;
import Controllers.*;
import javax.swing.JFrame;

/**
 * Main - Main class
 */
public class Main {
	public static void main(String[] args) {
		ReservationOverview window = ReservationOverview.getInstance();
		ReservationOverviewController controller = new ReservationOverviewController(window);
		window.setController(controller);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
