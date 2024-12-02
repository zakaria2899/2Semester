package Database;

import objects.Customer;
import objects.MyDate;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImplTest
{

  @Test void create() throws SQLException
  {
    CustomerDAOImpl.getInstance().create(
        new Customer("124222-1314", new MyDate(26,3,2003), "Martin", "Feldt",
            "Test addresse", "Horsens", 2233, "12121212", "Test@Test.dk"));
  }

  @Test void getAll() throws SQLException
  {
    ArrayList<Customer> customers = new ArrayList<>();
    customers.addAll(CustomerDAOImpl.getInstance().getAllCustomers());
    for (int i = 0; i < customers.size(); i++)
    {
      System.out.println(customers.get(i).toString());
    }
  }

  @Test void update() throws SQLException
  {
    CustomerDAOImpl.getInstance().update(new Customer("124222-1314", new MyDate(26,3,2003), "Martin", "Feldt",
        "Test", "Hor", 2299, "23232323", "ikkeTest@Test.dk"));
  }

  @Test void delete() throws SQLException
  {
    CustomerDAOImpl.getInstance().delete(
        new Customer("124222-1317", new MyDate(26, 3, 2003), "Martin", "Feldt",
            "Test", "Hor", 2299, "23232323", "ikkeTest@Test.dk"));
  }

  @Test void getById() throws SQLException
  {
    Customer customer = CustomerDAOImpl.getInstance().getCustomerById("124222-1314");
    System.out.println(customer.toString());
  }

}