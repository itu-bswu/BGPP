package Controllers;

import Views.EditCustomer;
import java.awt.event.*;
import Models.Customer;
import Models.Reservation;
import java.util.Map;
import java.util.List;
import Models.Car;

/**
 * Controller - Edit Customer
 *
 */
public class EditCustomerController implements ActionListener {
	EditCustomer window;
	
	public EditCustomerController(EditCustomer window) {
		this.window = window;
		
		window.addSaveListener(this);
		window.addCancelListener(this);
		
		Customer customerModel = new Customer();
		
		Map<String, Object> customer = customerModel.read(window.getCustomerId());
		
		window.setPhoneString("" + customer.get("phone"));
		window.setNameString("" + customer.get("name"));
		
		Reservation reservationModel = new Reservation();
		Car carModel = new Car();
		
		List<Map<String, Object>> reservations = reservationModel.list(window.getCustomerId());
		for (Map<String, Object> object : reservations) {
			object.put("carTitle", carModel.read((Integer)object.get("carId")).get("licensePlate"));
		}
		window.setValues(reservations);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(window.saveButtonTitle)) {
			Customer customerModel = new Customer();
			
			int phone = 0;
			if (window.getPhoneString().equals("") == false) {
				phone = Integer.parseInt(window.getPhoneString());
			}
			
			boolean success = customerModel.update(window.getCustomerId(), window.getNameString(), phone);
			
			if (success) {
				window.dispose();
			}
		} else if (e.getActionCommand().equals(window.cancelButtonTitle)) {
			window.dispose();
		}
	}
}
