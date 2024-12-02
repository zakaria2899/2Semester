package Database;

import objects.*;
import org.postgresql.Driver;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO
{
  private static ReservationDAOImpl instance;

  private ReservationDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new Driver());
  }

  public static synchronized ReservationDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ReservationDAOImpl();
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

  // Inserting parameters into table
  @Override
  public void create(Reservation reservation) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement insertStatement = connection.prepareStatement(
          "INSERT INTO reservation(car, customerID, start_date, end_date, pick_up, drop_off) VALUES (?, ?, ?, ?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);

      // Since MyDate isn't recognized by sql we'll have to convert MyDate objects to java.sql.Date
      Date sqlStartDate = Date.valueOf(
          reservation.getStartDate().toLocalDate());
      Date sqlEndDate = Date.valueOf(reservation.getEndDate().toLocalDate());

      // Inserting parameters into database
      insertStatement.setString(1, reservation.getCar().getPlate());
      insertStatement.setString(2, reservation.getCustomer().getCprNumber());
      insertStatement.setDate(3, sqlStartDate);
      insertStatement.setDate(4, sqlEndDate);
      insertStatement.setString(5, reservation.getPickupLocation().toString());
      insertStatement.setString(6, reservation.getReturnLocation().toString());
      insertStatement.executeUpdate();


    }
  }

  // Selecting a reservation by ID
  @Override
  public List<Reservation> getAllReservations() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement selectStatement = connection.prepareStatement(
          "SELECT * FROM reservation");

      ResultSet resultSet = selectStatement.executeQuery();
      List<Reservation> reservations = new ArrayList<>();

      ArrayList<Car> cars;
      cars = (ArrayList<Car>) CarDAOImpl.getInstance().getAllCars();

      ArrayList<Customer> customers;
      customers = (ArrayList<Customer>) CustomerDAOImpl.getInstance().getAllCustomers();

      ArrayList<Location> locations;
      locations = (ArrayList<Location>) LocationDAOImpl.getInstance().getAllLocations();

      while (resultSet.next())
      {
        int reservationId = resultSet.getInt("reservationID");

        String carPlate = resultSet.getString("car");
        String customerID = resultSet.getString("customerID");

        LocalDate sqlStartDate = resultSet.getDate("start_date").toLocalDate();
        LocalDate sqlEndDate = resultSet.getDate("end_date").toLocalDate();

        String pickupLocation = resultSet.getString("pick_up");
        String returnLocation = resultSet.getString("drop_off");



        Car car = null;
        for (Car c : cars)
        {
          if (c.getPlate().equals(carPlate))
          {
            car = c;
          }
        }


        Customer customer = null;
        for (Customer c : customers)
        {
          if (c.getCprNumber().equals(customerID))
          {
            customer = c;
          }
        }


        Location pickUp = null;
        Location dropOff = null;

        for (Location l : locations)
        {
          if (l.getLocation().equals(pickupLocation))
          {
            pickUp = l;
          }
          if (l.getLocation().equals(returnLocation))
          {
            dropOff = l;
          }
        }

        MyDate startDate = new MyDate(sqlStartDate.getDayOfMonth(),
            sqlStartDate.getMonthValue(), sqlStartDate.getYear());
        MyDate endDate = new MyDate(sqlEndDate.getDayOfMonth(),
            sqlEndDate.getMonthValue(), sqlEndDate.getYear());

        reservations.add(
            new Reservation(reservationId, car, customer, pickUp, dropOff,
                startDate, endDate));
        // Here we create the MyDate objects of the dates
      }
      return reservations;
    }
  }



  // Remove a reservation from database
  @Override public void delete(Reservation reservation) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM reservation WHERE reservationID = ?");

      deleteStatement.setInt(1, reservation.getReservationID());

      deleteStatement.executeUpdate();
    }
  }
}
