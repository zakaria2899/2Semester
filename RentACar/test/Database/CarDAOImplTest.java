package Database;

import objects.Car;
import objects.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarDAOImplTest
{
  String licensePlate = "ABC1275";
  String brand = "Toyota";
  String model = "Corolla";
  String description = "Compact car";
  double dailyRentalPrice = 50.0;
  int year = 2022;
  int seatingCapacity = 5;
  int greenlevel = 3;
  String fuelType = "Diesel";
  Location currentLocation = new Location("Test2");


  @Test void create() throws SQLException
  {
    CarDAOImpl.getInstance().create(new Car(licensePlate,brand,model,description,year,seatingCapacity,greenlevel,fuelType,dailyRentalPrice,currentLocation));
  }

  @Test void delete() throws SQLException
  {
    CarDAOImpl.getInstance().delete(new Car(licensePlate,brand,model,description,year,seatingCapacity,greenlevel,fuelType,dailyRentalPrice,currentLocation));
  }

  @Test void getAllCars() throws SQLException
  {
    ArrayList<Car> cars = new ArrayList<>();
    cars.addAll(CarDAOImpl.getInstance().getAllCars());

    for (int i = 0; i < cars.size(); i++)
    {
      System.out.println(cars.get(i).getPlate());

    }
  }

  @Test void update() throws SQLException
  {
    CarDAOImpl.getInstance().update(
        new Car(licensePlate, brand, model, description, year, seatingCapacity,
            greenlevel, fuelType, 100, new Location("Test2")));
  }

  @Test void getByPlate() throws SQLException
  {
    Car car = CarDAOImpl.getInstance().getCarByPlate("ABC1295");
    System.out.println(car.getPlate());
  }
}