package client.network;

import objects.Car;
import objects.Customer;
import objects.Location;
import objects.Reservation;
import shared.Subject;

public interface Client extends Subject
{
  void startClient();
  void addCar(Car car);
  void editCar(Car car);
  void removeCar(Car car);
  void addCustomer(Customer customer);
  void editCustomer(Customer customer);
  void removeCustomer(Customer customer);
  void addLocation(Location location);
  void removeLocation(Location location);
  void addReservation(Reservation reservation);
  void removeReservation(Reservation reservation);
  void getCarList();
  void getCustomerList();
  void getLocations();
  void getReservations();
  void stop();



}
