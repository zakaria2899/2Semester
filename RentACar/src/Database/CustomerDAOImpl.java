package Database;

import objects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO
{
  private static CustomerDAOImpl instance;

  CustomerDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized CustomerDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new CustomerDAOImpl();
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
  public void create(Customer customer) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO customer(cpr, firstname, surname, address, city, zip_code, email, phone_no, DoB) VALUES (?,?,?,?,?,?,?,?,?)");


      // Inserting parameters into database
      insertStatement.setString(1, customer.getCprNumber()); // Set cpr as first parameter
      insertStatement.setString(2, customer.getFirstName());
      insertStatement.setString(3, customer.getLastName());
      insertStatement.setString(4, customer.getAddress());
      insertStatement.setString(5, customer.getCity());
      insertStatement.setInt(6, customer.getPostalCode());
      insertStatement.setString(7, customer.getEmail());
      insertStatement.setString(8, customer.getPhoneNumber());
      Date sqlDate = new Date(customer.getBirthDate().getYear()-1900, customer.getBirthDate().getMonth()-1, customer.getBirthDate().getDay()); //Fixing the unnecessary offset there is on Date objects.
      insertStatement.setDate(9, sqlDate);
      System.out.println(sqlDate.toString());

      insertStatement.executeUpdate();

    }
  }

  // Finding a customer from ID
  @Override
  public List<Customer> getAllCustomers() throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM rentacar.customer");

      ResultSet rs= readStatement.executeQuery();
      ArrayList<Customer> result = new ArrayList<>();
      while(rs.next())
      {
        String cpr = rs.getString("cpr");
        String firstname = rs.getString("firstname");
        String surname = rs.getString("surname");
        String address = rs.getString("address");
        String city = rs.getString("city");
        int postalcode = rs.getInt("zip_code");
        String email = rs.getString("email");
        String phonenum = rs.getString("phone_no");
        String dob = rs.getString("dob");
        String[] date = dob.split("-");
        MyDate date1 = new MyDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));

        result.add(new Customer(cpr,date1,firstname,surname,address,city,postalcode,phonenum,email));

      }
      return result;
    }
  }


  @Override public void delete(Customer customer) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM customer WHERE cpr = ?");

      deleteStatement.setString(1, customer.getCprNumber());

      deleteStatement.executeUpdate();
    }
  }

  @Override public Customer getCustomerById(String id) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM rentacar.customer where cpr = ?");

      readStatement.setString(1, id);
      ResultSet rs = readStatement.executeQuery();
      Customer customer = null;
      while(rs.next())
      {
        String cpr = rs.getString("cpr");
        String firstname = rs.getString("firstname");
        String surname = rs.getString("surname");
        String address = rs.getString("address");
        String city = rs.getString("city");
        int postalcode = rs.getInt("zip_code");
        String email = rs.getString("email");
        String phonenum = rs.getString("phone_no");
        String dob = rs.getString("dob");
        String[] date = dob.split("-");
        MyDate date1 = new MyDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));

        customer = new Customer(cpr,date1,firstname,surname,address,city,postalcode,phonenum,email);

      }
      return customer;


    }
  }

  public void update(Customer customer) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement updateStatement = connection.prepareStatement("UPDATE customer SET address = ?, city = ?, zip_code = ?, email = ?, phone_no = ? WHERE cpr = ?");

      updateStatement.setString(1, customer.getAddress());
      updateStatement.setString(2, customer.getCity());
      updateStatement.setInt(3,customer.getPostalCode());
      updateStatement.setString(4,customer.getEmail());
      updateStatement.setString(5,customer.getPhoneNumber());
      updateStatement.setString(6,customer.getCprNumber());

      updateStatement.executeUpdate();
    }
  }
}
