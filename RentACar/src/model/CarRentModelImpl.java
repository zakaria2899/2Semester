package model;

import client.network.Client;
import objects.*;
import client.network.SocketClient;
import shared.Subject;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class CarRentModelImpl implements CarRentModel
{
  private ArrayList<Car> cars;
  private ArrayList<Customer> customers;
  private ArrayList<Location> locations;
  private ArrayList<Reservation> reservations;
  private Client client;

  private PropertyChangeSupport support;

  public CarRentModelImpl(Client client)
  {
    this.client = client;
    client.AddObserver("carlist", this::onNewCarList);
    client.AddObserver("customerlist", this::onNewCustomerList);
    client.AddObserver("reservations", this::onNewReservations);
    client.AddObserver("locations", this::onNewLocations);

    cars = new ArrayList<>();
    customers = new ArrayList<>();
    locations = new ArrayList<>();
    reservations = new ArrayList<>();

    support = new PropertyChangeSupport(this);
  }

  private void onNewLocations(PropertyChangeEvent propertyChangeEvent)
  {
    if (locations != null)
    {
      locations.clear();
    }
    locations.addAll((List<Location>) propertyChangeEvent.getNewValue());
    support.firePropertyChange("locations",null, locations);
  }

  private void onNewReservations(PropertyChangeEvent propertyChangeEvent)
  {
    if (reservations != null)
    {
      reservations.clear();
    }
    reservations.addAll((List<Reservation>)propertyChangeEvent.getNewValue());
    support.firePropertyChange("reservations",null, reservations);
  }

  private void onNewCustomerList(PropertyChangeEvent propertyChangeEvent)
  {
    if (customers != null)
    {
      customers.clear();
    }
    customers.addAll((List<Customer>) propertyChangeEvent.getNewValue());
    support.firePropertyChange("customers",null, customers);
  }

  private void onNewCarList(PropertyChangeEvent propertyChangeEvent)
  {
    if (cars != null)
    {
      cars.clear();
    }
    cars.addAll((List<Car>) propertyChangeEvent.getNewValue());
    support.firePropertyChange("cars",null, cars);
  }

  public synchronized void addCar(Car car)
  {
    client.addCar(car);
  }

  public synchronized void editCar(Car car)
  {
    client.editCar(car);
  }

  public synchronized void removeCar(Car car)
  {
    client.removeCar(car);
  }


  public synchronized void addReservation(Reservation reservation)
  {
    client.addReservation(reservation);
  }

  public synchronized void removeReservation(Reservation reservation)
  {
    client.removeReservation(reservation);
  }


  public synchronized void addCustomer(Customer customer)
  {
    client.addCustomer(customer);
  }

  public synchronized void editCustomer(Customer customer)
  {
    client.editCustomer(customer);
  }

  public synchronized void removeCustomer(Customer customer)
  {
    client.removeCustomer(customer);
  }

  public synchronized void addLocation(Location location)
  {
    client.addLocation(location);
  }

  public synchronized void removeLocation(Location location)
  {
    client.removeLocation(location);
  }

  public synchronized void searchCar(MyDate pickUpDate, MyDate dropOffDate, Location pickUp, Location dropOff)
  {
    ArrayList filteredCarList = new ArrayList();
    for (Car car : cars)
    {
      if (validateCarReservation(car, pickUpDate, dropOffDate))
      {
        filteredCarList.add(car);
      }
    }
    ArrayList<Object> searchstuff = new ArrayList<>();
    searchstuff.add(pickUpDate);
    searchstuff.add(dropOffDate);
    searchstuff.add(pickUp);
    searchstuff.add(dropOff);
    support.firePropertyChange("CarListWithDate", null, filteredCarList);
    support.firePropertyChange("CarSearch", null, searchstuff);
  }

  public synchronized boolean validateCarReservation(Car car, MyDate pickUpDate, MyDate dropOffDate)
  {
    int check = 0;
    ArrayList<Reservation> reservations = new ArrayList<>();
    for (Reservation reservation : this.reservations)
    {
      if (reservation.getCar().getPlate().equals(car.getPlate()))
      {
        reservations.add(reservation);
      }
    }

    for (Reservation reservation : reservations)
    {
      if (pickUpDate.isAfter(reservation.getStartDate()) && pickUpDate.isBefore(
          reservation.getEndDate()))
      {
        continue;
      }
      else if (dropOffDate.isAfter(reservation.getStartDate())
          && pickUpDate.isBefore(reservation.getEndDate()))
      {
        continue;
      }
      else if (pickUpDate.equals(reservation.getStartDate())
          || dropOffDate.equals(reservation.getEndDate()))
      {
        continue;
      }
      else
      {
        check++;
      }
    }
    if (check == reservations.size())
    {
      return true;
    }
    return false;
  }



  public synchronized void stopClient()
  {
    client.stop();
  }



  @Override public void AddObserver(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void RemoveObserver(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
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

  public synchronized void askCars()
  {
    client.getCarList();
  }

  public synchronized void askCustomers()
  {
    client.getCustomerList();
  }

  public synchronized void askLocations()
  {
    client.getLocations();
  }

  public synchronized void askReservations()
  {
    client.getReservations();
  }

  public synchronized void customerLogin(Customer customer)
  {
    support.firePropertyChange("login",null,customer);
  }
}
