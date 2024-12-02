package Database;

import objects.Location;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO
{
  void create(Location location) throws SQLException;
  void delete(Location location) throws SQLException;
  List<Location> getAllLocations() throws SQLException;
  Location getLocationByName(String id) throws SQLException;
}
