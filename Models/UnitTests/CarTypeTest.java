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
		int reindeer = carType.create("Rensdyrsl�de");
		int toboggan = carType.create("K�lk");
		
		assertTrue(reindeer > 0); // Test #1
		assertTrue(toboggan > 0); // Test #2
		assertEquals(2, carType.amountOfEntries()); // Test #2
		assertTrue(carType.delete(toboggan)); // Test #3
		assertEquals(1, carType.amountOfEntries()); // Test #3
	}
	
	/**
	 * Test - CarType #4
	 */
	@Test
	public void testFindType () {
		assertNotNull(carType.read("Rensdyrsl�de")); // Test #4
	}
	
	/**
	 * Test - CarType #5-7
	 */
	@Test
	public void testMultipleTypes () {
		assertFalse(carType.create("Rensdyrsl�de") > 0); // Test #5
		
		Map<String, Object> reindeer = carType.read("Rensdyrsl�de");
		int id = Integer.parseInt(reindeer.get("id").toString());
		assertTrue(carType.delete(id)); // Test #6
		
		int typeReindeer = carType.create("Rensdyrsl�de");
		assertTrue(typeReindeer > 0); // Test #7
		
		Map<String, Object> toboggan = carType.read("K�lk");
		id = Integer.parseInt(toboggan.get("id").toString());
		carType.delete(id);
		carType.delete(typeReindeer);
	}
}
