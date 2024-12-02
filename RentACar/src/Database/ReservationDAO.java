package Database;

import objects.*;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO
{
  // Inserting parameters into table
  void create(Reservation reservation) throws SQLException;
  // Selecting a reservation by ID
  List<Reservation> getAllReservations() throws SQLException;
  // Remove a reservation from database
  void delete(Reservation reservation) throws SQLException;
}