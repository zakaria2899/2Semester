package client.network;

import objects.Car;
import objects.Customer;
import objects.Location;
import objects.Reservation;
import shared.Message;
import shared.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client
{
  private ObjectInputStream inFromServer;
  private ObjectOutputStream outToServer;
  private PropertyChangeSupport support;
  private Socket socket;

  public SocketClient()
  {
    support = new PropertyChangeSupport(this);
    try
    {
      System.out.println("Connecting to server...");
      socket = new Socket("localhost", 8888);
      System.out.println("Connected to server");
      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());

      new Thread(this::startClient).start();

    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void startClient()
  {
      try
      {
        System.out.println("sending message");
        outToServer.writeObject(new Message("new client", null));
        while (true)
        {
          Message message = (Message) inFromServer.readObject();
          System.out.println("received message:" + message.getType());
          if ("good bye".equals(message.getType()))
          {
            socket.close();
            break;
          }
          else
          {
            support.firePropertyChange(message.getType(), null, message.getArgs());
          }
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
  }

  public void addCar(Car car)
  {
    try
    {
      outToServer.writeObject(new Message("create car", car));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send add car: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void editCar(Car car)
  {
    try
    {
      outToServer.writeObject(new Message("edit car", car));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send edit car: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void removeCar(Car car)
  {
    try
    {
      outToServer.writeObject(new Message("remove car", car));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send remove car: " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void addCustomer(Customer customer)
  {
    try
    {
      outToServer.writeObject(new Message("create customer", customer));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send add customer: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void editCustomer(Customer customer)
  {
    try
    {
      outToServer.writeObject(new Message("edit customer", customer));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send edit customer: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void removeCustomer(Customer customer)
  {
    try
    {
      outToServer.writeObject(new Message("remove customer", customer));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send remove customer: " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void addLocation(Location location)
  {
    try
    {
      outToServer.writeObject(new Message("create location", location));
    }
    catch (IOException e)
    {
      System.out.println("couldn't send add location: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void removeLocation(Location location)
  {
    try
    {
      outToServer.writeObject(new Message("remove location", location));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send remove location: " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void addReservation(Reservation reservation)
  {
    try
    {
      outToServer.writeObject(new Message("create reservation", reservation));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send add reservation: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void removeReservation(Reservation reservation)
  {
    try
    {
      outToServer.writeObject(new Message("remove reservation", reservation));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send remove reservation: " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void getCarList()
  {
    try
    {
      outToServer.writeObject(new Message("get carlist", null));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send get carlist: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void getCustomerList()
  {
    try
    {
      outToServer.writeObject(new Message("get customerlist", null));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send get customerlist: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void getReservations()
  {
    try
    {
      outToServer.writeObject(new Message("get reservations", null));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send get reservations: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void getLocations()
  {
    try
    {
      outToServer.writeObject(new Message("get locations", null));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("couldn't send get locations: " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void stop()
  {
    try
    {
      outToServer.writeObject(new Message("good bye", null));
      outToServer.flush();
    }
    catch (IOException e)
    {
      System.out.println("Error writing to server: " + e.getMessage());
      e.printStackTrace();
    }
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

}
