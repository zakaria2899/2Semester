package viewModel;

import model.CarRentModel;
import objects.Car;
import objects.Location;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import java.util.List;

public class AddCarViewModel
{
  private CarRentModel carRentModel;

  private StringProperty numberPlate;
  private StringProperty brand;
  private IntegerProperty year;
  private StringProperty model;
  private StringProperty fuel;
  private ObjectProperty<Location> location;
  private IntegerProperty capacity;
  private IntegerProperty greenLevel;
  private DoubleProperty price;

  private ObservableList<Location> locationList;
  private ObservableList<String> fuelList;

  public AddCarViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    carRentModel.AddObserver("locations", this::refreshLocations);
    locationList = FXCollections.observableArrayList();

    this.numberPlate = new SimpleStringProperty();
    this.brand = new SimpleStringProperty();
    this.year = new SimpleIntegerProperty();
    this.model = new SimpleStringProperty();
    this.fuel = new SimpleStringProperty();
    this.location = new SimpleObjectProperty<>();
    this.capacity = new SimpleIntegerProperty();
    this.greenLevel = new SimpleIntegerProperty();
    this.price = new SimpleDoubleProperty();

    fuelList = FXCollections.observableArrayList();
    fuelList.add("Benzin");
    fuelList.add("Diesel");
    fuelList.add("El");
    fuelList.add("Hybrid, benzin");
    fuelList.add("Hybrid, diesel");


  }

  private void refreshLocations(PropertyChangeEvent propertyChangeEvent)
  {
    locationList.clear();
    locationList.addAll((List<Location>) propertyChangeEvent.getNewValue());
  }

  public void getLocations()
  {
    locationList.clear();
    locationList.addAll((List<Location>) carRentModel.getLocations());
  }

  public void addCar()
  {
    String plate = numberPlate.get().substring(0,2).toUpperCase() + numberPlate.get().substring(2);
    String Brand = brand.get().substring(0,1).toUpperCase() + brand.get().substring(1);
    String Model = model.get().substring(0,1).toUpperCase() + model.get().substring(1);
    String Fuel = fuel.get().substring(0,1).toUpperCase() + fuel.get().substring(1);

    carRentModel.addCar(new Car(plate, Brand, Model,"",year.get(), capacity.get(), greenLevel.get(), Fuel, price.get(), location.get()));
  }



  public StringProperty numberPlateProperty()
  {
    return numberPlate;
  }

  public StringProperty brandProperty()
  {
    return brand;
  }

  public IntegerProperty yearProperty()
  {
    return year;
  }

  public StringProperty modelProperty()
  {
    return model;
  }

  public StringProperty fuelProperty()
  {
    return fuel;
  }

  public ObjectProperty<Location> locationProperty()
  {
    return location;
  }

  public IntegerProperty capacityProperty()
  {
    return capacity;
  }

  public IntegerProperty greenLevelProperty()
  {
    return greenLevel;
  }

  public DoubleProperty priceProperty()
  {
    return price;
  }

  public ObservableList<Location> getLocationList()
  {
    return locationList;
  }

  public ObservableList<String> getFuelList()
  {
    return fuelList;
  }

  public void close()
  {
    carRentModel.stopClient();
  }

  /**
   * This method validates the input that is given for the plate of the car.
   * The validation checks if the plates first 2 chars is a letter and rest is digits
   */
  public boolean validatePlate()
  {
    if (numberPlate.get() == null || numberPlate.get().length() != 7)
    {
      return false;
    }

    for (int i = 0; i < numberPlate.get().length(); i++)
    {
      if (i < 2 && !Character.isLetter(numberPlate.get().charAt(i)))
      {
        return false;
      }
      else if (i >= 2 && !Character.isDigit(numberPlate.get().charAt(i)))
      {
        return false;
      }
    }
    for (Car car : carRentModel.getCars())
    {
      if (car.getPlate().equals(numberPlate.get()))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * This method validates the input that is given for the brand of the car.
   * The validation is to make sure it is not empty
   */
  public boolean validateBrand()
  {
    if (brand.get()==null || brand.get().isEmpty())
    {
      return false;
    }
    String[] brand1 = brand.get().split(" ");
    String brand2 = brand1[0];
    if (brand1.length > 1)
    {
      for (int i = 1; i < brand1.length; i++)
      {
        brand2 = brand2 + brand1[i];
      }
    }
    for (int i = 0; i < brand2.length(); i++)
    {
      if (!Character.isLetter(brand2.charAt(i)))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * This method validates the input that is given for the model of the car.
   * The validation checks if the input is null.
   */
  public boolean validateModel()
  {
    if (model.get()==null)
    {
      return false;
    }
    return true;
  }

  // Validating price so it isn't 0 or below
  /**
   * This method validates the input that is given for the rentprice of the car.
   * The validation checks if the price is higher than 0.
   */
  public boolean validatePrice()
  {
    try
    {;
      return price.get() > 0;
    } catch (NumberFormatException e)
    {
      return false;
    }
  }

  /**
   * This method validates the input that is given for the year of the car.
   * The validation is to make sure the year is between the current one and 1900.
   */
  public boolean validateYear()
  {
    if (year.get() < 1900)
    {
      return false;
    }
    try {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      return year.get() <= currentYear;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * This method validates the input that is given for the seat capacity of the car.
   * The validation is to make sure the capacity is set between the limits.
   */
  public boolean validateSeats()
  {
    try {
      return capacity.get() >= 2 && capacity.get() <= 10;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // Validation to make sure it is set between 1 and 5
  /**
   * This method validates the input that is given for the sustainable level of the car.
   * The validation is to make sure it is set between the limits.
   */
  public boolean validateGreenlevel()
  {
    if (greenLevel.get()==0)
    {
      return false;
    }
    return greenLevel.get() >= 1 && greenLevel.get() <= 5;
  }

  //Validation for only if it is null.
  public boolean validateLocation()
  {
    return location.get() != null;
  }

  //Validation for only if it is null.
  public boolean validateFuel()
  {
    return fuel.get() != null;
  }
}
