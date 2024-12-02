package view;

import javafx.scene.control.Alert;
import objects.Location;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import viewModel.LocationListViewModel;

public class LocationListView implements ViewController
{
  @FXML TableView<Location> locationTable;
  @FXML TableColumn<Location, String> locationColumn;
  @FXML TableColumn<Location, Integer> numberOfCarsColumn;
  @FXML TextField searchField;


  private ViewHandler viewHandler;
  private LocationListViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getLocationListViewModel();

    searchField.textProperty().bindBidirectional(viewModel.searchBarProperty());

    locationTable.setItems(viewModel.getLocationList());
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    numberOfCarsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfCars"));
    viewModel.getLocations();
    refreshList();
  }

  public void refreshList()
  {
    viewModel.refreshList();
  }

  public void newLocation()
  {
    viewHandler.openAddLocation();
  }

 public void removeLocation()
 {
   if (locationTable.getSelectionModel().getSelectedItem() != null)
   {
     viewModel.removeLocation(locationTable.getSelectionModel().getSelectedItem());
   }
   else
   {
     showAlert("Lokation ikke valgt", "Vælg en lokation fra listen");
   }
 }

  public void openCarListScene()
  {
    viewHandler.openCarList();
  }

  public void sort()
  {
    viewModel.sort();
  }
  public void openCustomerListScene()
  {
    viewHandler.openCustomerList();
  }

  public void openReservationListScene()
  {
    viewHandler.openReservationList();
  }

  @Override public void close()
  {
    viewModel.close();
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
