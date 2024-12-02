package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest
{
  Car car = new Car("AA12555", "mærke", "model", "a little description", 2018, 5, 3, "diesel", 2500, new Location("Vejle"));
  Customer customer = new Customer("011221-2921", new MyDate(), "Test name", "Test lastname", "test address", "test city", 1234, "12121212", "test@test.com");
  Location location = new Location("Test");
  MyDate myDate = new MyDate();
  MyDate myDate2 = new MyDate();
  Reservation reservation = new Reservation(0, car, customer, location, location, myDate, myDate2);

  @Test void getReservationID()
  {
    assertEquals(0, reservation.getReservationID());
  }

  @Test void getCar()
  {
    assertEquals(car, reservation.getCar());
  }

  @Test void getCustomer()
  {
    assertEquals(customer, reservation.getCustomer());
  }

  @Test void getPickupLocation()
  {
    assertEquals(location, reservation.getPickupLocation());
  }

  @Test void getReturnLocation()
  {
    assertEquals(location, reservation.getReturnLocation());
  }

  @Test void getStartDate()
  {
    assertEquals(myDate, reservation.getStartDate());
  }

  @Test void getEndDate()
  {
    assertEquals(myDate2, reservation.getEndDate());
  }

  @Test void setStartDate()
  {
    MyDate myDate3 = new MyDate(12,12,2012);
    reservation.setStartDate(myDate3);
    assertEquals(myDate3, reservation.getStartDate());

  }

  @Test void setEndDate()
  {
    MyDate myDate3 = new MyDate(12,12,2012);
    reservation.setEndDate(myDate3);
    assertEquals(myDate3, reservation.getEndDate());
  }

  @Test void testEquals()
  {
    Reservation reservation2 = new Reservation(reservation.getReservationID(), reservation.getCar(), reservation.getCustomer(), reservation.getPickupLocation(), reservation.getReturnLocation(), reservation.getStartDate(), reservation.getEndDate());
    assertTrue(reservation.equals(reservation2));
    reservation.setStartDate(new MyDate());
    assertTrue(reservation.equals(reservation2));
    reservation.setStartDate(new MyDate(12,1,2012));
    assertFalse(reservation.equals(reservation2));
  }
}