import static org.junit.Assert.*;

import java.util.ArrayList;
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

public class BasicDoubleLinkedList_STUDENT_Test
 {
	BasicDoubleLinkedList<String> stringLink;
	BasicDoubleLinkedList<Double> doubleLink;
	BasicDoubleLinkedList<Car> linkedCar;
	StringComparator compS;
	DoubleComparator compD;
	CarComparator compCar;
	
	public Car a=new Car("Porsche", "Taycan", 2023);
	public Car b=new Car("Jeep", "Renegade", 2023);
	public Car c=new Car("Lambo", "Urus", 2023);
	public Car d=new Car("Corvette", "Stingray", 2023);
	public Car e=new Car("McLaren", "720S", 2023);
	public Car f=new Car("Honda ", "Accord", 2023);

	public ArrayList<Car> fill = new ArrayList<Car>();
	

	@Before
	public void setUp() throws Exception {
		stringLink = new BasicDoubleLinkedList<String>();
		stringLink.addToEnd("Say");
		stringLink.addToEnd("Hi");
		compS = new StringComparator();
		
		doubleLink = new BasicDoubleLinkedList<Double>();
		doubleLink.addToEnd(10.00);
		doubleLink.addToEnd(250.0);
		compD = new DoubleComparator();
		
		linkedCar= new BasicDoubleLinkedList<Car>();
		linkedCar.addToEnd(d);
		linkedCar.addToEnd(f);
		compCar = new CarComparator();
	}

	@After
	public void tearDown() throws Exception {
		stringLink = null;
		doubleLink = null;
		linkedCar = null;
		compD = null;
		compS = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,stringLink.getSize());
		assertEquals(2,doubleLink.getSize());
		assertEquals(2,linkedCar.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("Hi", stringLink.getLast());
		stringLink.addToEnd("Finish");
		assertEquals("Finish", stringLink.getLast());
		
		assertEquals(f,linkedCar.getLast());
		linkedCar.addToEnd(c);
		assertEquals(c,linkedCar.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Say", stringLink.getFirst());
		stringLink.addToFront("Start");
		assertEquals("Start", stringLink.getFirst());
		
		assertEquals(d,linkedCar.getFirst());
		linkedCar.addToFront(b);
		assertEquals(b,linkedCar.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Say", stringLink.getFirst());
		stringLink.addToFront("Start");
		assertEquals("Start", stringLink.getFirst());
		
		assertEquals(d,linkedCar.getFirst());
		linkedCar.addToFront(b);
		assertEquals(b,linkedCar.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("Hi", stringLink.getLast());
		stringLink.addToEnd("Start");
		assertEquals("Start", stringLink.getLast());
		
		assertEquals(f,linkedCar.getLast());
		linkedCar.addToEnd(c);
		assertEquals(c,linkedCar.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Car> list;
		linkedCar.addToFront(c);
		linkedCar.addToEnd(f);
		list = linkedCar.toArrayList();
		assertEquals(c,list.get(0));
		assertEquals(d,list.get(1));
		assertEquals(f,list.get(2));
		assertEquals(f,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		stringLink.addToFront("Start");
		stringLink.addToEnd("Finish");
		ListIterator<String> iterator = stringLink.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Start", iterator.next());
		assertEquals("Say", iterator.next());
		assertEquals("Hi", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Finish", iterator.next());
		
		linkedCar.addToFront(c);
		linkedCar.addToEnd(f);
		ListIterator<Car> iteratorCar = linkedCar.iterator();
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(c, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(f, iteratorCar.next());
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(f, iteratorCar.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedCar.addToFront(a);
		linkedCar.addToEnd(d);
		ListIterator<Car> iteratorCar = linkedCar.iterator();
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(a, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(f, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(true, iteratorCar.hasPrevious());
		assertEquals(d, iteratorCar.previous());
		assertEquals(f, iteratorCar.previous());
		assertEquals(d, iteratorCar.previous());
		assertEquals(a, iteratorCar.previous());
	}
	 
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedCar.addToFront(b);
		linkedCar.addToEnd(e);
		ListIterator<Car> iteratorCar = linkedCar.iterator();		
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(b, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(f, iteratorCar.next());
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(e, iteratorCar.next());
		
		try{
			//no more elements in list
			iteratorCar.next();
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedCar.addToFront(b);
		linkedCar.addToEnd(f);
		ListIterator<Car> iteratorCar = linkedCar.iterator();		
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(b, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(f, iteratorCar.next());
		assertEquals(f, iteratorCar.next());
		assertEquals(true, iteratorCar.hasPrevious());
		assertEquals(f, iteratorCar.previous());
		assertEquals(f, iteratorCar.previous());
		assertEquals(d, iteratorCar.previous());
		assertEquals(b, iteratorCar.previous());
		
		try{
			//no more elements in list
			iteratorCar.previous();
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
	public void testIteratorUnsupportedOperationException() {
		linkedCar.addToFront(b);
		linkedCar.addToEnd(e);
		ListIterator<Car> iteratorCar = linkedCar.iterator();		
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(b, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(f, iteratorCar.next());
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(e, iteratorCar.next());
		
		try{
			//remove is not supported for the iterator
			iteratorCar.remove();
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
	public void testRemove() {
		// remove the first
		assertEquals(d, linkedCar.getFirst());
		assertEquals(f, linkedCar.getLast());
		linkedCar.addToFront(a);
		assertEquals(a, linkedCar.getFirst());
		linkedCar.remove(a, compCar);
		assertEquals(d, linkedCar.getFirst());
		//remove from the end of the list
		linkedCar.addToEnd(c);
		assertEquals(c, linkedCar.getLast());
		linkedCar.remove(c, compCar);
		assertEquals(f, linkedCar.getLast());
		//remove from middle of list
		linkedCar.addToFront(b);
		assertEquals(b, linkedCar.getFirst());
		assertEquals(f, linkedCar.getLast());
		linkedCar.remove(b, compCar);
		assertEquals(d, linkedCar.getFirst());
		assertEquals(f, linkedCar.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(d, linkedCar.getFirst());
		linkedCar.addToFront(a);
		assertEquals(a, linkedCar.getFirst());
		assertEquals(a, linkedCar.retrieveFirstElement());
		assertEquals(d,linkedCar.getFirst());
		assertEquals(d, linkedCar.retrieveFirstElement());
		assertEquals(f,linkedCar.getFirst());
		
		assertEquals("Say", stringLink.getFirst());
		stringLink.addToFront("Start");
		assertEquals("Start", stringLink.getFirst());
		assertEquals("Start", stringLink.retrieveFirstElement());
		assertEquals("Say",stringLink.getFirst());
		assertEquals("Say", stringLink.retrieveFirstElement());
		assertEquals("Hi",stringLink.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(f, linkedCar.getLast());
		linkedCar.addToEnd(c);
		assertEquals(c, linkedCar.getLast());
		assertEquals(c, linkedCar.retrieveLastElement());
		assertEquals(f,linkedCar.getLast());
		
		assertEquals("Hi", stringLink.getLast());
		stringLink.addToEnd("Start");
		assertEquals("Start", stringLink.getLast());
		assertEquals("Start", stringLink.retrieveLastElement());
		assertEquals("Hi",stringLink.getLast());
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
		public int compare(Car arg0, Car arg1) {
			// Just put cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Car{
		String car_make;
		String car_model;
		int car_y;
		
		public Car(String make, String model, int year){
			this.car_make = make;
			this.car_model = model;
			this.car_y = year;
		}
		
		public String getMake(){
			return car_make;
		}
		public String getModel(){
			return car_model;
		}
		public int getYear(){
			return car_y;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}
}