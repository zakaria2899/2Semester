package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest
{
  private Location location = new Location("test location");

  @Test public void testGetLocation()
  {
    assertEquals("test location", location.getLocation());
  }

  @Test public void testGetNumberOfCars()
  {
    assertEquals(0, location.getNumberOfCars());
  }

  @Test public void testAddAndRemoveCar()
  {
    location.addACar();
    assertEquals(1, location.getNumberOfCars());
    location.removeACar();
    assertEquals(0, location.getNumberOfCars());
  }

  @Test public void testToString()
  {
    assertEquals("test location", location.toString());
  }
}