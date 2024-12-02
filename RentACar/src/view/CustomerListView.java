package view;

import javafx.scene.control.Alert;
import objects.Customer;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import viewModel.CustomerListViewModel;

public class CustomerListView implements ViewController
{
  @FXML TableView<Customer> table;
  @FXML TableColumn<Customer, String> cprNumber;
  @FXML TableColumn<Customer, String> firstName;
  @FXML TableColumn<Customer, String> lastName;
  @FXML TableColumn<Customer, String> city;
  @FXML TableColumn<Customer, Integer> number;

  @FXML TextField searchField;

  private ViewHandler viewHandler;
  private CustomerListViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModelFactory.getCustomerListViewModel();
    table.setItems(viewModel.getCustomerList());
    cprNumber.setCellValueFactory(new PropertyValueFactory<>("cprNumber"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    city.setCellValueFactory(new PropertyValueFactory<>("city"));
    number.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    viewModel.getCustomerList();
    refreshList();
  }

  public void refreshList()
  {
    viewModel.RefreshCustomers();
  }

  public void openCarListScene()
  {
    viewHandler.openCarList();
  }

  public void openLocationsScene()
  {
    viewHandler.openLocationList();
  }

  public void addCustomer()
  {
    viewHandler.openClientRegisterScene();
  }

  public void addReservation()
  {
    if (table.getSelectionModel().getSelectedItem() != null)
    {
      viewModel.login(table.getSelectionModel().getSelectedItem());
      viewHandler.openSelectDate();
    }
    else
    {
      showAlert("Ingen kunde", "Vælg en kunde fra listen");
    }
  }
  public void removeCustomer()
  {
    if (table.getSelectionModel().getSelectedItem() != null)
    {
      viewModel.removeCustomer(table.getSelectionModel().getSelectedItem());
    }
    else
    {
      showAlert("Kunde ikke valgt", "Vælg en kunde fra listen");
    }
  }

  public void openReservationListScene()
  {
    viewHandler.openReservationList();
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
