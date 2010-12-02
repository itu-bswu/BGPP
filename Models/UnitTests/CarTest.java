/**
 * Test of Car (Model)
 */
package Models.UnitTests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Models.CarType;
import Models.Car;

import java.util.Map;
import java.util.HashMap;

/**
 * @author Niklas Hansen
 *
 */
public class CarTest {

	private CarType carType;
	private int varevogn, sportsvogn;
	private Car car;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.carType = new CarType();
		this.varevogn = carType.create("Varevogn");
		this.sportsvogn = carType.create("Sportsvogn");
		this.car = new Car();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		carType.delete(varevogn);
		carType.delete(sportsvogn);
	}
	
	/**
	 * Test - Car #1-3
	 */
	@Test
	public void testCarCreate () {
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("type", varevogn);
		createVars.put("car", "Ford Transit");
		createVars.put("licenseplate", "SV 32 654");
		
		int car1 = car.create(createVars);
		
		assertTrue(car1 > 0);
		assertTrue(car.read(car1) != null);
		assertTrue(car.delete(car1));
	}
}
