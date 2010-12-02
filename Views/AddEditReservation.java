package Views;

import javax.swing.*;
import java.awt.*;

/**
 * View - Add/Edit Reservation
 *
 */
public class AddEditReservation extends JFrame {
	/**
	 * AddEditReservation constructor
	 * creates a new reservation window
	 */
	public AddEditReservation() {
		this.setupInterface(-1);
	}
	
	/**
	 * AddEditReservation constructor
	 * Creates a new reservation window, prepopulated with data
	 * @param reservationID the reservation ID
	 */
	public AddEditReservation(int reservationID) {
		this.setupInterface(reservationID);
	}
	
	/**
	 * sets up the interface
	 * @param reservationID the reservation ID to get data from. Can be -1 for no reservation ID
	 */
	private void setupInterface(int reservationID) {
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
		
		String[] items = {
			"Lastbil",
			"Sportsvogn",
			"Slæde"
		};
		JComboBox carTypeSelect = new JComboBox(items);
		inputPanel.add(carTypeSelect);
		
		JTextField fromDateInput = new JTextField();
		inputPanel.add(fromDateInput);
		
		JTextField toDateInput = new JTextField();
		inputPanel.add(toDateInput);
		
		JTextField customerNameInput = new JTextField();
		inputPanel.add(customerNameInput);
		
		JTextField customerPhoneInput = new JTextField();
		inputPanel.add(customerPhoneInput);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton cancelButton = new JButton("Cancel");
		buttonsPanel.add(cancelButton);
		
		JButton saveButton = new JButton("Save");
		buttonsPanel.add(saveButton);
		
		this.setSize(275, 220);
		this.setVisible(true);
	}
}
