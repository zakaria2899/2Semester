package view;

import objects.Location;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import viewModel.SelectDateViewModel;

public class SelectDateView implements ViewController
{
  @FXML ChoiceBox<Location> pickupLocation;
  @FXML ChoiceBox<Location> returnLocation;
  @FXML DatePicker startDatePicker;
  @FXML DatePicker endDatePicker;
  @FXML Button searchCarButton;
  @FXML Button addCarButton;
  private ViewHandler viewHandler;
  private SelectDateViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModelFactory.getSelectDateViewModel();
    pickupLocation.setItems(viewModel.getLocations());
    returnLocation.setItems(viewModel.getLocations());
    pickupLocation.valueProperty().bindBidirectional(viewModel.pickUpLocationProperty());
    returnLocation.valueProperty().bindBidirectional(viewModel.returnLocationProperty());
    startDatePicker.valueProperty().bindBidirectional(viewModel.pickUpDatePropertyProperty());
    endDatePicker.valueProperty().bindBidirectional(viewModel.returnDatePropertyProperty());
    viewModel.refreshLocations();


  }

  @Override public void close()
  {
    viewModel.close();
  }

  public void onSearchCarButtonPressed()
  {
    if (!viewModel.validateDates())
    {
      showAlert("Fejl i datoer", "Vælg venligst gyldige datoer. Husk at du ikke kan vælge dato før idag.");
    }
    else if (!viewModel.validateLocation())
    {
      showAlert("Fejl i lokationer", "Vælg venligst gyldige lokationer");
    }
    else
    {
      viewModel.searchCar();
      viewHandler.openCarlistForReservation();
    }
  }

  public void onAddCarButtonPressed(){

    viewHandler.openAddCar();
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}


