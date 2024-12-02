package server.Model;

import Database.CarDAOImpl;
import Database.CustomerDAOImpl;
import Database.LocationDAOImpl;
import Database.ReservationDAOImpl;
import objects.Car;
import objects.Customer;
import objects.Location;
import objects.Reservation;
import shared.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarRentModelImpl implements CarRentModel
{
  private ArrayList<Car> cars;
  private ArrayList<Customer> customers;
  private ArrayList<Location> locations;
  private ArrayList<Reservation> reservations;

  private PropertyChangeSupport support;

  public CarRentModelImpl() throws SQLException
  {
    cars = new ArrayList<>();
    customers = new ArrayList<>();
    locations = new ArrayList<>();
    reservations = new ArrayList<>();
    support = new PropertyChangeSupport(this);

    askLocations();
    askCars();
    askCustomers();
    askReservations();
  }

  public synchronized void addCar(Car car) throws SQLException
  {
    boolean found = false;
    for (Car c : cars)
    {
      if (c.getPlate().equals(car.getPlate()))
      {
        found = true;
        break;
      }
    }
    if (!found)
    {
      CarDAOImpl.getInstance().create(car);
      askLocations();
      askCars();
    }
    support.firePropertyChange("carlist", null, cars);
  }

  public synchronized void editCar(Car car) throws SQLException
  {
    for (Car c : cars)
    {
      if (c.getPlate().equals(car.getPlate()))
      {
        CarDAOImpl.getInstance().update(c);
        break;
      }
    }
    askLocations();
    askCars();
    support.firePropertyChange("carlist", null, cars);
  }

  public synchronized void removeCar(Car car) throws SQLException
  {
    for (Car c : cars)
    {
      if (c.getPlate().equals(car.getPlate()))
      {
        CarDAOImpl.getInstance().delete(c);
        break;
      }
    }
    askLocations();
    askCars();
    support.firePropertyChange("carlist", null, cars);
  }

  public synchronized void addReservation(Reservation reservation) throws SQLException
  {
    ReservationDAOImpl.getInstance().create(reservation);
    askReservations();
    support.firePropertyChange("reservations", null, reservations);
  }

  public synchronized void removeReservation(Reservation reservation) throws SQLException
  {
    ReservationDAOImpl.getInstance().delete(reservation);
    askReservations();
    support.firePropertyChange("reservations", null, reservations);
  }

  public synchronized void addCustomer(Customer customer) throws SQLException
  {
    boolean found = false;
    for (Customer c : customers)
    {
      if (c.getCprNumber().equals(customer.getCprNumber()))
      {
        found = true;
        break;
      }
    }
    if (!found)
    {
      CustomerDAOImpl.getInstance().create(customer);
      askCustomers();
    }
    support.firePropertyChange("customerlist", null, customers);
  }

  public synchronized void editCustomer(Customer customer) throws SQLException
  {
    for (Customer c : customers)
    {
      if (c.getCprNumber().equals(customer.getCprNumber()))
      {
        CustomerDAOImpl.getInstance().update(c);
        break;
      }
    }
    askCustomers();
    support.firePropertyChange("customerlist", null, customers);
  }

  public synchronized void removeCustomer(Customer customer) throws SQLException
  {
    for (Customer c : customers)
    {
      if (c.getCprNumber().equals(customer.getCprNumber()))
      {
        CustomerDAOImpl.getInstance().delete(c);
        break;
      }
    }
    askCustomers();

    support.firePropertyChange("customerlist", null, customers);
  }

  public synchronized void addLocation(Location location) throws SQLException
  {
    boolean a = false;
    for (Location loc : locations)
    {
      if (loc.getLocation().equals(location.getLocation()))
      {
        a = true;
        break;
      }
    }
    if (!a)
    {
      LocationDAOImpl.getInstance().create(location);
      askLocations();
    }

    support.firePropertyChange("locations", null, locations);
  }

  public synchronized void removeLocation(Location location) throws SQLException
  {
    boolean found = false;
    for (Location loc : locations)
    {
      if (loc.getLocation().equals(location.getLocation()))
      {
        found = true;
        break;
      }
    }
    if (found)
    {
      LocationDAOImpl.getInstance().delete(location);
      askLocations();
      support.firePropertyChange("locations", null, locations);
    }
  }

  @Override public void AddObserver(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener("name", listener);
  }

  @Override public void RemoveObserver(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener("name", listener);
  }

  @Override public void AddObserver(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void RemoveObserver(PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  public synchronized ArrayList<Car> getCars()
  {
    return cars;
  }

  public synchronized ArrayList<Customer> getCustomers()
  {
    return customers;
  }

  public synchronized ArrayList<Location> getLocations()
  {
    return locations;
  }

  public synchronized ArrayList<Reservation> getReservations()
  {
    return reservations;
  }

  public synchronized void askCars() throws SQLException
  {
    cars.clear();
    cars.addAll(CarDAOImpl.getInstance().getAllCars());
    support.firePropertyChange("carlist", null, cars);
  }

  public void askCustomers() throws SQLException
  {
    customers.clear();
    customers.addAll(CustomerDAOImpl.getInstance().getAllCustomers());
    support.firePropertyChange("customerlist", null, customers);
  }

  public void askLocations() throws SQLException
  {
    locations.clear();
    locations.addAll(LocationDAOImpl.getInstance().getAllLocations());
    support.firePropertyChange("locations", null, locations);
  }

  public void askReservations() throws SQLException
  {
    reservations.clear();
    reservations.addAll(ReservationDAOImpl.getInstance().getAllReservations());
    support.firePropertyChange("reservations", null, reservations);
  }
}
