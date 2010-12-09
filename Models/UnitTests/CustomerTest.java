/**
 * Test of Customer (Model)
 */
package Models.UnitTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Models.Customer;

/**
 * @author Niklas Hansen
 *
 */
public class CustomerTest {

	private Customer customer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.customer = new Customer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}
	
	/**
	 * Test - Customer #1-3
	 */
	@Test
	public void testCreateCustomer () {
		int prevAmount = customer.amountOfEntries();
		int poulKrebs = customer.create("Poul Krebs", 87654321);
		
		assertTrue(poulKrebs > 0); // Test #1
		assertEquals(prevAmount+1, customer.amountOfEntries()); // Test #1
		assertNotNull(customer.read(poulKrebs)); // Test #2
		assertNotNull(customer.read(87654321, true)); // Test #2
		assertTrue(customer.update(poulKrebs, "Poul Krebs", 12345678)); // Test #3
		assertNotNull(customer.read(12345678, true)); // Test #3
		assertNull(customer.read(87654321, true)); // Test #4
		assertTrue(customer.delete(poulKrebs)); // Test #5
		assertEquals(prevAmount, customer.amountOfEntries()); // Test #5
	}
	
	/**
	 * Test - Customer #4-6
	 */
	@Test
	public void testMultipleCustomers () {
		assertTrue(customer.create("Poul Krebs", 12345678) > 0); // Test #6
		assertFalse(customer.create("Søren Banjomus", 12345678) > 0); // Test #7
		
		int banjomus = customer.create("Søren Banjomus", 87654321);
		assertTrue(banjomus > 0); // Test #8
		
		Map<String, Object> updateVars = new HashMap<String, Object>();
		updateVars.put("phone", 12345678);
		assertFalse(customer.update(banjomus, updateVars)); // Test #9
		assertNotNull(customer.read(87654321, true)); // Test #9
	}
	
	/**
	 * Test - Customer #7-11
	 */
	@Test
	public void testFindAndDeleteCustomers () {
		int customer1 = Integer.parseInt(customer.read(12345678, true).get("id").toString());
		assertTrue(customer.delete(customer1)); // Test #10
		assertNotNull(customer.read(87654321, true)); // Test #11
		int customer2 = Integer.parseInt(customer.read(87654321, true).get("id").toString());
		assertTrue(customer.delete(customer2)); // Test #12
		assertNull(customer.read(87654321, true)); // Test #13
		assertTrue(customer.create("Søren Banjomus", 87654321) > 0); // Test #14
		assertNotNull(customer.read(87654321, true)); // Test #14
		
		customer2 = Integer.parseInt(customer.read(87654321, true).get("id").toString());
		customer.delete(customer2);
	}
}
