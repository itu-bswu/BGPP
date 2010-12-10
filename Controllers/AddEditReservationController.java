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
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

/**
 * Controller - Add/Edit Reservation
 *
 */
public class AddEditReservationController {
	AddEditReservation window;
	
	List<Map<String, Object>> cars;
	
	boolean isEditing;
	
	public AddEditReservationController(AddEditReservation window) {
		this.window = window;
		
		window.addCancelListener(new CancelListener());
		window.addSaveListener(new SaveListener());
		window.addDeleteListener(new DeleteListener());
		
		if (window.getReservationId() > -1) {
			isEditing = true;
			window.setIsEditing(true);
			
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
			int carId = (Integer)carObject.get("id");
			
			cars = carModel.list();
			String[] carNames = new String[cars.size()];
			int carTypeIndex = 0;
			int i;
			for (i = 0; i < cars.size(); i++) {
				String licensePlate = (String)cars.get(i).get("licensePlate");
				int typeId = (Integer)cars.get(i).get("carTypeId");
				String carType = (String)carTypeModel.read(typeId).get("name");
				
				carNames[i] = "" + carType + " (" + licensePlate + ")";
				
				if ((Integer)cars.get(i).get("id") == car) {
					carTypeIndex = i;
				}
			}
			
			window.setCarTypes(carNames);
			window.setData(customerName, Integer.toString(customerPhone), reservationStartDate, reservationEndDate, carTypeIndex);
		} else {
			isEditing = false;
			
			CarType carTypeModel = new CarType();
			
			cars = carTypeModel.list();
			String[] carTypeNames = new String[cars.size()];
			int i;
			for (i = 0; i < cars.size(); i++) {
				carTypeNames[i] = (String)cars.get(i).get("title");
			}
			
			window.setCarTypes(carTypeNames);
		}
	}
	
	private class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			window.dispose();
		}
	}
	
	private class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isEditing) {
				int reservationId = window.getReservationId();
				
				Reservation reservationModel = new Reservation();
				
				if (reservationModel.delete(reservationId)) {
					window.dispose();
				}
			}
		}
	}
	
	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isEditing) {
				Customer customerModel = new Customer();
				int customerId = customerModel.createIfNew(window.getCustomerName(), Integer.parseInt(window.getCustomerPhone()));
				
				Reservation reservationModel = new Reservation();
				
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM-yy");
				Date startDate = dateFormatter.parse(window.getStartDate(), new ParsePosition(0));
				Date endDate = dateFormatter.parse(window.getEndDate(), new ParsePosition(0));
				
				System.out.println("id: " + window.getSelectedCarTypeIndex() + ", car: " + cars.get(window.getSelectedCarTypeIndex()));
				
				int reservationId = window.getReservationId();
				int carId = (Integer)cars.get(window.getSelectedCarTypeIndex()).get("id");
				
				boolean success = reservationModel.update(reservationId, customerId, carId, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
				
				if (success) {
					window.dispose();
				}
			} else {
				Customer customerModel = new Customer();
				int customerId = customerModel.createIfNew(window.getCustomerName(), Integer.parseInt(window.getCustomerPhone()));
				int carType = (Integer)cars.get(window.getSelectedCarTypeIndex()).get("id");
				
				Reservation reservationModel = new Reservation();
				
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM-yy");
				Date startDate = dateFormatter.parse(window.getStartDate(), new ParsePosition(0));
				Date endDate = dateFormatter.parse(window.getEndDate(), new ParsePosition(0));
				
				int result = reservationModel.create(customerId, carType, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
				
				if (result > -1) {
					window.dispose();
				}
			}
		}
	}
}
