package objects;

import java.io.Serializable;

public class Location implements Serializable
{
  private String location;
  private int numberOfCars;

  public Location(String location)
  {
    this.location = location;
    numberOfCars = 0;
  }

  @Override public String toString()
  {
    return getLocation();
  }

  public void addACar()
  {
    numberOfCars++;
  }

  public void removeACar()
  {
    numberOfCars--;
  }

  public String getLocation()
  {
    return location;
  }

  public int getNumberOfCars()
  {
    return numberOfCars;
  }
}
