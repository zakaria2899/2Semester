package shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest
{
  Object object = new Object();
  Message msg = new Message("type", object);

  @Test void getType()
  {
    assertEquals("type", msg.getType());
  }

  @Test void getArgs()
  {
    assertEquals(object, msg.getArgs());
  }
}