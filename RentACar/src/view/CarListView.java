package view;

import javafx.scene.control.*;
import objects.Car;
import objects.Location;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import viewModel.CarListViewModel;

public class CarListView implements ViewController
{
  @FXML TableView<Car> table;
  @FXML TableColumn<Car, String> plateColumn;
  @FXML TableColumn<Car, String> brandColumn;
  @FXML TableColumn<Car, String> modelColumn;
  @FXML TableColumn<Car, Location> locationColumn;
  @FXML TableColumn<Car, Double> priceColumn;
  @FXML TextField searchField;
  @FXML Button carButton;

  private ViewHandler viewHandler;
  private CarListViewModel carListViewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.carListViewModel = viewModelFactory.getCarListViewModel();
    searchField.textProperty().bindBidirectional(carListViewModel.searchBarProperty());
    table.setItems(carListViewModel.getCarList());
    plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
    brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
    modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("currentLocation"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
    carListViewModel.getCars();
    refreshList();
  }

  public void refreshList()
  {
    carListViewModel.refreshList();
  }

  public void openLocationsScene()
  {
    viewHandler.openLocationList();
  }

  public void openCustomerListScene()
  {
    viewHandler.openCustomerList();
  }

  public void openReservationListScene()
  {
    viewHandler.openReservationList();
  }

  public void removeCar()
  {
    if (table.getSelectionModel().getSelectedItem() != null)
    {
      Car car = table.getSelectionModel().getSelectedItem();
      carListViewModel.removeCar(car);
    }
    else
    {
      showAlert("Bil ikke valgt", "Vælg en bil fra listen");
    }
  }

  public void openAddCarScene()
  {
    viewHandler.openAddCar();
  }

  @Override public void close()
  {
    carListViewModel.close();
  }

  public void sort()
  {
    carListViewModel.sort();
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
