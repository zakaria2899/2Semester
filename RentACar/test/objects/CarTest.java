package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest
{
  Car car = new Car("AA12555", "mærke", "model", "a little description", 2018, 5, 3, "diesel", 2500, new Location("Vejle"));
  @Test void setDescripton()
  {
    car.setDescripton("some other description");
    assertEquals("some other description", car.getDescripton());
  }

  @Test void setPricePerDay()
  {
    car.setPricePerDay(5);
    assertEquals(5, car.getPricePerDay());
  }

  @Test void setCurrentLocation()
  {
    car.setCurrentLocation(new Location("Århus"));
    assertEquals("Århus", car.getCurrentLocation().toString());
  }

  @Test void testToString()
  {
    assertEquals("Nummerplade: AA12555; mærke model, 2018; a little description.", car.toString());
  }

  @Test void getPlate()
  {
    assertEquals("AA12555", car.getPlate());
  }

  @Test void getBrand()
  {
    assertEquals("mærke", car.getBrand());
  }

  @Test void getModel()
  {
    assertEquals("model", car.getModel());
  }

  @Test void getDescripton()
  {
    assertEquals("a little description", car.getDescripton());
  }

  @Test void getYear()
  {
    assertEquals(2018, car.getYear());
  }

  @Test void getSeatingCapacity()
  {
    assertEquals(5, car.getSeatingCapacity());
  }

  @Test void getGreenLevel()
  {
    assertEquals(3, car.getGreenLevel());
  }

  @Test void getFuelType()
  {
    assertEquals("diesel", car.getFuelType());
  }

  @Test void getPricePerDay()
  {
    assertEquals(2500, car.getPricePerDay());
  }

  @Test void getCurrentLocation()
  {
    assertEquals("Vejle", car.getCurrentLocation().toString());
  }

  @Test void testEquals()
  {
    Car car1 = new Car(car.getPlate(), car.getBrand(), car.getModel(), car.getDescripton(), car.getYear(), car.getSeatingCapacity(), car.getGreenLevel(), car.getFuelType(), car.getPricePerDay(), car.getCurrentLocation());
    assertTrue(car.equals(car1));
    car1.setDescripton("some other");
    assertFalse(car.equals(car1));
  }
}