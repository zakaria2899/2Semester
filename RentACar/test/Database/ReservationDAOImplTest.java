package Database;

import objects.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDAOImplTest
{
  String licensePlate = "ABC1295";
  String brand = "Toyota";
  String model = "Corolla";
  String description = "Compact car";
  double dailyRentalPrice = 50.0;
  int year = 2022;
  int seatingCapacity = 5;
  int greenlevel = 3;
  String fuelType = "Diesel";
  Car car = new Car(licensePlate, brand, model, description, year, seatingCapacity, greenlevel, fuelType, dailyRentalPrice, new Location("Test"));
  Customer customer = new Customer("124222-1314", new MyDate(26,3,2003), "Martin", "Feldt",
      "Test addresse", "Horsens", 2233, "12121212", "Test@Test.dk");

  @Test void create() throws SQLException
  {
    ReservationDAOImpl.getInstance().create(
        new Reservation(0, car, customer, new Location("Test"), new Location("Test"), new MyDate(), new MyDate()));
  }

  @Test void getAllReservations() throws SQLException
  {
    List<Reservation> reservations = new ArrayList<>();
    reservations.addAll(ReservationDAOImpl.getInstance().getAllReservations());
    for (int i = 0; i < reservations.size(); i++)
    {
      System.out.println(reservations.get(i).getReservationID());
    }
  }

  @Test void delete() throws SQLException
  {
    ReservationDAOImpl.getInstance().delete(
        new Reservation(4, car, customer, new Location("Test"), new Location("Test"), new MyDate(), new MyDate()));
  }
}