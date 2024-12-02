package server.Network;

import shared.Message;

import java.util.ArrayList;

public class ConnectionPool
{
  private ArrayList<SocketHandler> connections;

  public ConnectionPool()
  {
    connections = new ArrayList<>();
  }

  public void addConnection(SocketHandler connection)
  {
    connections.add(connection);
  }

  public void removeConnection(SocketHandler connection)
  {
    connections.remove(connection);
  }

  public void broadCast(Message message)
  {
    System.out.println("sending broadcast");
    for (SocketHandler connection : connections)
    {
      connection.sendMessage(message);
    }
  }
}
