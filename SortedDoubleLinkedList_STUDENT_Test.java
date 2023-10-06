import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Talia
 *
 */
public class SortedDoubleLinkedList_STUDENT_Test {
	
	SortedDoubleLinkedList<String> linkedSortedString;
	SortedDoubleLinkedList<Double> linkedSortedDouble;
	SortedDoubleLinkedList<Car> linkedSortedCar;
	StringComparator compS;
	DoubleComparator compD;
	CarComparator compCar;
	
	public Car a=new Car("Mercedes-Benz", "GClass", 2023);
	public Car b=new Car("Jeep", "Renegade", 2023);
	public Car c=new Car("Toyota", "Crown", 2023);
	public Car d=new Car("Honda", "Civic", 2023);
	public Car e=new Car("Chevrolet", "Silverado", 2023);
	public Car f=new Car("Chrysler ", "Pacifica Touring-L", 2023);
	//alphabetic order: e f a c b d
	
	@Before
	public void setUp() throws Exception {
		compS = new StringComparator();
		linkedSortedString = new SortedDoubleLinkedList<String>(compS);
		
		compD = new DoubleComparator();
		linkedSortedDouble = new SortedDoubleLinkedList<Double>(compD);
		
		compCar = new CarComparator();
		linkedSortedCar = new SortedDoubleLinkedList<Car>(compCar);
		
	}

	@After
	public void tearDown() throws Exception {
		compS = null;
		compD = null;
		compCar = null;
		linkedSortedString = null;
		linkedSortedDouble = null;
		linkedSortedCar = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			linkedSortedString.addToEnd("Say_Hi");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertFalse("Didn't Successfully threw an UnsupportedOperationException", false);
		}
		catch (Exception e)
		{
			assertTrue("Did throw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			linkedSortedString.addToFront("Love_her");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertFalse("Didn't Successfully threw an UnsupportedOperationException", false);
		}
		catch (Exception e)
		{
			assertTrue("Did Throw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		linkedSortedCar.add(a);
		linkedSortedCar.add(b);
		linkedSortedCar.add(b);
		linkedSortedCar.add(e);
		ListIterator<Car> iterator = linkedSortedCar.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		linkedSortedString.add("Begin");
		linkedSortedString.add("World");
		linkedSortedString.add("Hello");
		linkedSortedString.add("Zebra");
		ListIterator<String> iterator = linkedSortedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals("Zebra", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zebra", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulCarPrevious() {
		linkedSortedCar.add(e);
		linkedSortedCar.add(c);
		linkedSortedCar.add(b);
		linkedSortedCar.add(d);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Car> iterator = linkedSortedCar.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(c, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(d, iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulDoubleNext() {
		linkedSortedDouble.add((Double)8.0);
		linkedSortedDouble.add((Double)6.0);
		linkedSortedDouble.add((Double)1.0);
		linkedSortedDouble.add((Double)2.0);
		ListIterator<Double> iterator = linkedSortedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals((Double)1.0, iterator.next());
		assertEquals((Double)2.0, iterator.next());
		assertEquals((Double)6.0, iterator.next());
		assertEquals(true, iterator.hasNext());	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		linkedSortedDouble.add((Double)5.0);
		linkedSortedDouble.add((Double)10.0);
		linkedSortedDouble.add((Double)8.0);
		linkedSortedDouble.add((Double)2.0);
		ListIterator<Double> iterator = linkedSortedDouble.iterator();
		assertEquals((Double)2.0, iterator.next());
		assertEquals((Double)5.0, iterator.next());
		assertEquals((Double)8.0, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals((Double)8.0, iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		linkedSortedCar.add(e);
		linkedSortedCar.add(c);
		linkedSortedCar.add(b);
		linkedSortedCar.add(d);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Car> iterator = linkedSortedCar.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		linkedSortedCar.add(e);
		linkedSortedCar.add(c);
		linkedSortedCar.add(b);
		linkedSortedCar.add(d);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Car> iterator = linkedSortedCar.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddCar() {
		//alphabetic order: e f a c b d
		linkedSortedCar.add(f);
		linkedSortedCar.add(b);
		linkedSortedCar.add(d);
		assertEquals(f, linkedSortedCar.getFirst());
		assertEquals(b, linkedSortedCar.getLast());
		linkedSortedCar.add(c);
		linkedSortedCar.add(e);
		assertEquals(e, linkedSortedCar.getFirst());
		assertEquals(c, linkedSortedCar.getLast());
		//deletes Elephant from linked list
		assertEquals(c,linkedSortedCar.retrieveLastElement());
		assertEquals(b, linkedSortedCar.getLast());
	}

	@Test
	public void testRemoveFirstCar() {
		//alphabetic order: e f a c b d
		linkedSortedCar.add(d);
		linkedSortedCar.add(f);
		assertEquals(f, linkedSortedCar.getFirst());
		assertEquals(d, linkedSortedCar.getLast());
		linkedSortedCar.add(c);
		assertEquals(f, linkedSortedCar.getFirst());
		// remove the first
		linkedSortedCar.remove(c, compCar);
		assertEquals(f, linkedSortedCar.getFirst());
	}
	
	@Test
	public void testRemoveEndCar() {
		//alphabetic order: e f a c b d
		linkedSortedCar.add(e);
		linkedSortedCar.add(d);
		assertEquals(e, linkedSortedCar.getFirst());
		assertEquals(d, linkedSortedCar.getLast());
		linkedSortedCar.add(c);
		assertEquals(c, linkedSortedCar.getLast());
		//remove from the end of the list
		linkedSortedCar.remove(d, compCar);
		assertEquals(c, linkedSortedCar.getLast());
	}

	@Test
	public void testRemoveMiddleCar() {
		//alphabetic order: e f a c b d
		linkedSortedCar.add(c);
		linkedSortedCar.add(d);
		assertEquals(d, linkedSortedCar.getFirst());
		assertEquals(c, linkedSortedCar.getLast());
		linkedSortedCar.add(e);
		assertEquals(e, linkedSortedCar.getFirst());
		assertEquals(c, linkedSortedCar.getLast());
		assertEquals(3,linkedSortedCar.getSize());
		//remove from middle of list
		linkedSortedCar.remove(a, compCar);
		assertEquals(e, linkedSortedCar.getFirst());
		assertEquals(c, linkedSortedCar.getLast());
		assertEquals(3,linkedSortedCar.getSize());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String stat0, String stat1) {
			// TODO Auto-generated method stub
			return stat0.compareTo(stat1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double stat0, Double stat1) {
			// TODO Auto-generated method stub
			return stat0.compareTo(stat1);
		}
		
	}
	
	private class CarComparator implements Comparator<Car>
	{

		@Override
		public int compare(Car stat0, Car stat1) {
			// Just put cars in alphabetic order by make
			return stat0.getMake().compareTo(stat1.getMake());
		}		
	}
	
	private class Car{
		String car_make;
		String car_model;
		int car_year;
		
		public Car(String make, String model, int year){
			this.car_make = make;
			this.car_model = model;
			this.car_year = year;
		}
		
		public String getMake(){
			return car_make;
		}
		public String getModel(){
			return car_model;
		}
		public int getYear(){
			return car_year;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}
}