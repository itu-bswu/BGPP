/**
 * Test of Reservation (Model)
 */
package Models.UnitTests;


import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Models.Reservation;
import Models.CarType;
import Models.Car;
import Models.Customer;

import java.sql.Date;
import java.util.List;

/**
 * @author Niklas Hansen
 *
 */
public class ReservationTest {

	private CarType carType;
	private int varevogn, sportsvogn;
	private Car car;
	private int SV12345, SV23456, XS98654;
	
	private Reservation reservation;
	private Customer customer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.carType = new CarType();
		this.car = new Car();
		
		this.varevogn = carType.create("Varevogn");
		this.sportsvogn = carType.create("Sportsvogn");
		
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("type", varevogn);
		createVars.put("car", "Ford Transit");
		createVars.put("licenseplate", "SV 12 345");
		this.SV12345 = car.create(createVars);
		
		createVars.put("licenseplate", "SV 23 456");
		this.SV23456 = car.create(createVars);
		
		createVars = new HashMap<String, Object>();
		createVars.put("type", sportsvogn);
		createVars.put("car", "Mazda MX-5");
		createVars.put("licenseplate", "XS 98 654");
		this.XS98654 = car.create(createVars);
		
		this.reservation = new Reservation();
		this.customer = new Customer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		car.delete(SV12345);
		car.delete(SV23456);
		car.delete(XS98654);
		
		carType.delete(varevogn);
		carType.delete(sportsvogn);
		
		int customer1 = Integer.parseInt(customer.read(43218765, true).get("id").toString());
		reservation.delete(Integer.parseInt(reservation.list(customer1).get(0).get("id").toString()));
		customer.delete(customer1);
		customer.delete(Integer.parseInt(customer.read(45612378, true).get("id").toString()));
	}

	/**
	 * Test - Reservation #1-3
	 */
	@Test
	public void testCreateReservations () {
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("car", varevogn);
		createVars.put("customerPhone", 43218765);
		createVars.put("customerName", "Jens Ole");
		createVars.put("startDate", Date.valueOf("2010-12-16"));
		createVars.put("startDate", Date.valueOf("2010-12-19"));
		assertTrue(reservation.create(createVars) > 0); // Test #1
		
		int prevAmount = customer.amountOfEntries();
		
		createVars.put("car", sportsvogn);
		createVars.put("startDate", Date.valueOf("2010-12-17"));
		createVars.put("startDate", Date.valueOf("2010-12-23"));
		assertTrue(reservation.create(createVars) > 0); // Test #2
		assertEquals(prevAmount, customer.amountOfEntries()); // Test #2
		
		createVars = new HashMap<String, Object>();
		createVars.put("car", varevogn);
		createVars.put("customerPhone", 99999999);
		createVars.put("startDate", Date.valueOf("2010-12-18"));
		createVars.put("startDate", Date.valueOf("2010-12-19"));
		assertFalse(reservation.create(createVars) > 0); // Test #3
	}
	
	/**
	 * Test - Reservation #4-7
	 */
	@Test
	public void testFindReservations () {
		int customer1 = Integer.parseInt(customer.read(43218765, true).get("id").toString());
		List<Map<String, Object>> reservationList = reservation.list(customer1);
		assertEquals(reservationList.size(), 2); // Test #4
		
		int reservationToDelete = Integer.parseInt(reservationList.get(0).get("id").toString());
		assertTrue(reservation.delete(reservationToDelete)); // Test #5
		
		assertEquals(reservation.list(customer1).size(), 1); // Test #6
		
		int customer2 = Integer.parseInt(customer.read(99999999, true).get("id").toString());
		assertNull(reservation.list(customer2)); // Test #7
		assertEquals(reservation.list(customer2).size(), 0); // Test #7
	}
	
	/**
	 * Test - Reservation #8
	 */
	@Test
	public void testUnknown () {
		int unknownCar = 1337;
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("car", unknownCar);
		createVars.put("customerPhone", 43218765);
		createVars.put("customerName", "Jens Ole");
		createVars.put("startDate", Date.valueOf("2010-12-16"));
		createVars.put("startDate", Date.valueOf("2010-12-19"));
		assertFalse(reservation.create(createVars) > 0); // Test #8
	}
	
	/**
	 * Test - Reservation #9
	 */
	@Test
	public void testDoubleReservation () {
		int prevAmount = customer.list().size();
		
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("car", sportsvogn);
		createVars.put("customerPhone", 45612378);
		createVars.put("customerName", "Børge Karlsen");
		createVars.put("startDate", Date.valueOf("2010-12-17"));
		createVars.put("startDate", Date.valueOf("2010-12-18"));
		assertFalse(reservation.create(createVars) > 0); // Test #9
		assertEquals(prevAmount, customer.list().size()); // Test #9
	}
	
	/**
	 * Test - Reservation #10-11
	 */
	@Test
	public void testCreationAndDeletion () {
		Map<String, Object> createVars = new HashMap<String, Object>();
		createVars.put("car", varevogn);
		createVars.put("customerPhone", 45612378);
		createVars.put("customerName", "Børge Karlsen");
		createVars.put("startDate", Date.valueOf("2010-12-16"));
		createVars.put("startDate", Date.valueOf("2010-12-19"));
		int reservation1 = reservation.create(createVars);
		assertTrue(reservation1 > 0); // Test #10
		assertTrue(reservation.delete(reservation1));
	}
}