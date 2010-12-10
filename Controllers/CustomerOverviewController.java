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
 *
 */
public class CustomerOverviewController {
	CustomerOverview window;
	List<Map<String, Object>> customers;
	
	public CustomerOverviewController(CustomerOverview window) {
		this.window = window;
		
		window.addTableMouseListener(new TableClickListener());
		window.addSearchListener(new SearchListener());
		
		Customer customerModel = new Customer();
		customers = customerModel.list();
		window.setValues(customers);
	}
	
	private class SearchListener implements ActionListener {
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
	
	private class TableClickListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int row = window.getSelectedRow();
				int column = window.getSelectedColumn();
				new EditCustomerController(new EditCustomer((Integer)customers.get(row).get("id")));
			}
		}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
