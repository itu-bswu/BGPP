package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.List;
import java.util.Map;

/**
 * View - Customer Overview
 */
public class CustomerOverview extends JFrame {
	private JTable table;
	
	JButton search;
	
	JTextField nameInput;
	JTextField phoneInput;
	
	/**
	 * constructor, sets up the interface
	 */
	public CustomerOverview() {
		super("Customers list");
		
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
		
		search = new JButton("Search");
		topPanel.add(search, BorderLayout.EAST);
		
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
	
	
	/**
	 * gets the string in the name field
	 * @return the name string
	 */
	public String getNameString() {
		return nameInput.getText();
	}
	
	
	/**
	 * gets the string in the phone field
	 * @return the phone string
	 */
	public String getPhoneString() {
		return phoneInput.getText();
	}
	
	
	/**
	 * adds an actionlistener to the search button
	 * @param a the actionlistener instance
	 */
	public void addSearchListener(ActionListener a) {
		search.addActionListener(a);
	}
	
	
	/**
	 * adds an mouselistener to the table
	 * @param m the mouselistener instance
	 */
	public void addTableMouseListener(MouseListener m) {
		table.addMouseListener(m);
	}
	
	
	/**
	 * gets the currently selected row of the table
	 * @return the row index
	 */
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	
	/**
	 * gets the currently selected column of the table
	 * @return the column index
	 */
	public int getSelectedColumn() {
		return table.getSelectedColumn();
	}
	
	
	/**
	 * sets the values to be shown in the table
	 * @param content the List of customer Maps
	 */
	public void setValues(List<Map<String, Object>> content) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setValues(content);
		model.fireTableDataChanged();
	}
	
	
	/**
	 * CustomTableModel class
	 * custom table model to control the data
	 */
	private class CustomTableModel extends AbstractTableModel {
		private String[] columns = { "Name", "Phone" };
		
		private List<Map<String, Object>> values;
		
		/**
		 * sets the values to be shown in the table
		 * @param content the List of customer Maps
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
			switch (column) {
				case 0:
					return values.get(row).get("name");
				case 1:
					return values.get(row).get("phone");
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
