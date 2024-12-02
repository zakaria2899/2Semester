package Database;

import objects.Location;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationDAOImplTest
{

  @Test void create() throws SQLException
  {
    LocationDAOImpl.getInstance().create(new Location("Test2"));
  }

  @Test void delete() throws SQLException
  {
    LocationDAOImpl.getInstance().delete(new Location("Test2"));
  }

  @Test void getAllLocations() throws SQLException
  {
    List<Location> locations = new ArrayList<>();
    locations.addAll(LocationDAOImpl.getInstance().getAllLocations());
    for (Location location : locations)
    {
      System.out.println(location.toString());
    }
  }

  @Test void getByName() throws SQLException
  {
   Location location =  LocationDAOImpl.getInstance().getLocationByName("Test1");
    System.out.println(location.toString());
  }
}