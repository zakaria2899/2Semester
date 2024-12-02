package viewModel;

import model.CarRentModel;
import objects.Location;
import objects.MyDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.util.List;

public class SelectDateViewModel
{
  private ObservableList<Location> locations;
  private ObjectProperty<Location> pickUpLocation;
  private ObjectProperty<Location> returnLocation;
  private ObjectProperty<LocalDate> pickUpDateProperty;
  private ObjectProperty<LocalDate> returnDateProperty;
  private CarRentModel model;

  public SelectDateViewModel(CarRentModel model)
  {
    this.model = model;
    locations = FXCollections.observableArrayList();
    pickUpDateProperty = new SimpleObjectProperty();
    returnDateProperty = new SimpleObjectProperty();
    pickUpLocation = new SimpleObjectProperty();
    returnLocation = new SimpleObjectProperty();
    model.AddObserver("locations", this::getLocationsFromModel);
  }

  private void getLocationsFromModel(PropertyChangeEvent propertyChangeEvent)
  {
    locations.clear();
    locations.addAll((List<Location>)propertyChangeEvent.getNewValue());
  }

  public void refreshList()
  {
    locations.clear();
    locations.addAll(model.getLocations());
  }

  public void refreshLocations()
  {
    model.askLocations();
  }

  public ObservableList<Location> getLocations()
  {
    return locations;
  }

  public ObjectProperty pickUpLocationProperty()
  {
    return pickUpLocation;
  }

  public ObjectProperty returnLocationProperty()
  {
    return returnLocation;
  }

  public ObjectProperty pickUpDatePropertyProperty()
  {
    return pickUpDateProperty;
  }

  public ObjectProperty returnDatePropertyProperty()
  {
    return returnDateProperty;
  }

  public void searchCar()
  {
    MyDate startDate = new MyDate(pickUpDateProperty.get().getDayOfMonth(), pickUpDateProperty.get().getMonthValue(), pickUpDateProperty.get().getYear());
    MyDate endDate = new MyDate(returnDateProperty.get().getDayOfMonth(), returnDateProperty.get().getMonthValue(), returnDateProperty.get().getYear());
    model.searchCar(startDate, endDate, pickUpLocation.get(), returnLocation.get());
  }

  public boolean validateDates()
  {
    if (pickUpDateProperty.get() == null || returnDateProperty.get() == null)
    {
      return false;
    }
    MyDate startDate = new MyDate(pickUpDateProperty.get().getDayOfMonth(), pickUpDateProperty.get().getMonthValue(), pickUpDateProperty.get().getYear());
    MyDate endDate = new MyDate(returnDateProperty.get().getDayOfMonth(), returnDateProperty.get().getMonthValue(), returnDateProperty.get().getYear());

    System.out.println(startDate.toString());
    System.out.println(endDate.toString());
    if (startDate.isAfter(endDate))
    {
      return false;
    }
    else if (startDate.isBefore(new MyDate()) || endDate.isBefore(new MyDate()))
    {
      return false;
    }
    return true;
  }

  public boolean validateLocation()
  {
    if (pickUpLocation.get() == null || pickUpLocation.get() == null)
    {
      return false;
    }
    return true;
  }

  public void close()
  {
    model.stopClient();
  }


}
