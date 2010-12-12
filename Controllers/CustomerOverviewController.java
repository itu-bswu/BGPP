package Controllers;

import Views.EditCustomer;
import Controllers.EditCustomerController;
import java.awt.event.*;
import Models.Customer;
import Views.CustomerOverview;
import java.util.List;
import java.util.Map;

/**
 * Controller - Customer Overview
 */
public class CustomerOverviewController {
	CustomerOverview window;
	List<Map<String, Object>> customers;
	
	/**
	 * constructor, sets up the interface
	 * @param window the window this object with be assigned with
	 */
	public CustomerOverviewController(CustomerOverview window) {
		this.window = window;
		
		window.addTableMouseListener(new TableClickListener());
		window.addSearchListener(new SearchListener());
		
		Customer customerModel = new Customer();
		customers = customerModel.list();
		window.setValues(customers);
	}
	
	
	/**
	 * class to receive actionlistener calls from the search button
	 */
	private class SearchListener implements ActionListener {
		/**
		 * handles the event
		 * @param e the actionlistener event object
		 */
		public void actionPerformed(ActionEvent e) {
			Customer customerModel = new Customer();
			int phone = 0;
			if (window.getPhoneString().equals("") == false) {
				phone = Integer.parseInt(window.getPhoneString());
			}
			customers = customerModel.search(window.getNameString(), phone, "name", "ASC");
			window.setValues(customers);
		}
	}
	
	
	/**
	 * class to receive mouselistener calls from the table
	 */
	private class TableClickListener implements MouseListener {
		/**
		 * handles mouse click events
		 * @param e the mouselistener event object
		 */
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int row = window.getSelectedRow();
				new EditCustomerController(new EditCustomer((Integer)customers.get(row).get("id")));
			}
		}
		
		/**
		 * handles mouse entered events
		 * @param e the mouselistener event object
		 */
		public void mouseEntered(MouseEvent e) {}
		
		/**
		 * handles mouse exited events
		 * @param e the mouselistener event object
		 */
		public void mouseExited(MouseEvent e) {}
		
		/**
		 * handles mouse pressed events
		 * @param e the mouselistener event object
		 */
		public void mousePressed(MouseEvent e) {}
		
		/**
		 * handles mouse released events
		 * @param e the mouselistener event object
		 */
		public void mouseReleased(MouseEvent e) {}
	}
}
