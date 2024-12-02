package view;

import objects.Car;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import viewModel.CarlistForReservationViewModel;

public class CarlistForReservationView implements ViewController
{
  @FXML TableView<Car> carlistTable;
  @FXML TableColumn<Car, String> brandColumn;
  @FXML TableColumn<Car, String> modelColumn;
  @FXML TableColumn<Car, Integer> yearColumn;
  @FXML TableColumn<Car, Double> priceColumn;
  @FXML TableColumn<Car, String> descriptionColumn;
  @FXML TableColumn<Car, Integer> greenLevelColumn;
  @FXML TableColumn<Car, Integer> capacityColumn;

  @FXML TextField pickUpDate;
  @FXML TextField dropOffDate;
  @FXML TextField pickUp;
  @FXML TextField dropOff;

  private ViewHandler viewHandler;
  private CarlistForReservationViewModel viewModel;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getCarlistForReservationViewModel();

    carlistTable.setItems(viewModel.getCars());
    brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
    modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
    yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descripton"));
    greenLevelColumn.setCellValueFactory(new PropertyValueFactory<>("greenLevel"));
    capacityColumn.setCellValueFactory(new PropertyValueFactory<>("seatingCapacity"));

    pickUpDate.textProperty().bindBidirectional(viewModel.pickUpDateProperty());
    dropOffDate.textProperty().bindBidirectional(viewModel.returnDateProperty());
    pickUp.textProperty().bindBidirectional(viewModel.pickUpLocationProperty());
    dropOff.textProperty().bindBidirectional(viewModel.dropOffLocationProperty());

  }

  @Override public void close()
  {
    viewModel.close();
  }

  public void goBack()
  {
    viewHandler.openSelectDate();
  }

  public void reserveCar()
  {
    if (carlistTable.getSelectionModel().getSelectedItem() == null)
    {
      showAlert("Fejl", "Vælg venligst en bil fra listen");
    }
    else if (!viewModel.validateSelectedCar(carlistTable.getSelectionModel().getSelectedItem()))
    {
      showAlert("Fejl", "Bilen er allerede reserveret vælg venligst en anden bil");
      viewHandler.openSelectDate();
    }
    else
    {
      viewModel.selectCarToReserve(carlistTable.getSelectionModel().getSelectedItem());
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
}
