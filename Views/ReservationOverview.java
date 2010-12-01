package Views;

/**
 * View - Reservation Overview
 *
 */
public class ReservationOverview extends javax.swing.JFrame {
	public ReservationOverview() {
		this.setSize(800, 600);
		
		String[] colums = {
			"Car",
			"1/12",
			"2/12",
			"3/12",
			"4/12",
			"5/12",
			"6/12",
			"7/12"
		};
		
		Object[][] data = {
			{
				"Lastbil 1", "", "", "", "", "", "", ""
			},
			{
				"Lastbil 2", "", "", "", "", "", "", ""
			},
			{
				"Lastbil 3", "", "", "", "", "", "", ""
			},
			{
				"Sportsvogn 1", "", "", "", "", "", "", ""
			},
			{
				"Sportsvogn 2", "", "", "", "", "", "", ""
			},
			{
				"Sportsvogn 2", "", "", "", "", "", "", ""
			},
			{
				"Sportsvogn 3", "", "", "", "", "", "", ""
			},
			{
				"Sportsvogn 4", "", "", "", "", "", "", ""
			},
			{
				"Sportsvogn 5", "", "", "", "", "", "", ""
			},
			{
				"Varevogn 1", "", "", "", "", "", "", ""
			},
			{
				"Varevogn 2", "", "", "", "", "", "", ""
			},
			{
				"Varevogn 3", "", "", "", "", "", "", ""
			},
			{
				"hundesl\u00E6de 1", "", "", "", "", "", "", ""
			},
			{
				"hundesl\u00E6de 2", "", "", "", "", "", "", ""
			},
			{
				"Din mor", "", "", "", "", "", "", ""
			}
		};
		
		javax.swing.JTable table = new javax.swing.JTable(data, colums);
		table.setShowGrid(true);
		table.setShowHorizontalLines(true);
		table.setGridColor(java.awt.Color.GRAY);
		table.setRowHeight(25);
		
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			if (i == 0) {
				table.getColumnModel().getColumn(i).setMinWidth(150);
				table.getColumnModel().getColumn(i).setMaxWidth(150);
			} else {
				table.getColumnModel().getColumn(i).setCellRenderer(new TableCellRenderer());
			}
		}
		
		this.setLayout(new java.awt.BorderLayout());
		this.add(table.getTableHeader(), java.awt.BorderLayout.PAGE_START);
		this.add(table, java.awt.BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public class TableCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
		public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean selected, boolean focus, int row, int colum) {
			java.awt.Component cell = super.getTableCellRendererComponent(table, value, selected, focus, row, colum);
			
			cell.setBackground(java.awt.Color.BLUE);
			
			return cell;
		}
	}
}
