package viewModel;

import core.ModelFactory;
import objects.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationListViewModelTest
{
  //make sure server is running and ready for connections

  LocationListViewModel viewModel = new LocationListViewModel(ModelFactory.getInstance()
      .getCarRentModel());

  @Test void validateNewLocation()
  {
    viewModel.getLocationList().add(new Location("Sarkisla"));
    viewModel.addLocationProperty().set("Sarkisla");
    assertFalse(viewModel.validateNewLocation());
    viewModel.addLocationProperty().set("not Sarkisla");
    assertTrue(viewModel.validateNewLocation());
    viewModel.addLocationProperty().set(null);
    assertFalse(viewModel.validateNewLocation());
  }
}