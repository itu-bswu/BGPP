package Views;

/**
 * View - Reservation Overview
 *
 */
public class ReservationOverview extends javax.swing.JFrame {
	/**
	 * CellState enum
	 * Specifices 4 constants for marking a cell as the start, middle or end of a period
	 * Used when drawing the cells
	 */
	private enum CellState {
		CELL_START, CELL_MIDDLE, CELL_END, CELL_NONE
	}
	
	CellState[][] carsStates;
	
	/**
	 * ReservationOverview contructor
	 */
	public ReservationOverview() {
		this.setSize(800, 400);
		
		javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
		this.setJMenuBar(menuBar);
		
		javax.swing.JMenu fileMenu = new javax.swing.JMenu("File");
		menuBar.add(fileMenu);
		
		javax.swing.JMenuItem newReservationItem = new javax.swing.JMenuItem("New reservation...");
		fileMenu.add(newReservationItem);
		
		fileMenu.addSeparator();
		javax.swing.JMenuItem quitItem = new javax.swing.JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		javax.swing.JMenu viewMenu = new javax.swing.JMenu("View");
		menuBar.add(viewMenu);
		
		javax.swing.JMenuItem gotoItem = new javax.swing.JMenuItem("Go to date...");
		viewMenu.add(gotoItem);
		
		viewMenu.addSeparator();
		javax.swing.JMenuItem customerItem = new javax.swing.JMenuItem("View customer list...");
		viewMenu.add(customerItem);
		
		//TODO: Load number of cars from database
		carsStates = new CellState[15][7];
		
		//inserting dummy data
		//TODO: Load data from database
		for (CellState[] state : carsStates) {
			state[0] = CellState.CELL_START;
			state[1] = CellState.CELL_MIDDLE;
			state[2] = CellState.CELL_MIDDLE;
			state[3] = CellState.CELL_END;
			state[4] = CellState.CELL_NONE;
			state[5] = CellState.CELL_START;
			state[6] = CellState.CELL_END;
		}
		
		//date colums
		//TODO: generate from date
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
		
		//cell contents
		//TODO: Load cars from database
		Object[][] data = {
			{ "Lastbil 1", "", "", "", "", "", "", "" },
			{ "Lastbil 2", "", "", "", "", "", "", "" },
			{ "Lastbil 3", "", "", "", "", "", "", "" },
			{ "Sportsvogn 1", "", "", "", "", "", "", "" },
			{ "Sportsvogn 2", "", "", "", "", "", "", "" },
			{ "Sportsvogn 2", "", "", "", "", "", "", "" },
			{ "Sportsvogn 3", "", "", "", "", "", "", "" },
			{ "Sportsvogn 4", "", "", "", "", "", "", "" },
			{ "Sportsvogn 5", "", "", "", "", "", "", "" },
			{ "Varevogn 1", "", "", "", "", "", "", "" },
			{ "Varevogn 2", "", "", "", "", "", "", "" },
			{ "Varevogn 3", "", "", "", "", "", "", "" },
			{ "hundesl\u00E6de 1", "", "", "", "", "", "", "" },
			{ "hundesl\u00E6de 2", "", "", "", "", "", "", "" },
			{ "Din mor", "", "", "", "", "", "", "" }
		};
		
		//creates the table
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
		this.add(table.getTableHeader(), java.awt.BorderLayout.NORTH);
		this.add(new javax.swing.JScrollPane(table), java.awt.BorderLayout.CENTER);
		
		//bottom panel
		javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
		bottomPanel.setLayout(new java.awt.BorderLayout());
		bottomPanel.add(new javax.swing.JButton("<"), java.awt.BorderLayout.WEST);
		bottomPanel.add(new javax.swing.JButton(">"), java.awt.BorderLayout.EAST);
		bottomPanel.add(new javax.swing.JButton("Go to date..."), java.awt.BorderLayout.CENTER);
		this.add(bottomPanel, java.awt.BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	/**
	 * class TableCellRenderer
	 * custom renderer class for the cells in the table
	 * extends JPanel instead of the default JLabel, since no text is needed
	 */
	public class TableCellRenderer extends javax.swing.JPanel implements javax.swing.table.TableCellRenderer {
		private int row;
		private int column;
		
		/**
		 * getTableCellRendererComponent
		 * method defined in javax.swing.table.TableCellRenderer
		 * @param table the table
		 * @param value ??
		 * @param selected wether the cell is selected or not
		 * @param focus wether the cell is in focus or not
		 * @param row the cell's row index
		 * @param column the cell's column index
		 */
		public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean selected, boolean focus, int row, int column) {
			
			this.row = row;
			this.column = column;
			
			return this;
		}
		
		/**
		 * paintComponent
		 * draws the cell contents
		 * @param g the java.awt.Graphics object
		 */
		@Override public void paintComponent(java.awt.Graphics g) {
			super.paintComponent(g);
			
			g.setColor(java.awt.Color.BLUE);
			
			switch (carsStates[row][column-1]) {
				case CELL_START:
					g.fillRect(5, 5, this.getWidth()-5, this.getHeight()-10);
					break;
				case CELL_MIDDLE:
					g.fillRect(0, 5, this.getWidth(), this.getHeight()-10);
					break;
				case CELL_END:
					g.fillRect(0, 5, this.getWidth()-5, this.getHeight()-10);
					break;
				default:
					break;
			}
		}
	}
}
