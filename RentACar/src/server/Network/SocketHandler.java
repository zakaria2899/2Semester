package server.Network;

import server.Model.CarRentModel;
import server.Model.CarRentModelImpl;
import objects.Car;
import objects.Customer;
import objects.Location;
import objects.Reservation;
import shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class SocketHandler implements Runnable
{
  private ObjectInputStream inFromClient;
  private ObjectOutputStream outToClient;
  private Socket socket;
  private ConnectionPool connectionPool;
  private CarRentModel carRentModel;


  public SocketHandler(Socket socket, ConnectionPool connectionPool, CarRentModel carRentModel)
  {
    this.connectionPool = connectionPool;
    this.carRentModel = carRentModel;

    try
    {
      this.socket = socket;
      outToClient = new ObjectOutputStream(this.socket.getOutputStream());
      inFromClient = new ObjectInputStream(this.socket.getInputStream());
    }
    catch (IOException e)
    {
      System.out.println("IO Exception: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        Message message = (Message) inFromClient.readObject();
        System.out.println("got new message");
        if ("new client".equals(message.getType()))
        {
          System.out.println("New client received; adding to connection pool");
          connectionPool.addConnection(this);

          if (carRentModel.getCustomers().isEmpty())
          {
            carRentModel.askCustomers();
          }
          sendMessage(new Message("customerlist", carRentModel.getCustomers()));

          if (carRentModel.getLocations().isEmpty())
          {
            carRentModel.askLocations();
          }
          sendMessage(new Message("locations", carRentModel.getLocations()));

          if (carRentModel.getCars().isEmpty())
          {
            carRentModel.askCars();
          }
          sendMessage(new Message("carlist", carRentModel.getCars()));

          if (carRentModel.getReservations().isEmpty())
          {
            carRentModel.askReservations();
          }
          sendMessage(new Message("reservations", carRentModel.getReservations()));

        }
        else if ("create car".equals(message.getType()))
        {
          System.out.println("got new car");
          carRentModel.addCar((Car)message.getArgs());
        }
        else if ("edit car".equals(message.getType()))
        {
          System.out.println("got edit car");
          carRentModel.editCar((Car)message.getArgs());
        }
        else if ("remove car".equals(message.getType()))
        {
          System.out.println("got remove car");
          carRentModel.removeCar((Car)message.getArgs());
        }
        else if ("create customer".equals(message.getType()))
        {
          System.out.println("got new customer");
          carRentModel.addCustomer((Customer)message.getArgs());
        }
        else if ("edit customer".equals(message.getType()))
        {
          System.out.println("got edit customer");
          carRentModel.editCustomer((Customer)message.getArgs());
        }
        else if ("remove customer".equals(message.getType()))
        {
          System.out.println("got remove customer");
          carRentModel.removeCustomer((Customer)message.getArgs());
        }
        else if ("create location".equals(message.getType()))
        {
          System.out.println("got new location");
          carRentModel.addLocation((Location)message.getArgs());
        }
        else if ("remove location".equals(message.getType()))
        {
          System.out.println("got remove location");
          carRentModel.removeLocation((Location)message.getArgs());
        }
        else if ("create reservation".equals(message.getType()))
        {
          System.out.println("got new reservation");
          carRentModel.addReservation((Reservation)message.getArgs());
        }
        else if ("remove reservation".equals(message.getType()))
        {
          System.out.println("got remove reservation");
          carRentModel.removeReservation((Reservation)message.getArgs());
        }
        else if ("get carlist".equals(message.getType()))
        {
          System.out.println("got get carlist");
          carRentModel.askCars();

        }
        else if ("get customerlist".equals(message.getType()))
        {
          System.out.println("got get customerlist");
          carRentModel.askCustomers();
        }
        else if ("get reservations".equals(message.getType()))
        {
          System.out.println("got get reservations");
          carRentModel.askReservations();
        }
        else if ("get locations".equals(message.getType()))
        {
          System.out.println("got get locations");
          carRentModel.askLocations();
        }


        else if ("good bye".equals(message.getType()))
        {
          System.out.println("got goodbye");
          stopconnection();
          break;
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        System.out.println("IOException in SocketHandler: " + e.getMessage());
        stopconnection();
        connectionPool.removeConnection(this);
        throw new RuntimeException(e);
      }
      catch (SQLException e)
      {
        System.out.println("SQLException in SocketHandler: " + e.getMessage());
        stopconnection();
        connectionPool.removeConnection(this);
        throw new RuntimeException(e);
      }
    }
  }

  public void sendMessage(Message message)
  {
    try
    {
      outToClient.writeObject(message);
      outToClient.reset();
    }
    catch (IOException e)
    {
      System.out.println("IOException in SocketHandler: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public void stopconnection()
  {
    connectionPool.removeConnection(this);
    try
    {
      outToClient.writeObject(new Message("good bye", null));
      socket.close();
    }
    catch (IOException e)
    {
      System.out.println("IOException in SocketHandler: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
