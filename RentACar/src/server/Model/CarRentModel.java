package server.Model;

import objects.*;
import shared.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarRentModel extends Subject
{
  void addCar(Car car) throws SQLException;
  void editCar(Car car) throws SQLException;
  void removeCar(Car car) throws SQLException;
  void addReservation(Reservation reservation) throws SQLException;
  void removeReservation(Reservation reservation) throws SQLException;
  void addCustomer(Customer customer) throws SQLException;
  void editCustomer(Customer customer) throws SQLException;
  void removeCustomer(Customer customer) throws SQLException;
  void addLocation(Location location) throws SQLException;
  void removeLocation(Location location) throws SQLException;
  ArrayList<Car> getCars();
  ArrayList<Customer> getCustomers();
  ArrayList<Location> getLocations();
  ArrayList<Reservation> getReservations();
  void askCars() throws SQLException;
  void askCustomers() throws SQLException;
  void askLocations() throws SQLException;
  void askReservations() throws SQLException;
}
