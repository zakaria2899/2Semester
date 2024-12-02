package core;

import client.network.SocketClient;

public class ClientFactory
{
  private SocketClient socketClient;

  public ClientFactory()
  {
    socketClient = new SocketClient();
  }

  public SocketClient getSocketClient()
  {
    if (socketClient == null)
    {
      socketClient = new SocketClient();
    }
    return socketClient;
  }
}
