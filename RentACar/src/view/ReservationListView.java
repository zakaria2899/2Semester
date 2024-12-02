package view;

import javafx.scene.control.Alert;
import objects.Location;
import objects.MyDate;
import objects.Reservation;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import viewModel.ReservationListViewModel;

public class ReservationListView implements ViewController
{
  @FXML TableView<Reservation> reservationTable;
  @FXML TableColumn<Reservation, Integer> ReservationIdColumn;
  @FXML TableColumn<Reservation, String> customerColumn;
  @FXML TableColumn<Reservation, String> CarColumn;
  @FXML TableColumn<Reservation, MyDate> startDateColumn;
  @FXML TableColumn<Reservation, MyDate> endDateColumn;
  @FXML TableColumn<Reservation, Location> pickupLocationColumn;
  @FXML TableColumn<Reservation, Location> dropOffLocationColumn;

  @FXML TextField searchField;

  private ViewHandler viewHandler;
  private ReservationListViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModelFactory.getReservationListViewModel();
    searchField.textProperty().bindBidirectional(viewModel.searchBarProperty());
    reservationTable.setItems(viewModel.getReservationList());
    ReservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
    customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
    CarColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
    startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    pickupLocationColumn.setCellValueFactory(new PropertyValueFactory<>("pickupLocation"));
    dropOffLocationColumn.setCellValueFactory(new PropertyValueFactory<>("returnLocation"));
    viewModel.getReservations();
    refreshList();
  }



  public void openLocationsScene()
  {
    viewHandler.openLocationList();
  }

  public void openCustomerListScene()
  {
    viewHandler.openCustomerList();
  }

  public void openCarListScene()
  {
    viewHandler.openCarList();
  }

  public void refreshList()
  {
    viewModel.refreshList();
  }

  public void removeReservation()
  {
    if (reservationTable.getSelectionModel().getSelectedItem() != null)
    {
      Reservation reservation = reservationTable.getSelectionModel()
          .getSelectedItem();
      viewModel.removeReservation(reservation);
    }
    else
    {
      showAlert("Reservation ikke valgt", "Vælg en reservation fra listen");
    }
  }

  public void sort()
  {
    viewModel.sort();
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
