package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import Util.Logger;
import Util.MySQLConnection;

/**
 * Model - Car.
 * As data-representation of cars in the database, this class provides several 
 * methods for dealing with cars, i.e. creating cars, listing cars, updating 
 * and deleting cars.
 * 
 * <code>
 * 	Car car = new Car();
 * 	// Create a new car (Ford Transit), with the license-plate "SV 12 345".
 * 	CarType carType = new CarType();
 * 	int varevogn = carType.create("Varevogn");
 * 	int newCar = car.create("Ford Transit", "SV 12 345", varevogn);
 * 	// Delete a car.
 * 	car.delete(newCar);
 * </code>
 */
public class Car extends Model {
	
	/**
	 * Creates a new car in the database with the provided informations. If the 
	 * creation of the car is successful, the ID-number of the new car is 
	 * returned; otherwise -1 is returned.
	 * 
	 * @param createVars Map containing data to be stored.
	 * 			key				=> description
	 * 			title			=> The title of the car; eg. Ford Fiesta
	 * 			licensePlate	=> The licenseplate of the car; eg. SV 21 435
	 * 			carType 		=> The ID of the car-type of the car.
	 * @return ID on success; -1 on failure.
	 */
	public int create (Map<String, Object> createVars) {
		if (createVars.get("title").toString() == null || 
			createVars.get("title").toString().length() <= 0 ||
			createVars.get("licensePlate").toString() == null || 
			createVars.get("licensePlate").toString().length() <= 0 || 
			Integer.parseInt(createVars.get("carType").toString()) <= 0)
				throw new NullPointerException();
		
		createVars.put("licensePlate", createVars.get("licensePlate").toString()
												 .replaceAll(" ", ""));
		
		CarType carType = new CarType();
		int carTypeId = Integer.parseInt(createVars.get("carType").toString());
		if (carType.read(carTypeId) == null)
			return -1;
		
		return super.create(createVars);
	}
	
	/**
	 * Creates a new car in the database with the provided informations. If the 
	 * creation of the car is successful, the ID-number of the new car is 
	 * returned; otherwise -1 is returned.
	 * 
	 * @param title The title of the car; eg. Ford Fiesta
	 * @param licensePlate The licenseplate of the car; eg. SV 21 435 (spaces unnecessary)
	 * @param carType The ID of the car-type of the car.
	 * @return ID on success; -1 on failure.
	 */
	public int create (String title, String licensePlate, int carType) {
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("title", title);
		createVars.put("licensePlate", licensePlate);
		createVars.put("carType", carType);
		return this.create(createVars);
	}
	
	/**
	 * Reads the car with the provided ID, and returns a Map containing the data 
	 * of the car. If no car is found, null will be returned.
	 * 
	 * @param id The id of the entry to read.
	 * @return Map containing data on success; null on failure.
	 * 			key 			=> description:
	 * 			id 				=> The ID of the car
	 * 			name			=> The title of the car
	 * 			typeId 			=> The type-ID of the car
	 * 			typeName		=> The name of the car-type
	 * 			licensePlate	=> The license plate
	 */
	public Map<String, Object> read (int id) {
		if (id <= 0)
			throw new NullPointerException();
		
		try {
			MySQLConnection conn = MySQLConnection.getInstance();
			String query = "SELECT Car.carId, Car.carType, Car.licensePlate, Car.title, CarType.title " +
						   "FROM Car, CarType " +
						   "WHERE carId = " + id + " " +
						   "AND CarType.typeId = Car.carType " + 
						   "LIMIT 1";
			ResultSet result = conn.query(query);
			if (result == null)
				return null;
			result.next();
			
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("id", 			result.getInt	("Car.carId"));
			returnMap.put("name", 			result.getString("Car.title"));
			returnMap.put("carTypeId", 		result.getInt	("Car.carType"));
			returnMap.put("carTypeName", 	result.getString("CarType.title"));
			returnMap.put("licensePlate", 	result.getString("Car.licensePlate"));
			
			return returnMap;
		} catch (SQLException e) {
			Logger.write("Couldn't read from the database: " + e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Updates the car with the provided ID-number. The fields to be updated, 
	 * are the keys in the map, and the new data is the values in the map.
	 * 
	 * @param id The ID of the entry to be updated.
	 * @param updateVars Map containing the data to be updated.
	 * @return true on success; false on failure.
	 */
	public boolean update(int id, Map<String, Object> updateVars) {
		if (updateVars.containsKey("licensePlate")) {
			String newLicensePlate = updateVars.get("licensePlate")
											   .toString()
											   .replaceAll(" ", "");
			updateVars.put("licensePlate", newLicensePlate);
		}
		
		return super.update(id, updateVars, "carId");
	}
	
	/**
	 * Deletes the car with the provided ID-number. If the deletion fails, 
	 * false is returned. Otherwise true is returned. Please note: If no car 
	 * is found with the provided ID, true will still be returned, as an entry 
	 * with that ID isn't in the database after this method-call.
	 * 
	 * @param id The ID of the entry to be deleted.
	 * @return true on success; false on failure.
	 */
	public boolean delete (int id) {
		return super.delete(id, "carId");
	}
	
	/**
	 * Returns a List of all cars in the database. Every car is represented 
	 * with a Map<String, Object> containing the data about that car.
	 * 
	 * @return A list with all data from the data-source.
	 * 			key				=> description
	 * 			id				=> The ID of the car.
	 * 			title			=> The title of the car (eg. Ford Fiesta).
	 * 			licensePlate	=> The license plate of the car (eg. SV32765).
	 * 			carTypeId		=> The ID of the type of the car.
	 * 			carType			=> The title of the car-type.
	 */
	public List<Map<String, Object>> list () {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		try {
			String query =	"SELECT carId, Car.title, licensePlate, carType, CarType.title " +
							"FROM Car, CarType " +
							"WHERE Car.carType = CarType.typeId " +
							"ORDER BY carType ASC ";
			MySQLConnection conn = MySQLConnection.getInstance();
			ResultSet result = conn.query(query);
			if (result == null)
				return null;
			Map<String, Object> curr;
			while (result.next()) {
				curr = new HashMap<String, Object>();
				curr.put("id", 				result.getInt	("carId"));
				curr.put("title", 			result.getString("title"));
				curr.put("licensePlate", 	result.getString("licensePlate"));
				curr.put("carTypeId", 		result.getInt	("carType"));
				curr.put("carType", 		result.getString("CarType.title"));
				
				list.add(curr);
			}
		} catch (SQLException e) {
			Logger.write("Failed to list items from database: " + e.getMessage());
		}
		
		return list;
	}
}
