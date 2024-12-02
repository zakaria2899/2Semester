package Database;

import objects.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CarDAO
{
  public void create(Car car) throws SQLException;

  public Car getCarByPlate(String licensePlate) throws SQLException;

  public List<Car> getAllCars() throws SQLException;

  public void delete(Car car) throws SQLException;

  public void update(Car car) throws SQLException;
}
