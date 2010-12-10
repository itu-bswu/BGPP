package Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import Models.*;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

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
	private Integer[][] cellReservationIds;
	
	private JMenuItem newReservationItem;
	private JMenuItem quitItem;
	private JMenuItem gotoItem;
	private JMenuItem prevWeekItem;
	private JMenuItem nextWeekItem;
	private JMenuItem customerListItem;
	private JMenuItem updateReservationsItem;
	
	public final String customerListItemTitle = "View customer list...";
	public final String newReservationItemTitle = "New reservation...";
	public final String prevWeekItemTitle = "Previous week";
	public final String nextWeekItemTitle = "Next week";
	public final String gotoItemTitle = "Go to week...";
	public final String updateReservationsTitle = "Reload reservations";
	
	private JTable table;
	
	private int week;
	private int year;
	
	private JLabel currentDateLabel;
	
	/**
	 * ReservationOverview contructor
	 */
	public ReservationOverview() {
		super("Reservations overview");
		
		this.setSize(800, 400);
		
		setupMenuBar();
		
		currentDateLabel = new JLabel("", SwingConstants.CENTER);
		
		carsStates = new CellState[0][0];
		cellReservationIds = new Integer[0][0];
		
		//creates the table
		table = new JTable(new CustomTableModel());
		table.setShowGrid(true);
		table.setGridColor(Color.GRAY);
		table.setRowHeight(25);
		table.getTableHeader().setReorderingAllowed(false);
		
		updateTableCells();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		this.getContentPane().add(currentDateLabel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void setupMenuBar () {
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
		updateReservationsItem = new JMenuItem(updateReservationsTitle);
		viewMenu.add(updateReservationsItem);
		
		viewMenu.addSeparator();
		customerListItem = new JMenuItem(customerListItemTitle);
		viewMenu.add(customerListItem);
	}
	
	public void updateTableCells() {
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			if (i == 0) {
				table.getColumnModel().getColumn(i).setMinWidth(175);
			} else {
				table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
			}
		}
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
	 * addUpdateListener
	 * add a new ActionListener to the update reservations list action
	 * @param a the ActionListener
	 */
	public void addUpdateListener(ActionListener a) {
		updateReservationsItem.addActionListener(a);
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
	 * getWeek
	 * gets the current week number displayed in the table
	 * @return the current week number
	 */
	public int getWeek() {
		return week;
	}
	
	
	/**
	 * gets the current year displayed in the table
	 * @return the current year
	 */
	public int getYear() {
		return year;
	}
	
	
	/**
	 * goToWeek
	 * changes the table to display data for the specified week number
	 * @param weekNr the week number to change to
	 * @param yearNr the year to change to
	 */
	public void goToWeek(int weekNr, int yearNr) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.goToWeek(weekNr, yearNr);
	}
	
	
	/**
	 * setCars
	 * sets the car-rows of the table
	 * @param carList the List of car Maps
	 */
	public void setCars(List<Map<String, Object>> carList) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setCars(carList);
	}
	
	
	/**
	 * setReservations
	 * sets the reservations to be shown in the table
	 * @param reservationsList the List of reservations
	 */
	public void setReservations(List<Map<String, Object>> reservationList) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setReservations(reservationList);
	}
	
	
	/**
	 * resetReservationsStates
	 * resets the cell states for the table
	 */
	private void resetReservationStates() {
		int i, j;
		for (i = 0; i < carsStates.length; i++) {
			for (j = 0; j < 7; j++) {
				carsStates[i][j] = CellState.CELL_NONE;
				cellReservationIds[i][j] = -1;
			}
		}
	}
	
	
	/**
	 * get the reservation for the selected tablecell
	 * @return the reservation ID
	 */
	public int getReservationForSelectedCell() {
		return cellReservationIds[getSelectedRow()][getSelectedColumn()-1];
	}
	
	
	/**
	 * CustomTableModel
	 * 
	 */
	private class CustomTableModel extends AbstractTableModel {
		private List<Map<String, Object>> cars = null;
		private List<Map<String, Object>> reservations = null;
		private Date firstWeekDay = null;
		private Date lastWeekDay = null;
		private String[] columns = null;
		
		/**
		 * CustomTableModel contructor
		 * sets up the table model
		 */
		public CustomTableModel() {
			columns = new String[8];
			columns[0] = "Car";
			
			int i;
			for (i = 1; i < 8; i++) {
				columns[i] = "";
			}
		}
		
		
		/**
		 * setCars
		 * sets the car-rows of the table
		 * @param carList the List of car Maps
		 */
		public void setCars(List<Map<String, Object>> carList) {
			cars = carList;
			
			carsStates = new CellState[cars.size()][7];
			cellReservationIds = new Integer[cars.size()][7];
			resetReservationStates();
			updateTableCells();
		}
		
		
		/**
		 * setReservations
		 * sets the reservations to be shown in the table
		 * @param reservationsList the List of reservations
		 */
		public void setReservations(List<Map<String, Object>> reservationList) {
			reservations = reservationList;
			
			resetReservationStates();
			
			for (Map<String, Object> reservation : reservationList) {
				Date startDate = (Date)(reservation.get("startDate"));
				Date endDate = (Date)(reservation.get("endDate"));
				int reservationId = ((Integer)reservation.get("id")).intValue();
				int carId = ((Integer)reservation.get("carId")).intValue();
				
				int carIndex = -1;
				int i;
				for (i = 0; i < cars.size(); i++) {
					int currentCar = (Integer)(cars.get(i).get("id"));
					if (currentCar == carId) {
						carIndex = i;
						break;
					}
				}
				
				int startDayIndex = (int)((startDate.getTime()-firstWeekDay.getTime())/(1000*60*60*24));
				int endDayIndex = (int)((endDate.getTime()-firstWeekDay.getTime())/(1000*60*60*24));
				
				for (i = (startDayIndex < 0? 0: startDayIndex); i <= (endDayIndex > 6? 6: endDayIndex); i++) {
					carsStates[carIndex][i] = (i == startDayIndex? CellState.CELL_START: i == endDayIndex? CellState.CELL_END: CellState.CELL_MIDDLE);
					cellReservationIds[carIndex][i] = reservationId;
				}
			}
			
			ReservationOverview.this.invalidate();
			ReservationOverview.this.validate();
		}
		
		
		/**
		 * goToWeek
		 * changes the table to display data for the specified week number
		 * @param weekNr the week number to change to
		 * @param yearNr the year to change to
		 */
		public void goToWeek(int weekNr, int yearNr) {
			week = weekNr;
			year = yearNr;
			
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.clear();
			cal.set(Calendar.YEAR, yearNr);
			cal.set(Calendar.WEEK_OF_YEAR, weekNr+1);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			firstWeekDay = cal.getTime();
			
			int i;
			for (i = 0; i < 7; i++) {
				columns[i+1] = "" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1);
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
			
			lastWeekDay = cal.getTime();
			
			this.fireTableStructureChanged();
			updateTableCells();
			
			currentDateLabel.setText("week " + weekNr + ", " + yearNr);
		}
		
		
		public int getRowCount() {
			if (cars == null) {
				return 0;
			}
			
			return cars.size();
		}
		
		
		public int getColumnCount() {
			if (columns == null) {
				return 0;
			}
			
			return columns.length;
		}
		
		
		public Object getValueAt(int row, int column) {
			if (column > 0) {
				return null;
			} else {
				String licensePlate = cars.get(row).get("licensePlate").toString();
				licensePlate = licensePlate.substring(0, 2) + " " +
							   licensePlate.substring(2, 4) + " " +
							   licensePlate.substring(4);
				return cars.get(row).get("carType") + " (" + 
					   				 licensePlate + ")";
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
	private class CustomTableCellRenderer extends DefaultTableCellRenderer {
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
			g.setColor(Color.BLUE);
			
			if (carsStates != null) {
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
			
			super.paintComponent(g);
		}
	}
}
