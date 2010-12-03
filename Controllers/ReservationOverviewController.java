package Controllers;

import java.awt.event.*;
import Views.*;

/**
 * Controller - Reservation Overview
 *
 */
public class ReservationOverviewController implements ActionListener, MouseListener {
	ReservationOverview window;
	
	/**
	 * constructor
	 * @param window the window this object will be assigned with
	 */
	public ReservationOverviewController(ReservationOverview window) {
		this.window = window;
		
		window.addNewReservationListener(this);
		window.addCustomerListListener(this);
		window.addTableMouseListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(window.getNewReservationItemTitle())) {
			new AddEditReservation();
		} else if (e.getActionCommand().equals(window.getCustomerListItemTitle())) {
			new CustomerOverviewController(new CustomerOverview());
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = window.getSelectedRow();
			int column = window.getSelectedColumn();
			System.out.println("x: " + row + ", y: " + column);
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
}
