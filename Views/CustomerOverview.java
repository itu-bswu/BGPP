package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

/**
 * View - Customer Overview
 *
 */
public class CustomerOverview extends JFrame {
	private JTable table;
	
	public CustomerOverview() {
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
		
		JButton search = new JButton("Search");
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
	
	
	public void addTableMouseListener(MouseListener m) {
		table.addMouseListener(m);
	}
	
	
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	
	public int getSelectedColumn() {
		return table.getSelectedColumn();
	}
	
	
	private class CustomTableModel extends AbstractTableModel {
		String[] columns = {
		"Name",
		"Phone"
		};
		
		String[][] values = {
		{
		"Name",
		"Phone"
		},
		{
		"Name",
		"Phone"
		},
		{
		"Name",
		"Phone"
		},
		{
		"Name",
		"Phone"
		},
		{
		"Name",
		"Phone"
		},
		{
		"Name",
		"Phone"
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
