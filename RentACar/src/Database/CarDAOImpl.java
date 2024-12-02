package Database;

import objects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO
{
  private static CarDAOImpl instance;

  public CarDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized CarDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new CarDAOImpl();
    }
    return instance;
  }

  //Establishing connection to database
  private static Connection getConnection() throws SQLException
  {
    Connection conn = DriverManager.getConnection(
        "jdbc:postgresql://sep2.postgres.database.azure.com:5432/postgres", "sep2", "Semesterprojekt=%");
    try (Statement stmt = conn.createStatement()) {
      stmt.execute("SET SCHEMA 'rentacar'");
    }
    return conn;
  }

  @Override
  public void create(Car car)
      throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement insertStatement = connection.prepareStatement(
          "INSERT INTO car(licensePlate, brand, model, description, price, year, seating, greenlevel, fuelType, currentLocation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

      insertStatement.setString(1, car.getPlate());
      insertStatement.setString(2, car.getBrand());
      insertStatement.setString(3, car.getModel());
      insertStatement.setString(4, car.getDescripton());
      insertStatement.setDouble(5, car.getPricePerDay());
      insertStatement.setInt(6, car.getYear());
      insertStatement.setInt(7, car.getSeatingCapacity());
      insertStatement.setInt(8, car.getGreenLevel());
      insertStatement.setString(9, car.getFuelType());
      insertStatement.setString(10, car.getCurrentLocation().getLocation());

      // Execute the insert statement
      insertStatement.executeUpdate();

    }
  }

  @Override
  public Car getCarByPlate(String licensePlate) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM car WHERE licensePlate = ?");
      selectStatement.setString(1, licensePlate);

      ResultSet resultSet = selectStatement.executeQuery();
      if (resultSet.next()) {
        String retrievedLicensePlate = resultSet.getString("licensePlate");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        String description = resultSet.getString("description");
        double dailyRentalPrice = resultSet.getDouble("price");
        int year = resultSet.getInt("year");
        int seatingCapacity = resultSet.getInt("seating");
        int greenlevel = resultSet.getInt("greenlevel");
        String fuelType = resultSet.getString("fuelType");
        Location currentLocation = new Location(resultSet.getString("currentLocation"));
        // Assuming getLocationObject retrieves Location object based on location string

        return new Car(retrievedLicensePlate, brand, model, description, year, seatingCapacity,
            greenlevel, fuelType, dailyRentalPrice, currentLocation);
      } else {
        throw new SQLException("No car found with the license: " + licensePlate);
      }
    }
  }

  // getting all cars from database.
  @Override
  public List<Car> getAllCars() throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM rentacar.car");

      ResultSet rs= readStatement.executeQuery();
      ArrayList<Car> result = new ArrayList<>();
      while(rs.next())
      {
        String licensePlate = rs.getString("licensePlate");
        String brand = rs.getString("brand");
        String model = rs.getString("model");
        String description = rs.getString("description");
        String fuelType = rs.getString("fuelType");
        double price = rs.getDouble("price");
        int year = rs.getInt("year");
        int seating = rs.getInt("seating");
        int greenLevel = rs.getInt("greenlevel");
        String currentLocation = rs.getString("currentLocation");

        result.add(new Car(licensePlate,brand,model,description,year,seating,greenLevel, fuelType, price, new Location(currentLocation)));

      }
      return result;
    }
  }

  @Override public void delete(Car car) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM car WHERE licensePlate = ?");

      deleteStatement.setString(1, car.getPlate());

      int affectedRows = deleteStatement.executeUpdate();

    }
  }

  @Override public void update(Car car) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement updateStatement = connection.prepareStatement("UPDATE car SET price = ?, currentLocation = ? WHERE licensePlate = ?");


      updateStatement.setDouble(1, car.getPricePerDay());
      updateStatement.setString(2, car.getCurrentLocation().toString());
      updateStatement.setString(3, car.getPlate());

      updateStatement.executeUpdate();
    }
  }
}
