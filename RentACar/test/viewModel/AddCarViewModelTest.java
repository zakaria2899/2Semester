package viewModel;

import client.network.SocketClient;
import core.ModelFactory;
import model.CarRentModelImpl;
import objects.Location;
import objects.MyDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Network.SocketServer;
import server.RunServer;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddCarViewModelTest
{

  //make sure server is running and ready for connections


    AddCarViewModel viewModel = new AddCarViewModel(ModelFactory.getInstance().getCarRentModel());


  @Test void validatePlate()
  {

    viewModel.numberPlateProperty().set("AB12345");
    assertTrue(viewModel.validatePlate());

    viewModel.numberPlateProperty().set(null);
    assertFalse(viewModel.validatePlate());

    viewModel.numberPlateProperty().set("");
    assertFalse(viewModel.validatePlate());

    viewModel.numberPlateProperty().set("ABC2345");
    assertFalse(viewModel.validatePlate());
  }

  @Test void validateBrand()
  {
    viewModel.brandProperty().set("a a a");
    assertTrue(viewModel.validateBrand());

    viewModel.brandProperty().set(null);
    assertFalse(viewModel.validateBrand());

    viewModel.brandProperty().set("aaa");
    assertTrue(viewModel.validateBrand());

    viewModel.brandProperty().set("aaa4");
    assertFalse(viewModel.validateBrand());

    viewModel.brandProperty().set("aaa-");
    assertFalse(viewModel.validateBrand());

  }

  @Test void validateModel()
  {
    viewModel.modelProperty().set("AB12345");
    assertTrue(viewModel.validateModel());
    viewModel.modelProperty().set(null);
    assertFalse(viewModel.validateModel());
    viewModel.modelProperty().set("A@asf 223");
    assertTrue(viewModel.validateModel());
  }

  @Test void validatePrice()
  {
    viewModel.priceProperty().set(0);
    assertFalse(viewModel.validatePrice());
    viewModel.priceProperty().set(-12);
    assertFalse(viewModel.validatePrice());
    viewModel.priceProperty().set(1);
    assertTrue(viewModel.validatePrice());
  }

  @Test void validateYear()
  {
    viewModel.yearProperty().set(0);
    assertFalse(viewModel.validateYear());
    viewModel.yearProperty().set(-12);
    assertFalse(viewModel.validateYear());
    viewModel.yearProperty().set(new MyDate().getYear() + 4);
    assertFalse(viewModel.validateYear());
  }

  @Test void validateSeats()
  {
    viewModel.capacityProperty().set(0);
    assertFalse(viewModel.validateSeats());
    viewModel.capacityProperty().set(-12);
    assertFalse(viewModel.validateSeats());
    viewModel.capacityProperty().set(2);
    assertTrue(viewModel.validateSeats());
    viewModel.capacityProperty().set(11);
    assertFalse(viewModel.validateSeats());
    viewModel.capacityProperty().set(5);
    assertTrue(viewModel.validateSeats());
  }

  @Test void validateGreenlevel()
  {
    viewModel.greenLevelProperty().set(0);
    assertFalse(viewModel.validateGreenlevel());
    viewModel.greenLevelProperty().set(-12);
    assertFalse(viewModel.validateGreenlevel());
    viewModel.greenLevelProperty().set(2);
    assertTrue(viewModel.validateGreenlevel());
    viewModel.greenLevelProperty().set(1);
    assertTrue(viewModel.validateGreenlevel());
    viewModel.greenLevelProperty().set(5);
    assertTrue(viewModel.validateGreenlevel());
    viewModel.greenLevelProperty().set(6);
    assertFalse(viewModel.validateGreenlevel());
  }

  @Test void validateLocation()
  {
    viewModel.locationProperty().set(null);
    assertFalse(viewModel.validateLocation());
    viewModel.locationProperty().set(new Location("q"));
    assertTrue(viewModel.validateLocation());
  }

  @Test void validateFuel()
  {
    viewModel.fuelProperty().set(null);
    assertFalse(viewModel.validateFuel());

    viewModel.fuelProperty().set("Benzin");
    assertTrue(viewModel.validateFuel());

    viewModel.fuelProperty().set("qasd");
    assertTrue(viewModel.validateFuel());
  }
}