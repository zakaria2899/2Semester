package view;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import viewModel.LocationListViewModel;

public class AddLocationView implements ViewController
{
  ViewHandler viewHandler;
  LocationListViewModel viewModel;
  @FXML TextField addLocationField;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getLocationListViewModel();
    addLocationField.textProperty().bindBidirectional(viewModel.addLocationProperty());
  }

  @Override public void close()
  {
    viewHandler.closeSecondaryStage();
  }

  public void addLocation()
  {
    if (viewModel.validateNewLocation())
    {
      viewModel.addLocation();
      viewHandler.closeSecondaryStage();
    }
    else
    {
      showAlert("Fejl ved location", "Indtast venligst et navn som ikke allerede findes");
    }
  }

  public void cancelNewLocation()
  {
    viewHandler.closeSecondaryStage();
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
