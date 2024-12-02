package model;

import objects.*;
import shared.Subject;

import java.util.ArrayList;

public interface CarRentModel extends Subject
{
  void addCar(Car car);
  void editCar(Car car);
  void removeCar(Car car);
  void addReservation(Reservation reservation);
  void removeReservation(Reservation reservation);
  void addCustomer(Customer customer);
  void editCustomer(Customer customer);
  void removeCustomer(Customer customer);
  void addLocation(Location location);
  void removeLocation(Location location);
  void searchCar(MyDate pickUpDate, MyDate dropOffDate, Location pickUp, Location dropOff);
  boolean validateCarReservation(Car car, MyDate pickUpDate, MyDate dropOffDate);
  void stopClient();
  ArrayList<Car> getCars();
  ArrayList<Customer> getCustomers();
  ArrayList<Location> getLocations();
  ArrayList<Reservation> getReservations();
  void askCars();
  void askCustomers();
  void askLocations();
  void askReservations();
  void customerLogin(Customer customer);
}
