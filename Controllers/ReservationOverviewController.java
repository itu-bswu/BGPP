package Controllers;

import java.awt.event.*;
import javax.swing.*;
import Views.ReservationOverview;
import Views.CustomerOverview;
import Views.AddEditReservation;
import Controllers.AddEditReservationController;
import Models.Car;
import Models.Reservation;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Controller - Reservation Overview
 *
 */
public class ReservationOverviewController {
	ReservationOverview window;
	
	/**
	 * constructor
	 * @param window the window this object will be assigned with
	 */
	public ReservationOverviewController(ReservationOverview window) {
		this.window = window;
		
		window.addNewReservationListener(new NewReservationListener());
		window.addCustomerListListener(new CustomerListListener());
		window.addTableMouseListener(new TableMouseListener());
		window.addPrevWeekListener(new PrevWeekListener());
		window.addNextWeekListener(new NextWeekListener());
		window.addGotoListener(new GotoWeekListener());
		window.addUpdateListener(new UpdateListener());
		
		Car carModel = new Car();
		List<Map<String, Object>> carList = carModel.list();
		window.setCars(carList);
		
		Calendar currentTime = Calendar.getInstance();
		currentTime.setFirstDayOfWeek(Calendar.MONDAY);
		window.goToWeek(currentTime.get(Calendar.WEEK_OF_YEAR)-1, currentTime.get(Calendar.YEAR));
		
		loadReservations();
	}
	
	
	public void loadReservations() {
		Calendar dates = Calendar.getInstance();
		dates.setFirstDayOfWeek(Calendar.MONDAY);
		dates.clear();
		dates.set(Calendar.YEAR,  window.getYear());
		dates.set(Calendar.WEEK_OF_YEAR, window.getWeek()+1);
		dates.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		Date weekStartDate = dates.getTime();
		dates.add(Calendar.DATE, 7);
		Date weekEndDate = dates.getTime();
		
		Reservation reservationModel = new Reservation();
		List<Map<String, Object>> reservationsList = reservationModel.list(new java.sql.Date(weekStartDate.getTime()), new java.sql.Date(weekEndDate.getTime()));
		window.setReservations(reservationsList);
	}
	
	
	private class TableMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int reservationId = window.getReservationForSelectedCell();
				if (reservationId > -1) {
					new AddEditReservationController(new AddEditReservation(reservationId));
				}
			}
		}
		
		public void mouseEntered(MouseEvent e) {}
		
		public void mouseExited(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) {}
		
		public void mouseReleased(MouseEvent e) {}
	}
	
	private class NewReservationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new AddEditReservationController(new AddEditReservation());
		}
	}
	
	private class CustomerListListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new CustomerOverviewController(new CustomerOverview());
		}
	}
	
	private class PrevWeekListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			window.goToWeek(window.getWeek()-1, window.getYear());
			loadReservations();
		}
	}
	
	private class NextWeekListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			window.goToWeek(window.getWeek()+1, window.getYear());
			loadReservations();
		}
	}
	
	private class GotoWeekListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object[] weeks = new Object[52];
			
			int i;
			for (i = 0; i < weeks.length; i++) {
				weeks[i] = "" + (i+1);
			}
			
			String result = (String)JOptionPane.showInputDialog(window, "Choose week number:", "Go to week", JOptionPane.PLAIN_MESSAGE, null, weeks, ""+window.getWeek());
			
			window.goToWeek(Integer.parseInt(result), window.getYear());
			loadReservations();
		}
	}
	
	private class UpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loadReservations();
		}
	}
}
