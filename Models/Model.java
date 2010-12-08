package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import Util.Logger;
import Util.MySQLConnection;

/**
 * Model
 * Basic abstract super-class for models. With this certain 
 * basic methods are guaranteed in all models. This is based 
 * on the CRUD-model (Create, Read, Update, Delete).
 *
 */
public abstract class Model {
	
	/**
	 * Creates an entry in the particular data-source, with 
	 * the data given in the Map. The ID of the new entry 
	 * is returned on success.
	 * 
	 * @param createVars Map containing data to be stored.
	 * @return ID on success; -1 on failure.
	 */
	public int create (Map<String, Object> createVars) {
		try {
			String className	= this.getClass().getName();
			className			= className.substring(className.lastIndexOf('.')+1, className.length());
			String setSQL 		= "";
			for (Map.Entry<String, Object> item : createVars.entrySet()) {
				setSQL = setSQL.concat(item.getKey() + " = '" + item.getValue() + "', ");
			}
			setSQL = setSQL.substring(0, setSQL.lastIndexOf(','));
			String query =	"INSERT INTO " + className + " " + 
							"SET " + setSQL;
			MySQLConnection conn = MySQLConnection.getInstance();
			ResultSet result = conn.query(query);
			if (result != null) {
				result.next();
				return result.getInt(1);
			}
		} catch (SQLException e) {
			Logger.write("Couldn't insert data to database: " + e.getMessage());
		}
		
		return -1;
	}
	
	/**
	 * Reads and returns the data with the provided Id in 
	 * a Map, with data-names as keys. If an entry with 
	 * the provided ID cannot be found in the data-source, 
	 * null will be returned.
	 * 
	 * @param id The id of the entry to read.
	 * @return Map containing data on success; null on failure.
	 */
	abstract public Map<String, Object> read (int id);
	
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
	abstract public boolean update(int id, Map<String, Object> updateVars);
	
	/**
	 * Deletes the entry with the provided ID in the data-
	 * source. On success true will be returned. If the 
	 * deletion failed (invalid ID or similar), false 
	 * will be returned.
	 * 
	 * @param id The ID of the entry to be deleted.
	 * @return true on success; false on failure.
	 */
	abstract public boolean delete (int id);
	
	/**
	 * Gives the amount of entries in the data-source, 
	 * i.e. the amount of customers in the database.
	 * 
	 * @return The amount of entries in the data-source.
	 */
	abstract public int amountOfEntries ();
	
	/**
	 * Lists the entries of the data-source.
	 * 
	 * @return A list with all data from the data-source.
	 */
	abstract public List<Map<String, Object>> list ();
}
