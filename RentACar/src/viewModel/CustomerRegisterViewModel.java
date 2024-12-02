package viewModel;

import model.CarRentModel;
import objects.Customer;
import objects.MyDate;
import javafx.beans.property.*;
import model.CarRentModelImpl;

import java.time.LocalDate;

public class CustomerRegisterViewModel
{
  private CarRentModel carRentModel;

  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty email;
  private StringProperty phone;
  private StringProperty address;
  private StringProperty city;
  private StringProperty postalCode;
  private ObjectProperty<LocalDate> birthDate;
  private StringProperty cpr6;
  private StringProperty cpr4;


  public CustomerRegisterViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    email = new SimpleStringProperty();
    phone = new SimpleStringProperty();
    address = new SimpleStringProperty();
    city = new SimpleStringProperty();
    postalCode = new SimpleStringProperty();
    birthDate = new SimpleObjectProperty<>();
    cpr6 = new SimpleStringProperty();
    cpr4 = new SimpleStringProperty();
  }

  public StringProperty firstNameProperty()
  {
    return firstName;
  }

  public StringProperty lastNameProperty()
  {
    return lastName;
  }

  public StringProperty emailProperty()
  {
    return email;
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }
  public StringProperty addressProperty()
  {
    return address;
  }

  public StringProperty cityProperty()
  {
    return city;
  }

  public StringProperty postalCodeProperty()
  {
    return postalCode;
  }

  public ObjectProperty<LocalDate> birthDateProperty()
  {
    return birthDate;
  }

  public StringProperty cpr6Property()
  {
    return cpr6;
  }

  public StringProperty cpr4Property()
  {
    return cpr4;
  }


  /**
   * This method validates the input of the first name.
   * the validation makes sure it is not empty and all characters are letters.
   */
  public boolean validateFirstName()
  {
    if (firstName.get() == null || firstName.get().isEmpty())
    {
      return false;
    }

    String[] names = firstName.get().split(" ");
    for (int i = 0; i < names.length; i++)
    {
      for (int j = 0; j < names[i].length(); j++)
      {
        if (!Character.isLetter(names[i].charAt(j)))
        {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * This method validates the input of the last name.
   * the validation makes sure it is not empty and all characters are letters.
   */
  public boolean validateLastName()
  {
    if (lastName.get() == null || lastName.get().isEmpty())
    {
      return false;
    }

    String[] names = lastName.get().split(" ");
    for (int i = 0; i < names.length; i++)
    {
      for (int j = 0; j < names[i].length(); j++)
      {
        if (!Character.isLetter(names[i].charAt(j)))
        {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * This method validates the input of the mail address.
   * the validation makes sure it has the format of a mail address.
   */
  public boolean validateMail()
  {
    if (email.get() == null || email.get().isEmpty() || email.get().equals(" "))
    {
      return false;
    }
    else if (!email.get().contains("@") || !email.get().contains("."))
    {
      return false;
    }

    String[] emailSplit = email.get().split("@");
    if (emailSplit.length != 2 || !emailSplit[1].contains("."))
    {
      return false;
    }
    else if (emailSplit[0].isEmpty() || emailSplit[1].isEmpty())
    {
      return false;
    }
    return true;
  }

  /**
   * This method validates the input of the phone number.
   * the validation makes sure it is 8 digits without a country code.
   */
  public boolean validateNumber()
  {
    if (phone.get() == null || phone.get().isEmpty() || phone.get().contains("+"))
    {
      return false;
    }

      String[] phoneSplit = phone.get().split(" ");
      String phonenumber = "";
      for (int i = 0; i < phoneSplit.length; i++)
      {
        phonenumber = phonenumber + phoneSplit[i];
      }
      if (phonenumber.length() != 8)
      {
        return false;
      }

        for (int i = 0; i < phonenumber.length(); i++)
        {
          if (!Character.isDigit(phonenumber.charAt(i)))
          {
            return false;
          }
        }
    return true;

  }

  /**
   * This method validates the input of the address.
   * the validation makes sure there is at most 5 words.
   */
  public boolean validateAddress()
  {
    if (address.get() == null || address.get().isEmpty() || address.get().equals(" "))
    {
      return false;
    }
    return true;
  }

  /**
   * This method validates the input of the city.
   * the validation makes sure all characters are letters in one word.
   */
  public boolean validateCity()
  {
    if (city.get() == null || city.get().isEmpty() || city.get().contains(" "))
    {
      return false;
    }
    for (int i = 0; i < city.get().length(); i++)
    {
      if (!Character.isLetter(city.get().charAt(i)))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * This method validates the input of the postal code.
   * the validation makes sure it is not empty and all characters are letters.
   */
  public boolean validatePostalCode()
  {
    if (postalCode.get() == null || postalCode.get().isEmpty())
    {
      return false;
    }
    else
    {
      try
      {
        int i = Integer.parseInt(postalCode.get());

        if (i < 1000 || i > 9990)
        {
          return false;
        }
      }
      catch (NumberFormatException e)
      {
        return false;
      }
    }
    return true;
  }

  public boolean validateBirthDate()
  {
    if (birthDate.get() == null)
    {
      return false;
    }
    MyDate myDate = new MyDate(birthDate.get().getDayOfMonth(), birthDate.get().getMonthValue(), birthDate.get().getYear());
    if (myDate.getAge() < 18)
    {
      return false;
    }
    return true;
  }

  public boolean validateCpr4()
  {
    if (cpr4.get() == null || cpr4.get().isEmpty() || cpr4.get().length() != 4)
    {
      return false;
    }
    else
    {
      for (int i = 0; i < cpr4.get().length(); i++)
      {
        if (!Character.isDigit(cpr4.get().charAt(i)))
        {
          return false;
        }
      }
    }
    return true;
  }

  public boolean validateCpr6()
  {
    if (cpr6.get() == null || cpr6.get().isEmpty() || cpr6.get().length() != 6)
    {
      return false;
    }
    else
    {
      for (int i = 0; i < cpr6.get().length(); i++)
      {
        if (!Character.isDigit(cpr6.get().charAt(i)))
        {
          return false;
        }
      }
    }
    int day1 = Integer.parseInt(cpr6.get().substring(0, 2));
    int month1 = Integer.parseInt(cpr6.get().substring(2,4));
    int year1 = Integer.parseInt(cpr6.get().substring(4,6));
    int day2 = birthDate.get().getDayOfMonth();
    int month2 = birthDate.get().getMonthValue();
    int year2 = birthDate.get().getYear() % 100;


    if (day1 != day2 || month1 != month2 || year1 != year2)
    {
      return false;
    }
    return true;
  }




  public void registerCustomer()
  {
    carRentModel.addCustomer(new Customer(cpr6.get()+"-"+cpr4.get(), new MyDate(birthDate.get().getDayOfMonth(), birthDate.get().getMonthValue(), birthDate.get().getYear()), firstName.get(), lastName.get(), address.get(), city.get(), Integer.parseInt(postalCode.get()), phone.get(), email.get().toLowerCase()));
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
