package Views;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.awt.event.ActionListener;
import Models.*;
import java.text.SimpleDateFormat;

/**
 * View - Add/Edit Reservation
 *
 */
public class AddEditReservation extends JFrame {
	private int thisReservation;
	
	private JComboBox carTypeSelect;
	private JTextField fromDateInput;
	private JTextField toDateInput;
	private JTextField customerNameInput;
	private JTextField customerPhoneInput;
	
	public final String cancelButtonTitle = "Cancel";
	public final String saveButtonTitle = "Save";
	
	JButton cancelButton;
	JButton saveButton;
	
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
		
		JLabel carTypeLabel = new JLabel("Car type: ");
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
		
		fromDateInput = new JTextField();
		inputPanel.add(fromDateInput);
		
		toDateInput = new JTextField();
		inputPanel.add(toDateInput);
		
		customerNameInput = new JTextField();
		inputPanel.add(customerNameInput);
		
		customerPhoneInput = new JTextField();
		inputPanel.add(customerPhoneInput);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		cancelButton = new JButton(cancelButtonTitle);
		buttonsPanel.add(cancelButton);
		
		saveButton = new JButton(saveButtonTitle);
		buttonsPanel.add(saveButton);
		
		this.setSize(275, 220);
		this.setVisible(true);
	}
	
	
	public void setCarTypes(String[] carTypes) {
		for (String s : carTypes) {
			carTypeSelect.addItem(s);
		}
	}
	
	
	public void setData(String customerName, String customerPhone, Date fromDate, Date toDate, int carType) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM-yy");
		fromDateInput.setText(dateFormatter.format(fromDate));
		toDateInput.setText(dateFormatter.format(toDate));
		customerNameInput.setText(customerName);
		customerPhoneInput.setText(customerPhone);
		carTypeSelect.setSelectedIndex(carType);
	}
	
	
	public int getReservationId() {
		return thisReservation;
	}
	
	
	public void addCancelListener(ActionListener a) {
		cancelButton.addActionListener(a);
	}
	
	
	public void addSaveListener(ActionListener a) {
		saveButton.addActionListener(a);
	}
}
