package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Util.Logger;
import Util.MySQLConnection;

/**
 * Model - Customer
 *
 */
public class Customer extends Model {
	
	/**
	 * Creates an entry in the particular data-source, with 
	 * the data given in the Map. The ID of the new entry 
	 * is returned on success.
	 * 
	 * @param createVars Map containing data to be stored.
	 * 			key				=> description
	 * 			name			=> The full name of the customer.
	 * 			phone			=> The customer's phone-number.
	 * @return ID on success; -1 on failure.
	 */
	public int create (Map<String, Object> createVars) {
		String name = createVars.get("name").toString();
		int phone = Integer.parseInt(createVars.get("phone").toString());
		return create(name, phone);
	}
	
	/**
	 * TODO: Edit this text
	 * Creates an entry in the particular data-source, with 
	 * the data given in the Map. The ID of the new entry 
	 * is returned on success.
	 * 
	 * @param name The full name of the customer.
	 * @param phone The customer's phone-number.
	 * @return ID on success; -1 on failure.
	 */
	public int create (String name, int phone) {
		if (name == null || name.length() <= 0 || phone <= 0)
				throw new NullPointerException();
			
		try {
			MySQLConnection conn = MySQLConnection.getInstance();
			String query = 	"INSERT INTO Customer " +
							"SET name = '" + name + "', " + 
							"phone = '" + phone + "'";
			ResultSet result = conn.query(query);
			result.next();
			int newId = result.getInt(1);
			if (newId > 0) {
				return newId;
			}
		} catch (Exception e) {
			Logger.write("Couldn't insert row to database: " + e.getMessage());
		}
		
		return -1;
	}
	
	/**
	 * TODO: Edit this text
	 * Reads and returns the data with the provided Id in 
	 * a Map, with data-names as keys. If an entry with 
	 * the provided ID cannot be found in the data-source, 
	 * null will be returned.
	 * 
	 * @param id The id of the entry to read.
	 * @return Map containing data on success; null on failure.
	 */
	public Map<String, Object> read (int id) {
		return read (id, false);
	}
	
	/**
	 * TODO: Edit this text
	 * Reads and returns the data with the provided Id in 
	 * a Map, with data-names as keys. If an entry with 
	 * the provided ID cannot be found in the data-source, 
	 * null will be returned.
	 * 
	 * @param id The id of the entry to read.
	 * @return Map containing data on success; null on failure.
	 */
	public Map<String, Object> read (int phoneId, boolean phone) {
		if (phoneId <= 0)
			throw new NullPointerException();
		
		String query = "";
		if (!phone) {
			query = "SELECT customerId, name, phone " +
					"FROM Customer " +
					"WHERE customerId = " + phoneId + " " + 
					"LIMIT 1";
		} else {
			query = "SELECT customerId, name, phone " +
					"FROM Customer " +
					"WHERE phone = " + phoneId + " " + 
					"LIMIT 1";
		}
		
		try {
			MySQLConnection conn = MySQLConnection.getInstance();
			ResultSet result = conn.query(query);
			if (result == null) {
				throw new SQLException();
			}
			result.next();
			
			Map<String, Object> returnMap = new TreeMap<String, Object>();
			returnMap.put("id", 			result.getInt	("customerId"));
			returnMap.put("name", 			result.getString("name"));
			returnMap.put("phone", 			result.getInt	("phone"));
			
			return returnMap;
		} catch (SQLException e) {
			Logger.write("Couldn't read from the database: " + e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Updates the entry with the provided ID in the data-
	 * source. The data to be updated is the keys in the map, 
	 * and the values are the new data. If then entry is 
	 * successfully updated, true will be returned. If the 
	 * update failed (invalid ID or similar), false will 
	 * be returned.
	 * 
	 * @param id The ID of the entry to be updated.
	 * @param updateVars Map containing the data to be updated.
	 * @return true on success; false on failure.
	 */
	public boolean update(int id, Map<String, Object> updateVars) { return false; }
	
	/**
	 * Deletes the entry with the provided ID in the data-
	 * source. On success true will be returned. If the 
	 * deletion failed (invalid ID or similar), false 
	 * will be returned.
	 * 
	 * @param id The ID of the entry to be deleted.
	 * @return true on success; false on failure.
	 */
	public boolean delete (int id) {
		if (id <= 0)
			throw new NullPointerException();
		
		try {
			MySQLConnection conn = MySQLConnection.getInstance();
			String query = "DELETE FROM Customer " +
						   "WHERE customerId = " + id;
			ResultSet result = conn.query(query);
			result.next();
			if (result != null) {
				return true;
			}
		} catch (Exception e) {
			Logger.write("Couldn't delete row from database: " + e.getMessage());
		}
		
		return false;
	}
	
	/**
	 * Gives the amount of entries in the data-source, 
	 * i.e. the amount of customers in the database.
	 * 
	 * @return The amount of entries in the data-source.
	 */
	public int amountOfEntries () { return 0; }
	
	/**
	 * TODO: Implement this
	 * Lists the entries of the data-source.
	 * 
	 * @return A list with all data from the data-source.
	 */
	public List<Map<String, Object>> list () { return null; }
}
