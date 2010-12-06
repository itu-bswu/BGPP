package Models;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Model - Reservation
 *
 */
public class Reservation extends Model {
	
	/**
	 * TODO: Implement this
	 * Creates an entry in the particular data-source, with 
	 * the data given in the Map. The ID of the new entry 
	 * is returned on success.
	 * 
	 * @param createVars Map containing data to be stored.
	 * 			key			=> description
	 * 			customer	=> The ID of the customer to book a car.
	 * 			carType		=> The ID of the car-type to be booked.
	 * 			startDate	=> The start date of the booking.
	 * 			endDate		=> The end date of the booking.
	 * @return ID on success; -1 on failure.
	 */
	public int create (Map<String, Object> createVars) { return -1; }
	
	/**
	 * TODO: Implement this
	 * Creates an entry in the particular data-source, with 
	 * the data given in the Map. The ID of the new entry 
	 * is returned on success.
	 * 
	 * @param customer The ID of the customer to book a car.
	 * @param carType The ID of the car-type to be booked.
	 * @param startDate The start date of the booking.
	 * @param endDate The end date of the booking.
	 * @return ID on success; -1 on failure.
	 */
	public int create (int customer, int carType, Date startDate, Date endDate) { return -1; }
	
	/**
	 * TODO: Implement this
	 * Reads and returns the data with the provided Id in 
	 * a Map, with data-names as keys. If an entry with 
	 * the provided ID cannot be found in the data-source, 
	 * null will be returned.
	 * 
	 * @param id The id of the entry to read.
	 * @return Map containing data on success; null on failure.
	 */
	public Map<String, Object> read (int id) { return null; }
	
	/**
	 * TODO: Implement this
	 * Updates the entry with the provided ID in the data-
	 * source. The data to be updated is the keys in the map, 
	 * and the values are the new data. If then entry is 
	 * successfully updated, true will be returned. If the 
	 * update failed (invalid ID or similar), false will 
	 * be returned.
	 * 
	 * @param id The ID of the entry to be updated.
	 * @param updateVars Map containing the data to be updated.
	 * 			key			=> description
	 * 			customer	=> The ID of the customer to book a car.
	 * 			carType		=> The ID of the car-type to be booked.
	 * 			startType	=> The start date of the booking.
	 * 			endDate		=> The end date of the booking.
	 * @return true on success; false on failure.
	 */
	public boolean update (int id, Map<String, Object> updateVars) { return false; }
	
	/**
	 * TODO: Implement this
	 * Updates the entry with the provided ID in the data-
	 * source. The data to be updated is the keys in the map, 
	 * and the values are the new data. If then entry is 
	 * successfully updated, true will be returned. If the 
	 * update failed (invalid ID or similar), false will 
	 * be returned.
	 * 
	 * @param id The ID of the entry to be updated.
	 * @param customer The ID of the customer to book a car.
	 * @param carType The ID of the car-type to be booked.
	 * @param startDate The start date of the booking.
	 * @param endDate The end date of the booking.
	 * @return true on success; false on failure.
	 */
	public boolean update (int id, int customer, int carType, Date startDate, Date endDate) { return false; }
	
	/**
	 * TODO: Implement this
	 * Deletes the entry with the provided ID in the data-
	 * source. On success true will be returned. If the 
	 * deletion failed (invalid ID or similar), false 
	 * will be returned.
	 * 
	 * @param id The ID of the entry to be deleted.
	 * @return true on success; false on failure.
	 */
	public boolean delete (int id) { return false; }
	
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
	
	/**
	 * TODO: Implement this
	 * TODO: More list()-methods
	 * Lists the entries of the data-source, from the user 
	 * with the provided ID.
	 * 
	 * @return A list with all data from the data-source.
	 */
	public List<Map<String, Object>> list (int id) { return null; }
}
