package Controllers;

import Views.AddEditReservation;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Models.Reservation;
import Models.CarType;
import Models.Car;
import Models.Customer;
import java.util.Map;
import java.util.Date;
import java.util.List;

/**
 * Controller - Add/Edit Reservation
 *
 */
public class AddEditReservationController implements ActionListener {
	AddEditReservation window;
	
	public AddEditReservationController(AddEditReservation window) {
		this.window = window;
		
		window.addCancelListener(this);
		window.addSaveListener(this);
		
		Reservation reservationModel = new Reservation();
		Car carModel = new Car();
		CarType carTypeModel = new CarType();
		Customer customerModel = new Customer();
		
		Map<String, Object> reservation = reservationModel.read(window.getReservationId());
		Date reservationStartDate = (Date)reservation.get("startDate");
		Date reservationEndDate = (Date)reservation.get("endDate");
		int customer = (Integer)reservation.get("customer");
		int car = (Integer)reservation.get("car");
		
		Map<String, Object> customerObject = customerModel.read(customer);
		String customerName = (String)customerObject.get("name");
		int customerPhone = (Integer)customerObject.get("phone");
		
		Map<String, Object> carObject = carModel.read(car);
		int carType = (Integer)carObject.get("typeId");
		
		List<Map<String, Object>> carTypes = carTypeModel.list();
		String[] carTypeNames = new String[carTypes.size()];
		int carTypeIndex = 0;
		int i;
		for (i = 0; i < carTypes.size(); i++) {
			carTypeNames[i] = (String)carTypes.get(i).get("title");
			
			if ((Integer)carTypes.get(i).get("id") == carType) {
				carTypeIndex = i;
			}
		}
		
		window.setCarTypes(carTypeNames);
		window.setData(customerName, Integer.toString(customerPhone), reservationStartDate, reservationEndDate, carTypeIndex);
	}
	
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(window.cancelButtonTitle)) {
			window.dispose();
		}
	}
}
