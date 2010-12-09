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
 *
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
		
		JScrollPane tableScollPane = new JScrollPane(table);
		tablePanel.add(tableScollPane, BorderLayout.CENTER);
		
		this.setSize(400, 400);
		this.setVisible(true);
	}
	
	
	public int getCustomerId() {
		return customerId;
	}
	
	
	public void setPhoneString(String phone) {
		phoneInput.setText(phone);
	}
	
	
	public void setNameString(String name) {
		nameInput.setText(name);
	}
	
	
	public String getPhoneString() {
		return phoneInput.getText();
	}
	
	
	public String getNameString() {
		return nameInput.getText();
	}
	
	
	public void addSaveListener(ActionListener a) {
		saveButton.addActionListener(a);
	}
	
	
	public void addCancelListener(ActionListener a) {
		cancelButton.addActionListener(a);
	}
	
	
	public void setValues(List<Map<String, Object>> values) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setValues(values);
	}
	
	
	private class CustomTableModel extends AbstractTableModel {
		String[] columns = { "Car", "From date", "To date" };
		
		private List<Map<String, Object>> values;
		
		public void setValues(List<Map<String, Object>> content) {
			values = content;
		}
		
		public int getRowCount() {
			if (values == null) {
				return 0;
			}
			
			return values.size();
		}
		
		public int getColumnCount() {
			if (columns == null) {
				return 0;
			}
			
			return columns.length;
		}
		
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
		
		public String getColumnName(int column) {
			return columns[column];
		}
		
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	}
}
