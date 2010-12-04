/**
 * Test of CarType (Model)
 */
package Models.UnitTests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Models.CarType;

import java.util.Map;

/**
 * @author Niklas Hansen
 *
 */
public class CarTypeTest {
	
	private CarType carType;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.carType = new CarType();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}
	
	/**
	 * Test - CarType #1-3
	 */
	@Test
	public void testTypeCreate () {
		int prevAmount = carType.amountOfEntries();
		int reindeer = carType.create("Rensdyrsl�de"); // Test #1
		int toboggan = carType.create("K�lk"); // Test #2
		
		assertTrue(reindeer > 0); // Test #1
		assertTrue(toboggan > 0); // Test #2
		assertEquals(prevAmount+2, carType.amountOfEntries()); // Test #2
		assertTrue(carType.delete(toboggan)); // Test #3
		assertEquals(prevAmount+1, carType.amountOfEntries()); // Test #3
	}
	
	/**
	 * Test - CarType #4
	 */
	@Test
	public void testFindType () {
		assertNotNull(carType.read("Rensdyrsl�de")); // Test #4
		assertNull(carType.read("Ukendt biltype")); // Test #5
	}
	
	/**
	 * Test - CarType #5-7
	 */
	@Test
	public void testMultipleTypes () {
		assertFalse(carType.create("Rensdyrsl�de") > 0); // Test #6
		
		Map<String, Object> reindeer = carType.read("Rensdyrsl�de");
		int id = Integer.parseInt(reindeer.get("id").toString());
		assertTrue(carType.delete(id)); // Test #7
		
		int typeReindeer = carType.create("Rensdyrsl�de");
		assertTrue(typeReindeer > 0); // Test #8
		
		// Clean-up after tests
		Map<String, Object> toboggan = carType.read("Rensdyrsl�de");
		id = Integer.parseInt(toboggan.get("id").toString());
		carType.delete(id);
		carType.delete(typeReindeer);
	}
}
