package Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import Models.*;
import Controllers.ReservationOverviewController;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

/**
 * View - Reservation Overview
 */
public class ReservationOverview extends JFrame {
	/**
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
	
	private JTable table;
	
	private int week;
	private int year;
	
	private JLabel currentDateLabel;
	
	private ReservationOverviewController controller;
	
	static ReservationOverview instance = null;
	
	
	/**
	 * gets the shared instance of this window
	 * @return the shared instance
	 */
	public static ReservationOverview getInstance() {
		if (instance == null) {
			instance = new ReservationOverview();
		}
		
		return instance;
	}
	
	
	public void setController(ReservationOverviewController controller) {
		this.controller = controller;
	}
	
	
	public ReservationOverviewController getController() {
		return controller;
	}
	
	
	/**
	 * Constructor, sets up the interface
	 */
	protected ReservationOverview() {
		super("Reservations overview");
		
		this.setSize(800, 400);
		
		setupMenuBar();
		
		currentDateLabel = new JLabel("", SwingConstants.CENTER);
		
		carsStates = new CellState[0][0];
		cellReservationIds = new Integer[0][0];
		
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
	
	
	/**
	 * sets up the menu bar
	 */
	public void setupMenuBar () {
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newReservationItem = new JMenuItem("New reservation...");
		fileMenu.add(newReservationItem);
		
		fileMenu.addSeparator();
		quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);
		
		prevWeekItem = new JMenuItem("Previous week");
		viewMenu.add(prevWeekItem);
		
		nextWeekItem = new JMenuItem("Next week");
		viewMenu.add(nextWeekItem);
		
		gotoItem = new JMenuItem("Go to week...");
		viewMenu.add(gotoItem);
		
		viewMenu.addSeparator();
		updateReservationsItem = new JMenuItem("Reload reservations");
		viewMenu.add(updateReservationsItem);
		
		viewMenu.addSeparator();
		customerListItem = new JMenuItem("View customer list...");
		viewMenu.add(customerListItem);
	}
	
	
	/**
	 * add a new ActionListener to the add new reservation action
	 * @param a the ActionListener
	 */
	public void addNewReservationListener(ActionListener a) {
		newReservationItem.addActionListener(a);
	}
	
	
	/**
	 * add a new ActionListener to the quit action
	 * @param a the ActionListener
	 */
	public void addQuitListener(ActionListener a) {
		quitItem.addActionListener(a);
	}
	
	
	/**
	 * add a new ActionListener to the go to date action
	 * @param a the ActionListener
	 */
	public void addGotoListener(ActionListener a) {
		gotoItem.addActionListener(a);
	}
	
	
	/**
	 * add a new ActionListener to the show customer list action
	 * @param a the ActionListener
	 */
	public void addCustomerListListener(ActionListener a) {
		customerListItem.addActionListener(a);
	}
	
	
	/**
	 * add a new ActionListener to the previous week list action
	 * @param a the ActionListener
	 */
	public void addNextWeekListener(ActionListener a) {
		nextWeekItem.addActionListener(a);
	}
	
	
	/**
	 * add a new ActionListener to the update reservations list action
	 * @param a the ActionListener
	 */
	public void addUpdateListener(ActionListener a) {
		updateReservationsItem.addActionListener(a);
	}
	
	
	/**
	 * add a new ActionListener to the next week list action
	 * @param a the ActionListener
	 */
	public void addPrevWeekListener(ActionListener a) {
		prevWeekItem.addActionListener(a);
	}
	
	
	/**
	 * add a new MouseListener to the table
	 * @param m the MouseListener
	 */
	public void addTableMouseListener(MouseListener m) {
		table.addMouseListener(m);
	}
	
	
	/**
	 * used to receive the selected row in the table
	 * @return the selected row index
	 */
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	
	/**
	 * used to receive the selected column in the table
	 * @return the selected column index
	 */
	public int getSelectedColumn() {
		return table.getSelectedColumn();
	}
	
	
	/**
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
	 * changes the table to display data for the specified week number
	 * @param weekNr the week number to change to
	 * @param yearNr the year to change to
	 */
	public void goToWeek(int weekNr, int yearNr) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.goToWeek(weekNr, yearNr);
	}
	
	
	/**
	 * sets the car-rows of the table
	 * @param carList the List of car Maps
	 */
	public void setCars(List<Map<String, Object>> carList) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setCars(carList);
	}
	
	
	/**
	 * sets the reservations to be shown in the table
	 * @param reservationsList the List of reservations
	 */
	public void setReservations(List<Map<String, Object>> reservationList) {
		CustomTableModel model = (CustomTableModel)table.getModel();
		model.setReservations(reservationList);
	}
	
	
	/**
	 * updates the table cells by applying a default size and/or setting a custom cell rendering
	 */
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
	 * custom table model to control the data
	 */
	private class CustomTableModel extends AbstractTableModel {
		private List<Map<String, Object>> cars = null;
		private List<Map<String, Object>> reservations = null;
		private Date firstWeekDay = null;
		private Date lastWeekDay = null;
		private String[] columns = null;
		
		/**
		 * contructor, sets up the table model
		 */
		public CustomTableModel() {
			columns = new String[8];
			columns[0] = "Car";
		}
		
		
		/**
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
		
		
		/**
		 * gets the number of rows in the table
		 * @return number of rows
		 */
		public int getRowCount() {
			if (cars == null) {
				return 0;
			}
			
			return cars.size();
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
		 * @param value the value of the cell
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
