package Controllers;

import java.awt.event.*;
import Views.*;

/**
 * Controller - Reservation Overview
 *
 */
public class ReservationOverviewController implements ActionListener {
	ReservationOverview window;
	
	/**
	 * constructor
	 * @param window the window this object will be assigned with
	 */
	public ReservationOverviewController(ReservationOverview window) {
		window.addNewReservationListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		new AddEditReservation();
	}
}
