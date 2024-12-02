package viewModel;

import core.ModelFactory;
import objects.MyDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRegisterViewModelTest
{
  //make sure server is running and ready for connections

  CustomerRegisterViewModel viewModel = new CustomerRegisterViewModel(
      ModelFactory.getInstance().getCarRentModel());

  @Test void validateFirstName()
  {
    viewModel.firstNameProperty().setValue("John");
    assertTrue(viewModel.validateFirstName());
    viewModel.firstNameProperty().setValue(null);
    assertFalse(viewModel.validateFirstName());
    viewModel.firstNameProperty().setValue("John Doe");
    assertTrue(viewModel.validateFirstName());
    viewModel.firstNameProperty().setValue("Hello 5");
    assertFalse(viewModel.validateFirstName());

  }

  @Test void validateLastName()
  {
    viewModel.lastNameProperty().setValue("John");
    assertTrue(viewModel.validateLastName());
    viewModel.lastNameProperty().setValue(null);
    assertFalse(viewModel.validateLastName());
    viewModel.lastNameProperty().setValue("John Doe");
    assertTrue(viewModel.validateLastName());
    viewModel.lastNameProperty().setValue("Hello 5");
    assertFalse(viewModel.validateLastName());
  }

  @Test void validateMail()
  {
    viewModel.emailProperty().setValue("John.dk");
    assertFalse(viewModel.validateMail());
    viewModel.emailProperty().setValue(null);
    assertFalse(viewModel.validateMail());
    viewModel.emailProperty().setValue("@.");
    assertFalse(viewModel.validateMail());
    viewModel.emailProperty().setValue("John@doe.dk");
    assertTrue(viewModel.validateMail());
    viewModel.emailProperty().setValue(" ");
    assertFalse(viewModel.validateMail());
  }

  @Test void validateNumber()
  {
    viewModel.phoneProperty().set("John");
    assertFalse(viewModel.validateNumber());
    viewModel.phoneProperty().set(null);
    assertFalse(viewModel.validateNumber());
    viewModel.phoneProperty().set("+4512121212");
    assertFalse(viewModel.validateNumber());
    viewModel.phoneProperty().set("12121212");
    assertTrue(viewModel.validateNumber());
    viewModel.phoneProperty().set("12 12 12 12");
    assertTrue(viewModel.validateNumber());
  }

  @Test void validateAddress()
  {
    viewModel.addressProperty().setValue("vej 6");
    assertTrue(viewModel.validateAddress());
    viewModel.addressProperty().setValue(null);
    assertFalse(viewModel.validateAddress());
    viewModel.addressProperty().setValue("a@-.,/");
    assertTrue(viewModel.validateAddress());

  }

  @Test void validateCity()
  {
    viewModel.cityProperty().setValue(null);
    assertFalse(viewModel.validateCity());
    viewModel.cityProperty().setValue("test");
    assertTrue(viewModel.validateCity());
    viewModel.cityProperty().setValue("test by");
    assertFalse(viewModel.validateCity());
  }

  @Test void validatePostalCode()
  {
    viewModel.postalCodeProperty().setValue(null);
    assertFalse(viewModel.validatePostalCode());
    viewModel.postalCodeProperty().setValue("");
    assertFalse(viewModel.validatePostalCode());
    viewModel.postalCodeProperty().setValue("a-*");
    assertFalse(viewModel.validatePostalCode());
    viewModel.postalCodeProperty().setValue("1000");
    assertTrue(viewModel.validatePostalCode());
    viewModel.postalCodeProperty().setValue("9990");
    assertTrue(viewModel.validatePostalCode());
    viewModel.postalCodeProperty().setValue("10000");
    assertFalse(viewModel.validatePostalCode());
    viewModel.postalCodeProperty().setValue("0999");
    assertFalse(viewModel.validatePostalCode());
  }

  @Test void validateBirthDate()
  {
    LocalDate date = LocalDate.now();
    viewModel.birthDateProperty().setValue(date);
    assertFalse(viewModel.validateBirthDate());
    date = LocalDate.now().minusYears(10);
    viewModel.birthDateProperty().setValue(date);
    assertFalse(viewModel.validateBirthDate());
    date = LocalDate.now().minusYears(18);
    viewModel.birthDateProperty().setValue(date);
    assertTrue(viewModel.validateBirthDate());
    date = LocalDate.now().minusYears(30);
    viewModel.birthDateProperty().setValue(date);
    assertTrue(viewModel.validateBirthDate());
  }

  @Test void validateCpr6()
  {
    viewModel.cpr6Property().setValue(null);
    assertFalse(viewModel.validateCpr6());
    viewModel.cpr6Property().setValue("121203");
    viewModel.birthDateProperty().setValue(LocalDate.of(2003, 12,10));
    assertFalse(viewModel.validateCpr6());
    viewModel.birthDateProperty().setValue(LocalDate.of(2003, 12,12));
    assertTrue(viewModel.validateCpr6());
  }

  @Test void validateCpr4()
  {
    viewModel.cpr4Property().setValue(null);
    assertFalse(viewModel.validateCpr4());
    viewModel.cpr4Property().setValue("12 2");
    assertFalse(viewModel.validateCpr4());
    viewModel.cpr4Property().setValue("aa12");
    assertFalse(viewModel.validateCpr4());
    viewModel.cpr4Property().setValue("1234");
    assertTrue(viewModel.validateCpr4());
  }
}