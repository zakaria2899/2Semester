package viewModel;

import model.CarRentModel;
import objects.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class LocationListViewModel
{
  private CarRentModel carRentModel;
  private ObservableList<Location> locationList;
  private StringProperty searchBar;
  private StringProperty addLocation;

  public LocationListViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    locationList = FXCollections.observableArrayList();
    searchBar = new SimpleStringProperty();
    addLocation = new SimpleStringProperty();
    carRentModel.AddObserver("locations",this::refreshLocations);
  }

  private void refreshLocations(PropertyChangeEvent propertyChangeEvent)
  {
    locationList.clear();
    locationList.addAll((List<Location>) propertyChangeEvent.getNewValue());
  }

  public void refreshList()
  {
    carRentModel.askLocations();
  }

  public void getLocations()
  {
    locationList.clear();
    locationList.addAll(carRentModel.getLocations());
  }

  public ObservableList<Location> getLocationList()
  {
    return locationList;
  }

  public StringProperty searchBarProperty()
  {
    return searchBar;
  }

  public StringProperty addLocationProperty()
  {
    return addLocation;
  }

  public boolean validateNewLocation()
  {
    if (addLocation.getValue() == null || addLocation.getValue().isEmpty())
    {
      return false;
    }
    boolean found = true;
    for (Location location : locationList)
    {
      if (location.getLocation().equalsIgnoreCase(addLocation.get()))
      {
        found = false;
        break;
      }
    }
    return found;
  }

  public void removeLocation(Location location)
  {
    carRentModel.removeLocation(location);
  }

  public void addLocation()
  {
    carRentModel.addLocation(new Location(addLocation.get()));
  }

  public void sort()
  {
    getLocations();
    if (searchBar.get().equals("") || searchBar.get().isEmpty())
    {
      getLocations();
    }
    else
    {
      for (Location location : locationList)
      {
        if (!location.getLocation().contains(searchBar.get()))
        {
          locationList.remove(location);
        }
      }
    }
    searchBar.set("");
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
