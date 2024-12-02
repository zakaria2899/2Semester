package objects;

import java.io.Serializable;

public class Car implements Serializable
{
  private String plate; //primary key
  private String brand, model, descripton;
  private int year, seatingCapacity, greenLevel;
  private String fuelType;
  private double pricePerDay;
  private Location currentLocation;

  public Car(String plate, String brand, String model, String descripton, int year,
      int seatingCapacity, int greenLevel, String fuelType, double pricePerDay,
      Location currentLocation)
  {
    this.plate = plate;
    this.brand = brand;
    this.model = model;
    this.descripton = descripton;
    this.year = year;
    this.seatingCapacity = seatingCapacity;
    this.greenLevel = greenLevel;
    this.fuelType = fuelType;
    this.pricePerDay = pricePerDay;
    this.currentLocation = currentLocation;
  }

  public void setDescripton(String descripton)
  {
    this.descripton = descripton;
  }

  public void setPricePerDay(double pricePerDay)
  {
    this.pricePerDay = pricePerDay;
  }

  public void setCurrentLocation(Location currentLocation)
  {
    this.currentLocation = currentLocation;
  }

  public String toString()
  {
    return brand + " " + model + ", " + year + "; " + descripton + ".";
  }

  public String getPlate()
  {
    return plate;
  }

  public String getBrand()
  {
    return brand;
  }

  public String getModel()
  {
    return model;
  }

  public String getDescripton()
  {
    return descripton;
  }

  public int getYear()
  {
    return year;
  }

  public int getSeatingCapacity()
  {
    return seatingCapacity;
  }

  public int getGreenLevel()
  {
    return greenLevel;
  }

  public String getFuelType()
  {
    return fuelType;
  }

  public double getPricePerDay()
  {
    return pricePerDay;
  }

  public Location getCurrentLocation()
  {
    return currentLocation;
  }

  @Override public boolean equals(Object o)
  {
    if (!(o instanceof Car))
      return false;
    else
    {
      Car car = (Car) o;
      return year == car.year && seatingCapacity == car.seatingCapacity
          && greenLevel == car.greenLevel
          && pricePerDay == car.pricePerDay && plate.equals(car.plate)
          && brand.equals(car.brand) && model.equals(car.model)
          && descripton.equals(car.descripton) && fuelType.equals(
          car.fuelType) && currentLocation.toString().equals(
          car.currentLocation.toString());
    }

  }
}
