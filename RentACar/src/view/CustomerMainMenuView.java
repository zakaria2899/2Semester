package view;

import javafx.scene.control.Alert;
import objects.*;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import viewModel.CustomerMainMenuViewModel;

public class CustomerMainMenuView implements ViewController
{
  @FXML TableView<Reservation> reservations;
  @FXML TableColumn<Reservation, String> reservationID;
  @FXML TableColumn<Reservation, Car> car;
  @FXML TableColumn<Reservation, MyDate> pickUpDate;
  @FXML TableColumn<Reservation, MyDate> returnDate;
  @FXML TableColumn<Reservation, Location> pickUpLocation;
  @FXML TableColumn<Reservation, Location> returnLocation;

  @FXML Text customerFullName;

  private ViewHandler viewHandler;
  private CustomerMainMenuViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getCustomerMainMenuViewModel();

    reservations.setItems(viewModel.getReservationObservableList());
    reservationID.setCellValueFactory(new PropertyValueFactory<Reservation, String>("reservationID"));
    car.setCellValueFactory(new PropertyValueFactory<Reservation,Car>("car"));
    pickUpDate.setCellValueFactory(new PropertyValueFactory<Reservation,MyDate>("startDate"));
    returnDate.setCellValueFactory(new PropertyValueFactory<Reservation,MyDate>("endDate"));
    pickUpLocation.setCellValueFactory(new PropertyValueFactory<Reservation,Location>("pickupLocation"));
    returnLocation.setCellValueFactory(new PropertyValueFactory<Reservation,Location>("returnLocation"));

    customerFullName.textProperty().bindBidirectional(viewModel.fullNameProperty());
    refreshList();

  }

  @Override public void close()
  {
    viewModel.close();
  }

  public void refreshList()
  {
    viewModel.refreshList();
  }

  public void openNewReservation()
  {
    viewHandler.openSelectDate();
  }

  public void cancelReservation()
  {
    if (reservations.getSelectionModel().getSelectedItem() != null)
    {
      if (viewModel.validateReservation(reservations.getSelectionModel().getSelectedItem()))
      {
        viewModel.cancelReservation(reservations.getSelectionModel().getSelectedItem());
      }
      else
      {
        showAlert("Kan ikke annuleres", "Fristen for annullering af reservationen er udgået");
      }

    }
    else
    {
      showAlert("Reservation ikke valgt", "Vælg en reservation fra listen");
    }
  }

  public void logOut()
  {
    viewHandler.openCustomerLogin();
  }

  public void openContactPage()
  {
    viewHandler.openContactPage();
  }

  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

}
