package viewModel;

import model.CarRentModel;
import objects.Car;
import objects.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class CarListViewModel
{
  private CarRentModel model;
  private ObservableList<Car> cars;
  private StringProperty searchBar;

  public CarListViewModel(CarRentModel carRentModel)
  {
    model = carRentModel;
    cars = FXCollections.observableArrayList();
    searchBar = new SimpleStringProperty();
    model.AddObserver("cars", this::refreshCars);
  }

  private void refreshCars(PropertyChangeEvent propertyChangeEvent)
  {
    cars.clear();
    cars.addAll((List<Car>) propertyChangeEvent.getNewValue());
  }

  public void sort()
  {
    getCars();
    cars.clear();
    if (searchBar.get() == null || searchBar.get().isEmpty())
    {
      System.out.println("searchBar is empty");
      getCars();
    }
    else
    {
      ArrayList<Car> org = model.getCars();
      ArrayList<Car> temp = new ArrayList<>(cars);
      for (Car car : org)
      {
        if (car.getPlate().contains(searchBar.get()) || car.getModel().contains(searchBar.get()) || car.getModel().contains(searchBar.get()) || car.getDescripton().contains(searchBar.get()) || car.getFuelType().contains(searchBar.get()) || car.toString().contains(searchBar.get()) || car.getCurrentLocation().getLocation().contains(searchBar.get()))
        {
          System.out.println("found");
          temp.add(car);
        }
      }
      cars.addAll(temp);
    }
    searchBar.set("");
  }

  public void refreshList()
  {
    model.askCars();
  }

  public void getCars()
  {
    cars.clear();
    cars.addAll(model.getCars());
  }

  public ObservableList<Car> getCarList()
  {
    return cars;
  }

  public StringProperty searchBarProperty()
  {
    return searchBar;
  }

  public void addCar()
  {
    model.addCar(new Car("bb60986", "Opel", "Insignia", "", 2014, 5, 3, "Diesel", 3000, new Location("Vejle")));
  }
  public void addAnotherCar()
  {
    model.addCar(new Car("dx61567", "Fiat", "Punto", "Lorte bil", 2013, 5, 2, "Diesel", 31, new Location("Århus")));
  }

  public void removeCar(Car car)
  {
    model.removeCar(car);
  }

  public void close()
  {
    model.stopClient();
  }
}
