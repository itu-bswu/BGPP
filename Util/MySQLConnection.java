package Util;

/**
 * MySQL connector.
 * Singleton providing database connection. Made with the intention of 
 * de-coupling models from pure SQL. By doing that, it is possible to 
 * change data-source later on, without having to change anything else 
 * than the connector itself.
 * 
 * @author Niklas Hansen
 * @version 0.1
 */
public class MySQLConnection {
	
	// Singleton instance
	private static MySQLConnection Instance;
	
	protected MySQLConnection () {}
	
	/**
	 * Method for getting the MySQLConnection instance. If an instance 
	 * isn't created yet, an instance will be created and returned.
	 * 
	 * @return The MySQLConnection instance
	 */
	static public MySQLConnection getInstance() {
		if (Instance == null) {
			Instance = new MySQLConnection();
		}
		
		return Instance;
	}
}