package Database;

import objects.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CustomerDAO
{

  void create(Customer customer) throws SQLException;
  List<Customer> getAllCustomers() throws SQLException;
  void delete(Customer customer) throws SQLException;
  Customer getCustomerById(String id) throws SQLException;
  void update(Customer customer) throws SQLException;
}
