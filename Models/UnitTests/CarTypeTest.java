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
		int reindeer = carType.create("Rensdyrslæde"); // Test #1
		int toboggan = carType.create("Kælk"); // Test #2
		
		assertTrue(reindeer > 0); // Test #1
		assertTrue(toboggan > 0); // Test #2
		assertEquals(prevAmount+2, carType.amountOfEntries()); // Test #2
		
		assertTrue(carType.update(toboggan, "Vinterkælk")); // Test #3
		assertNotNull(carType.read("Vinterkælk")); // Test #3
		assertNull(carType.read("Kælk")); // Test #3
		
		assertFalse(carType.update(toboggan, "Rensdyrslæde")); // Test #4
		assertNotNull(carType.read("Vinterkælk")); // Test #4
		
		assertTrue(carType.delete(toboggan)); // Test #5
		assertEquals(prevAmount+1, carType.amountOfEntries()); // Test #5
	}
	
	/**
	 * Test - CarType #4
	 */
	@Test
	public void testFindType () {
		assertNotNull(carType.read("Rensdyrslæde")); // Test #6
		assertNull(carType.read("Ukendt biltype")); // Test #7
	}
	
	/**
	 * Test - CarType #5-7
	 */
	@Test
	public void testMultipleTypes () {
		assertFalse(carType.create("Rensdyrslæde") > 0); // Test #8
		
		Map<String, Object> reindeer = carType.read("Rensdyrslæde");
		int id = Integer.parseInt(reindeer.get("id").toString());
		assertTrue(carType.delete(id)); // Test #9
		
		int typeReindeer = carType.create("Rensdyrslæde");
		assertTrue(typeReindeer > 0); // Test #10
		
		// Clean-up after tests
		Map<String, Object> toboggan = carType.read("Rensdyrslæde");
		id = Integer.parseInt(toboggan.get("id").toString());
		carType.delete(id);
		carType.delete(typeReindeer);
	}
}
