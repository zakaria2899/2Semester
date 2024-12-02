package server;

import server.Network.SocketServer;

import java.sql.SQLException;

public class RunServer
{
  public static void main(String[] args) throws SQLException
  {
    SocketServer server = new SocketServer();
    server.startServer();
  }
}
