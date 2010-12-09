package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.List;
import java.util.Map;

/**
 * View - Customer Overview
 *
 */
public class CustomerOverview extends JFrame {
	private JTable table;
	
	JButton search;
	
	JTextField nameInput;
	JTextField phoneInput;
	
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
	
	
	public String getNameString() {
		return nameInput.getText();
	}
	
	
	public String getPhoneString() {
		return phoneInput.getText();
	}
	
	
	public void addSearchListener(ActionListener a) {
		search.addActionListener(a);
	}
	
	
	public void addTableMouseListener(MouseListener m) {
		table.addMouseListener(m);
	}
	
	
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	
	public int getSelectedColumn() {
		return table.getSelectedColumn();
	}
	
	
	public void setValues(List<Map<String, Object>> content) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setValues(content);
		model.fireTableDataChanged();
	}
	
	
	private class CustomTableModel extends AbstractTableModel {
		private String[] columns = { "Name", "Phone" };
		
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
			switch (column) {
				case 0:
					return values.get(row).get("name");
				case 1:
					return values.get(row).get("phone");
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
