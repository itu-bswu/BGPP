package Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * View - Reservation Overview
 *
 */
public class ReservationOverview extends JFrame {
	/**
	 * CellState enum
	 * Specifices 4 constants for marking a cell as the start, middle or end of a period
	 * Used when drawing the cells
	 */
	private enum CellState {
		CELL_START, CELL_MIDDLE, CELL_END, CELL_NONE
	}
	
	private CellState[][] carsStates;
	
	private JMenuItem newReservationItem;
	private JMenuItem quitItem;
	private JMenuItem gotoItem;
	private JMenuItem prevWeekItem;
	private JMenuItem nextWeekItem;
	private JMenuItem customerListItem;
	
	public final String customerListItemTitle = "View customer list...";
	public final String newReservationItemTitle = "New reservation...";
	public final String prevWeekItemTitle = "Previous week";
	public final String nextWeekItemTitle = "Next week";
	public final String gotoItemTitle = "Go to week...";
	
	private JTable table;
	
	/**
	 * ReservationOverview contructor
	 */
	public ReservationOverview() {
		super("Reservatoins overview");
		
		this.setSize(800, 400);
		
		//setting up the menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newReservationItem = new JMenuItem(newReservationItemTitle);
		fileMenu.add(newReservationItem);
		
		fileMenu.addSeparator();
		quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);
		
		prevWeekItem = new JMenuItem(prevWeekItemTitle);
		viewMenu.add(prevWeekItem);
		
		nextWeekItem = new JMenuItem(nextWeekItemTitle);
		viewMenu.add(nextWeekItem);
		
		gotoItem = new JMenuItem(gotoItemTitle);
		viewMenu.add(gotoItem);
		
		viewMenu.addSeparator();
		customerListItem = new JMenuItem(customerListItemTitle);
		viewMenu.add(customerListItem);
		
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
		
		//creates the table
		table = new JTable(new CustomTableModel());
		table.setShowGrid(true);
		table.setGridColor(Color.GRAY);
		table.setRowHeight(25);
		
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			if (i == 0) {
				table.getColumnModel().getColumn(i).setMinWidth(150);
				table.getColumnModel().getColumn(i).setMaxWidth(150);
			} else {
				table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
			}
		}
		
		this.setLayout(new BorderLayout());
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	
	/**
	 * addNewReservationListener
	 * add a new ActionListener to the add new reservation action
	 * @param a the ActionListener
	 */
	public void addNewReservationListener(ActionListener a) {
		newReservationItem.addActionListener(a);
	}
	
	
	/**
	 * addQuitListener
	 * add a new ActionListener to the quit action
	 * @param a the ActionListener
	 */
	public void addQuitListener(ActionListener a) {
		quitItem.addActionListener(a);
	}
	
	
	/**
	 * addGotoListener
	 * add a new ActionListener to the go to date action
	 * @param a the ActionListener
	 */
	public void addGotoListener(ActionListener a) {
		gotoItem.addActionListener(a);
	}
	
	
	/**
	 * addCustomerListListener
	 * add a new ActionListener to the show customer list action
	 * @param a the ActionListener
	 */
	public void addCustomerListListener(ActionListener a) {
		customerListItem.addActionListener(a);
	}
	
	
	/**
	 * addNextWeekListener
	 * add a new ActionListener to the previous week list action
	 * @param a the ActionListener
	 */
	public void addNextWeekListener(ActionListener a) {
		nextWeekItem.addActionListener(a);
	}
	
	
	/**
	 * addPrevWeekListener
	 * add a new ActionListener to the next week list action
	 * @param a the ActionListener
	 */
	public void addPrevWeekListener(ActionListener a) {
		prevWeekItem.addActionListener(a);
	}
	
	
	/**
	 * addTableMouseListener
	 * add a new MouseListener to the table
	 * @param m the MouseListener
	 */
	public void addTableMouseListener(MouseListener m) {
		table.addMouseListener(m);
	}
	
	
	/**
	 * getSelectedRow
	 * used to receive the selected row in the table
	 * @return the selected row index
	 */
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	
	/**
	 * getSelectedColumn
	 * used to receive the selected column in the table
	 * @return the selected column index
	 */
	public int getSelectedColumn() {
		return table.getSelectedColumn();
	}
	
	
	/**
	 * CustomTableModel
	 * 
	 */
	private class CustomTableModel extends AbstractTableModel {
		String[] columns = {
		"Car",
		"1/12",
		"2/12",
		"3/12",
		"4/12",
		"5/12",
		"6/12",
		"7/12"
		};
		
		
		String[] cars = {
		"Lastbil 1",
		"Lastbil 2",
		"Lastbil 3",
		"Sportsvogn 1",
		"Sportsvogn 2",
		"Sportsvogn 2",
		"Sportsvogn 3",
		"Sportsvogn 4",
		"Sportsvogn 5",
		"Varevogn 1",
		"Varevogn 2",
		"Varevogn 3",
		"hundesl\u00E6de 1",
		"hundesl\u00E6de 2",
		"Din mor"
		};
		
		
		public int getRowCount() {
			return cars.length;
		}
		
		public int getColumnCount() {
			return columns.length;
		}
		
		public Object getValueAt(int row, int column) {
			if (column > 0) {
				return null;
			} else {
				return cars[row];
			}
		}
		
		public String getColumnName(int column) {
			return columns[column];
		}
		
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	}
	
	
	/**
	 * class TableCellRenderer
	 * custom renderer class for the cells in the table
	 * extends JPanel instead of the default JLabel, since no text is needed
	 */
	private class CustomTableCellRenderer extends JPanel implements TableCellRenderer {
		private int row;
		private int column;
		
		/**
		 * getTableCellRendererComponent
		 * method defined in table.TableCellRenderer
		 * @param table the table
		 * @param value ??
		 * @param selected wether the cell is selected or not
		 * @param focus wether the cell is in focus or not
		 * @param row the cell's row index
		 * @param column the cell's column index
		 */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int column) {
			
			this.row = row;
			this.column = column;
			
			return this;
		}
		
		/**
		 * paintComponent
		 * draws the cell contents
		 * @param g the Graphics object
		 */
		@Override public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLUE);
			
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
