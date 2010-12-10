package Controllers;

import Views.EditCustomer;
import java.awt.event.*;
import Models.Customer;
import Models.Reservation;
import java.util.Map;
import java.util.List;
import Models.Car;
import javax.swing.JOptionPane;

/**
 * Controller - Edit Customer
 */
public class EditCustomerController {
	EditCustomer window;
	
	/**
	 * constructor, sets up the interface
	 * @param window the window this object will be assigned with
	 */
	public EditCustomerController(EditCustomer window) {
		this.window = window;
		
		window.addSaveListener(new SaveListener());
		window.addCancelListener(new CancelListener());
		
		Customer customerModel = new Customer();
		
		Map<String, Object> customer = customerModel.read(window.getCustomerId());
		
		window.setPhoneString("" + customer.get("phone"));
		window.setNameString("" + customer.get("name"));
		
		Reservation reservationModel = new Reservation();
		Car carModel = new Car();
		
		List<Map<String, Object>> reservations = reservationModel.list(window.getCustomerId());
		for (Map<String, Object> object : reservations) {
			Map<String, Object> car = carModel.read((Integer)object.get("carId"));
			String licensePlate = car.get("licensePlate").toString();
			licensePlate = licensePlate.substring(0, 2) + " " +
			   licensePlate.substring(2, 4) + " " +
			   licensePlate.substring(4);
			
			object.put("carTitle", car.get("carTypeName").toString() + " (" + licensePlate + ")");
		}
		
		window.setValues(reservations);
	}
	
	
	/**
	 * class to receive actionlistener calls from the save button
	 */
	private class SaveListener implements ActionListener {
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
			
			boolean success = customerModel.update(window.getCustomerId(), window.getNameString(), phone);
			
			if (success) {
				window.dispose();
			} else {
				JOptionPane.showMessageDialog(window, "Invalid name or phone number, or phone number already exists", "Warning", JOptionPane.WARNING_MESSAGE);
			}

		}
	}
	
	
	/**
	 * class to receive actionlistener calls from the cancel button
	 */
	private class CancelListener implements ActionListener {
		/**
		 * handles the event
		 * @param e the actionlistener event object
		 */
		public void actionPerformed(ActionEvent e) {
			window.dispose();
		}
	}
}
