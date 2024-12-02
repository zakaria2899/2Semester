package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest
{
  Customer customer = new Customer("011221-2921", new MyDate(), "Test name", "Test lastname", "test address", "test city", 1234, "12121212", "test@test.com");

  @Test void testToString()
  {
    assertEquals( "011221-2921; Test name Test lastname; test address test city 1234;12121212", customer.toString() );
  }

  @Test void getEmail()
  {
    assertEquals( "test@test.com", customer.getEmail() );
  }

  @Test void getPhoneNumber()
  {
    assertEquals( "12121212", customer.getPhoneNumber() );
  }

  @Test void getPostalCode()
  {
    assertEquals( 1234, customer.getPostalCode() );
  }

  @Test void getCity()
  {
    assertEquals( "test city", customer.getCity() );
  }

  @Test void getAddress()
  {
    assertEquals( "test address", customer.getAddress() );
  }

  @Test void getCprNumber()
  {
    assertEquals( "011221-2921", customer.getCprNumber() );
  }

  @Test void getBirthDate()
  {
    assertEquals( "24/5/2024", customer.getBirthDate().toString()); // test was last made in 24th may 2024
  }

  @Test void getFirstName()
  {
    assertEquals( "Test name", customer.getFirstName() );
  }

  @Test void getLastName()
  {
    assertEquals( "Test lastname", customer.getLastName() );
  }

  @Test void setAddress()
  {
    customer.setAddress("another address");
    assertEquals( "another address", customer.getAddress() );
  }

  @Test void setCity()
  {
    customer.setCity("another city");
  }

  @Test void setPostalCode()
  {
    customer.setPostalCode(0000);
    assertEquals( 0000, customer.getPostalCode() );
  }

  @Test void setPhoneNumber()
  {
    customer.setPhoneNumber("11111111");
    assertEquals( "11111111", customer.getPhoneNumber() );
  }

  @Test void setEmail()
  {
    customer.setEmail("Anothertest@test.com");
    assertEquals( "Anothertest@test.com", customer.getEmail() );
  }
}