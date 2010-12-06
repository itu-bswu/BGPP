package Controllers;

import java.awt.event.*;
import javax.swing.*;
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
		window.addPrevWeekListener(this);
		window.addNextWeekListener(this);
		window.addGotoListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(window.newReservationItemTitle)) {
			new AddEditReservation();
		} else if (e.getActionCommand().equals(window.customerListItemTitle)) {
			new CustomerOverviewController(new CustomerOverview());
		} else if (e.getActionCommand().equals(window.prevWeekItemTitle)) {
			window.goToWeek(window.getWeek()-1, window.getYear());
		} else if (e.getActionCommand().equals(window.nextWeekItemTitle)) {
			window.goToWeek(window.getWeek()+1, window.getYear());
		} else if (e.getActionCommand().equals(window.gotoItemTitle)) {
			Object[] weeks = new Object[52];
			
			int i;
			for (i = 0; i < weeks.length; i++) {
				weeks[i] = "" + i;
			}
			
			String result = (String)JOptionPane.showInputDialog(window, "Choose week number:", "Go to week", JOptionPane.PLAIN_MESSAGE, null, weeks, ""+window.getWeek());
			
			window.goToWeek(Integer.parseInt(result), window.getYear());
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			new AddEditReservation();
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
