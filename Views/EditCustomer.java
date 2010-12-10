package Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * View - Edit Customer
 */
public class EditCustomer extends JFrame {
	JTable table;
	
	JButton saveButton;
	JButton cancelButton;
	
	JTextField phoneInput;
	JTextField nameInput;
	
	int customerId;
	
	public final String saveButtonTitle = "Save";
	public final String cancelButtonTitle = "Cancel";
	
	/**
	 * constructor, sets up the interface
	 * @param customerId the customerId to load
	 */
	public EditCustomer(int customerId) {
		super("Edit customer");
		
		this.customerId = customerId;
		
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel labelPanel = new JPanel(new GridLayout(2, 0));
		topPanel.add(labelPanel, BorderLayout.WEST);
		
		JLabel nameLabel = new JLabel("Name:");
		labelPanel.add(nameLabel);
		
		JLabel phoneLabel = new JLabel("Phone:");
		labelPanel.add(phoneLabel);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 0));
		topPanel.add(inputPanel, BorderLayout.CENTER);
		
		nameInput = new JTextField();
		inputPanel.add(nameInput);
		
		phoneInput = new JTextField();
		inputPanel.add(phoneInput);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(2, 0));
		topPanel.add(buttonsPanel, BorderLayout.EAST);
		
		saveButton = new JButton("Save");
		buttonsPanel.add(saveButton);
		
		cancelButton = new JButton("Cancel");
		buttonsPanel.add(cancelButton);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		this.add(tablePanel);
		
		table = new JTable(new CustomTableModel());
		table.setGridColor(Color.GRAY);
		table.setShowGrid(true);
		table.setRowHeight(25);
		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		
		table.getColumnModel().getColumn(0).setMinWidth(175);
		
		JScrollPane tableScollPane = new JScrollPane(table);
		tablePanel.add(tableScollPane, BorderLayout.CENTER);
		
		this.setSize(400, 400);
		this.setVisible(true);
	}
	
	
	/**
	 * returns the customer in this window
	 * @return the customer id
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	
	/**
	 * sets the string shown in the phone field
	 * @param phone the phone string
	 */
	public void setPhoneString(String phone) {
		phoneInput.setText(phone);
	}
	
	
	/**
	 * sets the string shown in the name field
	 * @param name the name string
	 */
	public void setNameString(String name) {
		nameInput.setText(name);
	}
	
	
	/**
	 * gets the phone string currently in the phone field
	 * @return the phone string
	 */
	public String getPhoneString() {
		return phoneInput.getText();
	}
	
	
	/**
	 * gets the name string currently in the name field
	 * @return the name string
	 */
	public String getNameString() {
		return nameInput.getText();
	}
	
	
	/**
	 * adds an actionlistener to the save button
	 * @param a the actionlistener instance
	 */
	public void addSaveListener(ActionListener a) {
		saveButton.addActionListener(a);
	}
	
	
	/**
	 * adds an actionlistener to the cancel button
	 * @param a the actionlistener instance
	 */
	public void addCancelListener(ActionListener a) {
		cancelButton.addActionListener(a);
	}
	
	
	/**
	 * sets the values to be shown in the table
	 * @param values a List of reservation Maps
	 */
	public void setValues(List<Map<String, Object>> values) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setValues(values);
	}
	
	
	/**
	 * CustomTableModel class
	 * used to control the data in the table
	 */
	private class CustomTableModel extends AbstractTableModel {
		private String[] columns = { "Car", "From date", "To date" };
		
		private List<Map<String, Object>> values;
		
		/**
		 * sets the values to be shown in the table
		 * @param values a List of reservation Maps
		 */
		public void setValues(List<Map<String, Object>> content) {
			values = content;
		}
		
		/**
		 * gets the number of rows in the table
		 * @return number of rows
		 */
		public int getRowCount() {
			if (values == null) {
				return 0;
			}
			
			return values.size();
		}
		
		
		/**
		 * gets the number of columns in the table
		 * @return number of columns
		 */
		public int getColumnCount() {
			if (columns == null) {
				return 0;
			}
			
			return columns.length;
		}
		
		
		/**
		 * gets the value of the cell
		 * @param row the row
		 * @param column the column
		 * @return the object at that cell
		 */
		public Object getValueAt(int row, int column) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM-yy");
			
			switch (column) {
				case 0:
					return values.get(row).get("carTitle");
				case 1:
					return dateFormatter.format((Date)values.get(row).get("startDate"));
				case 2:
					return dateFormatter.format((Date)values.get(row).get("endDate"));
				default:
					return null;
			}
		}
		
		
		/**
		 * gets the name of the column
		 * @param column the column index
		 * @return the name of the column
		 */
		public String getColumnName(int column) {
			return columns[column];
		}
		
		
		/**
		 * specifies wether a cell is editable or not
		 * @param rowIndex the row index
		 * @param columnIndex the column index
		 * @return true if the cell is editable, false if not
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	}
}
