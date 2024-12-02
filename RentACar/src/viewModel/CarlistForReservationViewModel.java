package viewModel;

import model.CarRentModel;
import objects.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class CarlistForReservationViewModel
{
  private CarRentModel carRentModel;

  private ObservableList<Car> cars;
  private StringProperty pickUpDate;
  private StringProperty returnDate;
  private StringProperty pickUpLocation;
  private StringProperty dropOffLocation;
  private Customer customer;
  private MyDate StartDate;
  private MyDate EndDate;
  private Location StartLocation;
  private Location EndLocation;

  public CarlistForReservationViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    carRentModel.AddObserver("CarListWithDate", this::refreshList);
    carRentModel.AddObserver("CarSearch", this::refreshData);
    carRentModel.AddObserver("login", this::refreshCustomer);
    cars = FXCollections.observableArrayList();
    pickUpDate = new SimpleStringProperty();
    returnDate = new SimpleStringProperty();
    pickUpLocation = new SimpleStringProperty();
    dropOffLocation = new SimpleStringProperty();
  }

  private void refreshCustomer(PropertyChangeEvent propertyChangeEvent)
  {
    customer = (Customer) propertyChangeEvent.getNewValue();
  }

  private void refreshData(PropertyChangeEvent propertyChangeEvent)
  {
    ArrayList<Object> objects = (ArrayList<Object>) propertyChangeEvent.getNewValue();
    StartDate = (MyDate) objects.get(0);
    EndDate = (MyDate) objects.get(1);
    StartLocation = (Location) objects.get(2);
    EndLocation = (Location) objects.get(3);
    pickUpDate.set(StartDate.toString());
    returnDate.set(EndDate.toString());
    pickUpLocation.set(StartLocation.toString());
    dropOffLocation.set(EndLocation.toString());
  }

  private void refreshList(PropertyChangeEvent propertyChangeEvent)
  {
    cars.clear();
    cars.addAll((List<Car>)propertyChangeEvent.getNewValue());
  }

  public ObservableList<Car> getCars()
  {
    return cars;
  }

  public StringProperty pickUpDateProperty()
  {
    return pickUpDate;
  }

  public StringProperty returnDateProperty()
  {
    return returnDate;
  }

  public StringProperty pickUpLocationProperty()
  {
    return pickUpLocation;
  }

  public StringProperty dropOffLocationProperty()
  {
    return dropOffLocation;
  }

  public boolean validateSelectedCar(Car car)
  {
    if (carRentModel.getCars().contains(car))
    {
      String[] pickUpDate = pickUpDateProperty().get().split("/");
      String[] returnDate = returnDateProperty().get().split("/");
      MyDate pickupdate = new MyDate(Integer.parseInt(pickUpDate[0]), Integer.parseInt(pickUpDate[1]), Integer.parseInt(pickUpDate[2]));
      MyDate returndate = new MyDate(Integer.parseInt(returnDate[0]), Integer.parseInt(returnDate[1]), Integer.parseInt(returnDate[2]));
      Car car1 = carRentModel.getCars().get(carRentModel.getCars().indexOf(car));
      if (carRentModel.validateCarReservation(car1, pickupdate, returndate))
      {
        return true;
      }
    }
    return false;
  }

  public void selectCarToReserve(Car car)
  {
    String[] pickUpDate = pickUpDateProperty().get().split("/");
    String[] returnDate = returnDateProperty().get().split("/");
    MyDate pickupdate = new MyDate(Integer.parseInt(pickUpDate[0]), Integer.parseInt(pickUpDate[1]), Integer.parseInt(pickUpDate[2]));
    MyDate returndate = new MyDate(Integer.parseInt(returnDate[0]), Integer.parseInt(returnDate[1]), Integer.parseInt(returnDate[2]));

    carRentModel.addReservation(new Reservation(0, car, customer, StartLocation, EndLocation, StartDate, EndDate));
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
