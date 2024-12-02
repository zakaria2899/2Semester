package Database;

import objects.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAOImpl implements LocationDAO
{
  private static LocationDAO instance;

  LocationDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized LocationDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new LocationDAOImpl();
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

  @Override public void create(Location location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement insertStatement = connection.prepareStatement(
          "INSERT INTO location(location, numberOfCars) VALUES (?, ?)");

      // Inserting parameters into database
      insertStatement.setString(1, location.toString());
      insertStatement.setInt(2, location.getNumberOfCars());

      insertStatement.executeUpdate();

    }
  }

  @Override public void delete(Location location) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement("Delete from location where location = ?");

      deleteStatement.setString(1, location.getLocation());
      deleteStatement.executeUpdate();
    }
  }

  @Override public List<Location> getAllLocations() throws SQLException
  {

    try(Connection connection = getConnection())
    {
      PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM location");

      ResultSet rs = selectStatement.executeQuery();
      List<Location> locations = new ArrayList<>();
      while (rs.next())
      {
        String location = rs.getString("location");
        int numberOfCars = rs.getInt("numberofcars");
        Location location1 = new Location(location);
        for (int i = 0; i < numberOfCars; i++)
        {
          location1.addACar();
        }
        locations.add(location1);
      }
      return locations;
    }
  }

  @Override public Location getLocationByName(String id) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM location where location = ?");
      selectStatement.setString(1, id);

      ResultSet rs = selectStatement.executeQuery();
      Location location1 = null;
      while (rs.next())
      {
        String location = rs.getString("location");
        int numberOfCars = rs.getInt("numberofcars");
        location1 = new Location(location);
        for (int i = 0; i < numberOfCars; i++)
        {
          location1.addACar();
        }
      }
      return location1;
    }
  }
}
