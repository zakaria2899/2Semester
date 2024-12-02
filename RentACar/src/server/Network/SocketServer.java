package server.Network;

import server.Model.CarRentModel;
import server.Model.CarRentModelImpl;
import shared.Message;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class SocketServer
{
  private ConnectionPool connectionPool;
  CarRentModel carRentModel;

  public SocketServer() throws SQLException
  {
    System.out.println("Connecting to Database");
    carRentModel = new CarRentModelImpl();
    System.out.println();
    connectionPool = new ConnectionPool();
    carRentModel.AddObserver(this::broadCast);
    startServer();
  }

  private void broadCast(PropertyChangeEvent propertyChangeEvent)
  {
    connectionPool.broadCast(new Message(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getNewValue()));
  }

  public void startServer()
  {
    System.out.println("Starting Server...");
    try
    {
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("Server started.");
      while(true)
      {
        System.out.println("Waiting for connection...");
        Socket socket = serverSocket.accept();
        System.out.println("Connection accepted");
        SocketHandler socketHandler = new SocketHandler(socket, connectionPool, carRentModel);
        System.out.println("starting serverhandler");
        new Thread(socketHandler).start();
      }



    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }
}
