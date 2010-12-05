/**
 * Test of Customer (Model)
 */
package Models.UnitTests;

import static org.junit.Assert.*;

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
		int poulKrebs = customer.create("Poul Krebs", 12345678);
		
		assertTrue(poulKrebs > 0); // Test #1
		assertNotNull(customer.read(poulKrebs)); // Test #2
		// Update
		assertTrue(customer.delete(poulKrebs)); // Test #3
	}
	
	/**
	 * Test - Customer #4-6
	 */
	@Test
	public void testMultipleCustomers () {
		assertTrue(customer.create("Poul Krebs", 12345678) > 0); // Test #4
		assertFalse(customer.create("Søren Banjomus", 12345678) > 0); // Test #5
		assertTrue(customer.create("Søren Banjomus", 87654321) > 0); // Test #6
	}
	
	/**
	 * Test - Customer #7-11
	 */
	@Test
	public void testFindAndDeleteCustomers () {
		int customer1 = Integer.parseInt(customer.read(12345678, true).get("id").toString());
		assertTrue(customer.delete(customer1)); // Test #7
		assertNotNull(customer.read(87654321, true)); // Test #8
		int customer2 = Integer.parseInt(customer.read(87654321, true).get("id").toString());
		assertTrue(customer.delete(customer2)); // Test #9
		assertNull(customer.read(87654321, true)); // Test #10
		assertTrue(customer.create("Søren Banjomus", 87654321) > 0); // Test #11
		assertNotNull(customer.read(87654321, true)); // Test #11
		
		customer2 = Integer.parseInt(customer.read(87654321, true).get("id").toString());
		customer.delete(customer2);
	}
}
