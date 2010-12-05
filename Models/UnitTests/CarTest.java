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

/**
 * TODO: Write unit test javadoc.
 * TODO: Review unit tests based on CRUD-model.
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
		assertTrue(car.delete(car1)); // Test #3
	}
	
	/**
	 * Test - Car #4-7
	 */
	@Test
	public void testMultipleCars () {
		int car1 = car.create("Ford Transit", "SV 32 654", varevogn);
		int car2 = car.create("Mazda MX-5", "VS 12 345", sportsvogn);
		
		assertTrue(car1 > 0); // Test #4
		assertTrue(car2 > 0); // Test #4
		assertTrue(car.delete(car2)); // Test #5
		
		car2 = car.create("Mazda MX-5", "VS 12 345", sportsvogn);
		
		assertTrue(car2 > 0); // Test #6
		assertFalse(car.create("Mazda MX-5", "VS 12 345", sportsvogn) > 0); // Test #7
		
		car.delete(car1);
		car.delete(car2);
	}
	
	/**
	 * Test - Car #8
	 */
	@Test
	public void testUnknownType () {
		int submarine = 1337;		
		assertFalse(car.create("Ford Transit", "SV 32 654", submarine) > 0); // Test #8
	}
}
