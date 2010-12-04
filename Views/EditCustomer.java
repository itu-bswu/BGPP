package Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * View - Edit Customer
 *
 */
public class EditCustomer extends JFrame {
	JTable table;
	
	public EditCustomer() {
		super("Edit customer");
		
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
		
		JTextField nameInput = new JTextField();
		inputPanel.add(nameInput);
		
		JTextField phoneInput = new JTextField();
		inputPanel.add(phoneInput);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(2, 0));
		topPanel.add(buttonsPanel, BorderLayout.EAST);
		
		JButton saveButton = new JButton("Save");
		buttonsPanel.add(saveButton);
		
		JButton cancelButton = new JButton("Cancel");
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
	
	private class CustomTableModel extends AbstractTableModel {
		String[] columns = {
		"Car",
		"From date",
		"To date"
		};
		
		String[][] values = {
		{
		"Car",
		"1/12",
		"4/12"
		},
		{
		"Car",
		"1/12",
		"4/12"
		},
		{
		"Car",
		"1/12",
		"4/12"
		},
		{
		"Car",
		"1/12",
		"4/12"
		},
		{
		"Car",
		"1/12",
		"4/12"
		},
		{
		"Car",
		"1/12",
		"4/12"
		}
		};
		
		public int getRowCount() {
			return values.length;
		}
		
		public int getColumnCount() {
			return columns.length;
		}
		
		public Object getValueAt(int row, int column) {
			return values[row][column];
		}
		
		public String getColumnName(int column) {
			return columns[column];
		}
		
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	}
}
