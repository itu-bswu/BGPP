package Controllers;

import Views.*;
import java.awt.event.*;
import Models.Customer;

/**
 * Controller - Customer Overview
 *
 */
public class CustomerOverviewController implements MouseListener, ActionListener {
	CustomerOverview window;
	
	public CustomerOverviewController(CustomerOverview window) {
		this.window = window;
		
		window.addTableMouseListener(this);
		window.addSearchListener(this);
		
		Customer customerModel = new Customer();
		window.setValues(customerModel.list());
	}
	
	public void actionPerformed(ActionEvent e) {
		Customer customerModel = new Customer();
		int phone = 0;
		if (window.getPhoneString().equals("") == false) {
			phone = Integer.parseInt(window.getPhoneString());
		}
		window.setValues(customerModel.search(window.getNameString(), phone, "name", "ASC"));
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = window.getSelectedRow();
			int column = window.getSelectedColumn();
			new EditCustomer();
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
