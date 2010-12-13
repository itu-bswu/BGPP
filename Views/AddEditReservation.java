package Views;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.awt.event.ActionListener;
import Models.*;
import com.michaelbaranov.microba.calendar.DatePicker;

/**
 * View - Add/Edit Reservation
 */
public class AddEditReservation extends JFrame {
	private int thisReservation;
	
	private JComboBox carTypeSelect;
	private JTextField customerNameInput;
	private JTextField customerPhoneInput;
	
	private DatePicker fromDatePicker;
	private DatePicker toDatePicker;
	
	public final String cancelButtonTitle = "Cancel";
	public final String saveButtonTitle = "Save";
	public final String deleteButtonTitle = "Delete";
	
	JButton cancelButton;
	JButton saveButton;
	JButton deleteButton;
	
	JLabel carTypeLabel;
	
	/**
	 * AddEditReservation constructor
	 * creates a new reservation window
	 */
	public AddEditReservation() {
		super("Reservation");
		setupInterface();
		thisReservation = -1;
	}
	
	/**
	 * AddEditReservation constructor
	 * Creates a new reservation window, prepopulated with data
	 * @param reservationID the reservation ID
	 */
	public AddEditReservation(int reservationID) {
		super("Reservation");
		setupInterface();
		thisReservation = reservationID;
	}
	
	/**
	 * sets up the interface
	 */
	private void setupInterface() {
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(panel, BorderLayout.NORTH);
		
		JPanel labelsPanel = new JPanel(new GridLayout(5, 0));
		panel.add(labelsPanel, BorderLayout.WEST);
		
		carTypeLabel = new JLabel("Car type: ");
		labelsPanel.add(carTypeLabel);
		
		JLabel fromDateLabel = new JLabel("From date: ");
		labelsPanel.add(fromDateLabel);
		
		JLabel toDateLabel = new JLabel("to date: ");
		labelsPanel.add(toDateLabel);
		
		JLabel customerNameLabel = new JLabel("Customer name: ");
		labelsPanel.add(customerNameLabel);
		
		JLabel customerPhoneLabel = new JLabel("Customer phone: ");
		labelsPanel.add(customerPhoneLabel);
		
		JPanel inputPanel = new JPanel(new GridLayout(5, 0));
		panel.add(inputPanel, BorderLayout.CENTER);
		
		carTypeSelect = new JComboBox();
		inputPanel.add(carTypeSelect);
		
		fromDatePicker = new DatePicker();
		inputPanel.add(fromDatePicker);
		
		toDatePicker = new DatePicker();
		inputPanel.add(toDatePicker);
		
		customerNameInput = new JTextField();
		inputPanel.add(customerNameInput);
		
		customerPhoneInput = new JTextField();
		inputPanel.add(customerPhoneInput);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		cancelButton = new JButton(cancelButtonTitle);
		buttonsPanel.add(cancelButton);
		
		deleteButton = new JButton(deleteButtonTitle);
		deleteButton.setVisible(false);
		buttonsPanel.add(deleteButton);
		
		saveButton = new JButton(saveButtonTitle);
		buttonsPanel.add(saveButton);
		
		this.setSize(275, 220);
		this.setVisible(true);
	}
	
	
	/**
	 * specifies wether it is editing an existing reservation or creating a new one
	 * @param isEditing true if it's an editing, false if it's a new
	 */
	public void setIsEditing(boolean isEditing) {
		if (isEditing) {
			deleteButton.setVisible(true);
			carTypeLabel.setText("Car: ");
		} else {
			deleteButton.setVisible(false);
			carTypeLabel.setText("Car type: ");
		}

	}
	
	
	/**
	 * gets the string in the name field
	 * @return the name string
	 */
	public String getCustomerName() {
		return customerNameInput.getText();
	}
	
	
	/**
	 * gets the string in the phone field
	 * @return the phone string
	 */
	public String getCustomerPhone() {
		return customerPhoneInput.getText();
	}
	
	
	/**
	 * gets the selected car type
	 * @return the selected index
	 */
	public int getSelectedCarTypeIndex() {
		return carTypeSelect.getSelectedIndex();
	}
	
	
	/**
	 * gets the string in the start date
	 * @return the start date string
	 */
	public Date getStartDate() {
		return fromDatePicker.getDate();
	}
	
	
	/**
	 * gets the string in the end date
	 * @return the end date string
	 */
	public Date getEndDate() {
		return toDatePicker.getDate();
	}
	
	
	/**
	 * sets the shown car types
	 * @param carTypes string array of car type descriptions
	 */
	public void setCarTypes(String[] carTypes) {
		for (String s : carTypes) {
			carTypeSelect.addItem(s);
		}
	}
	
	
	/**
	 * sets the data to be shown
	 * @param customerName the string to be shown in the name field
	 * @param customerPhone the string to be shown in the phone field
	 * @param fromDate the date object to be parsed and shown in the from date field
	 * @param toDate the date object to be parsed and shown in the to date field
	 * @param carType the car type index to be chosen in the car type combobox
	 */
	public void setData(String customerName, String customerPhone, Date fromDate, Date toDate, int carType) {
		
		try {
			fromDatePicker.setDate(fromDate);
			toDatePicker.setDate(toDate);
		} catch (Exception e) {}
		
		customerNameInput.setText(customerName);
		customerPhoneInput.setText(customerPhone);
		carTypeSelect.setSelectedIndex(carType);
	}
	
	
	/**
	 * get the reservation id currently being edited
	 * @return the reservation id;
	 */
	public int getReservationId() {
		return thisReservation;
	}
	
	
	/**
	 * adds an actionlistener to the cancel button
	 * @param a the actionlistener instance
	 */
	public void addCancelListener(ActionListener a) {
		cancelButton.addActionListener(a);
	}
	
	
	/**
	 * adds an actionlistener to the delete button
	 * @param a the actionlistener instance
	 */
	public void addDeleteListener(ActionListener a) {
		deleteButton.addActionListener(a);
	}
	
	
	/**
	 * adds an actionlistener to the save button
	 * @param a the actionlistener instance
	 */
	public void addSaveListener(ActionListener a) {
		saveButton.addActionListener(a);
	}
}
