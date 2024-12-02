package objects;

import java.io.Serializable;

public class Customer implements Serializable
{
  private String cprNumber;// primary key
  private MyDate birthDate;
  private String firstName;
  private String lastName;
  private String address;
  private String city;
  private int postalCode;
  private String phoneNumber;
  private String email;

  public Customer(String cprNumber, MyDate birthDate, String firstName, String lastName,
      String address, String city, int postalCode, String phoneNumber,
      String email)
  {
    this.cprNumber = cprNumber;
    this.birthDate = birthDate;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public String toString()
  {
    return cprNumber + "; " + firstName + " " + lastName + "; " + address + " " + city + " " + postalCode + ";" + phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public int getPostalCode()
  {
    return postalCode;
  }

  public String getCity()
  {
    return city;
  }

  public String getAddress()
  {
    return address;
  }

  public String getCprNumber()
  {
    return cprNumber;
  }

  public MyDate getBirthDate()
  {
    return birthDate;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public void setPostalCode(int postalCode)
  {
    this.postalCode = postalCode;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }
}