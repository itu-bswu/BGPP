/**
 * Test of Car (Model)
 */
package Models.UnitTests;


import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Models.CarType;
import Models.Car;

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
		int car1 = car.create("Ford Transit", "SV 32 654", varevogn);
		
		assertTrue(car1 > 0); // Test #1
		assertNotNull(car.read(car1)); // Test #2
		assertEquals("SV32654", car.read(car1).get("licensePlate") ); // Test #2
		
		Map<String, Object> updateVars = new HashMap<String, Object>();
		updateVars.put("licensePlate", "XS 09 876");
		assertTrue(car.update(car1, updateVars)); // Test #3
		assertEquals("XS09876", car.read(car1).get("licensePlate") ); // Test #3
		
		assertTrue(car.delete(car1)); // Test #4
	}
	
	/**
	 * Test - Car #4-7
	 */
	@Test
	public void testMultipleCars () {
		int car1 = car.create("Ford Transit", "SV 32 654", varevogn);
		int car2 = car.create("Mazda MX-5", "VS 12 345", sportsvogn);
		
		assertTrue(car1 > 0); // Test #5
		assertTrue(car2 > 0); // Test #5
		assertTrue(car.delete(car2)); // Test #6
		
		car2 = car.create("Mazda MX-5", "VS 12 345", sportsvogn);
		
		assertTrue(car2 > 0); // Test #7
		assertFalse(car.create("Mazda MX-5", "VS 12 345", sportsvogn) > 0); // Test #8
		
		car.delete(car1);
		car.delete(car2);
	}
	
	/**
	 * Test - Car #8
	 */
	@Test
	public void testUnknownType () {
		int submarine = 1337;		
		assertFalse(car.create("Ford Transit", "SV 32 654", submarine) > 0); // Test #9
	}
}
