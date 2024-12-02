package objects;

import java.io.Serializable;

public class Reservation implements Serializable
{
  private int reservationID; // primary key, probably a serial in database.
  private Car car;
  private Customer customer;
  private Location pickupLocation;
  private Location returnLocation;
  private MyDate startDate;
  private MyDate endDate;

  public Reservation(int reservationID, Car car, Customer customer,
      Location pickupLocation, Location returnLocation, MyDate startDate,
      MyDate endDate)
  {
    this.reservationID = reservationID;
    this.car = car;
    this.customer = customer;
    this.pickupLocation = pickupLocation;
    this.returnLocation = returnLocation;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public int getReservationID()
  {
    return reservationID;
  }

  public Car getCar()
  {
    return car;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public Location getPickupLocation()
  {
    return pickupLocation;
  }

  public Location getReturnLocation()
  {
    return returnLocation;
  }

  public MyDate getStartDate()
  {
    return startDate;
  }

  public MyDate getEndDate()
  {
    return endDate;
  }

  public void setStartDate(MyDate startDate)
  {
    this.startDate = startDate;
  }

  public void setEndDate(MyDate endDate)
  {
    this.endDate = endDate;
  }

  @Override public boolean equals(Object o)
  {
    if (o instanceof Reservation)
    {
      Reservation other = (Reservation) o;

      return reservationID == other.reservationID && car.equals(other.car) && customer.equals(other.customer)
          && pickupLocation.equals(other.pickupLocation) && returnLocation.equals(other.returnLocation)
          && startDate.equals(other.startDate) && endDate.equals(other.endDate);
    }
    return false;
  }
}
