package view;

import objects.Location;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import viewModel.AddCarViewModel;

public class AddCarView implements ViewController
{
  @FXML TextField plateField;
  @FXML TextField brandField;
  @FXML TextField modelField;
  @FXML TextField yearField;
  @FXML ChoiceBox<String> fuelTypeField;
  @FXML ChoiceBox<Location> locationField;
  @FXML TextField seatCapacityField;
  @FXML TextField greenLevelField;
  @FXML TextField priceField;

  @FXML Button cancelButton;
  @FXML Button addButton;

  private ViewHandler viewHandler;
  private AddCarViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getAddCarViewModel();
    locationField.setItems(viewModel.getLocationList());
    fuelTypeField.setItems(viewModel.getFuelList());

    plateField.textProperty().bindBidirectional(viewModel.numberPlateProperty());
    brandField.textProperty().bindBidirectional(viewModel.brandProperty());
    modelField.textProperty().bindBidirectional(viewModel.modelProperty());
    yearField.textProperty().bindBidirectional(viewModel.yearProperty(), new NumberStringConverter());
    fuelTypeField.valueProperty().bindBidirectional(viewModel.fuelProperty());
    locationField.valueProperty().bindBidirectional(viewModel.locationProperty());
    seatCapacityField.textProperty().bindBidirectional(viewModel.capacityProperty(), new NumberStringConverter());
    greenLevelField.textProperty().bindBidirectional(viewModel.greenLevelProperty(), new NumberStringConverter());
    priceField.textProperty().bindBidirectional(viewModel.priceProperty(), new NumberStringConverter());
  }

  @Override public void close()
  {
    viewModel.close();
  }

  public void addCar()
  {
    if (!viewModel.validateBrand())
    {
      showAlert("Fejl i mærke", "Indtast venligst et correct mærke");
    }
    else if (!viewModel.validatePlate())
    {
      showAlert("Fejl i nummerplade", "Indtast venligst et korrekt nummerplade. Tjek evt. om bilen allerede er tilføjet.");
    }
    else if (!viewModel.validateModel())
    {
      showAlert("Fejl i model", "indtast venligst et model");
    }
    else if (!viewModel.validatePrice())
    {
      showAlert("Fejl i pris", "Bilens lejepris skal være over 0");
    }
    else if (!viewModel.validateYear())
    {
      showAlert("Fejl i årgang", "Indtast venligt et korrekt årstal");
    }
    else if (!viewModel.validateSeats())
    {
      showAlert("Fejl i antal sæder", "Venligst indtast et korrekt antal sæder");
    }
    else if (!viewModel.validateGreenlevel())
    {
      showAlert("Fejl i bæredygdig niveau", "Venligst indtast et correct bærdygdig niveau");
    }
    else if (!viewModel.validateLocation())
    {
      showAlert("Invalid lokation", "Vælg venligst en lokation");
    }
    else if (!viewModel.validateFuel())
    {
      showAlert("Invalid drivmiddel", "Vælg venligst et drivmiddel");
    }
    else
    {
      viewModel.addCar();
      plateField.clear();
      brandField.clear();
      modelField.clear();
      yearField.clear();
      priceField.clear();
      seatCapacityField.clear();
      greenLevelField.clear();
      viewHandler.closeSecondaryStage();
    }

  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  public void cancel()
  {
    viewHandler.closeSecondaryStage();
  }
}
