package viewModel;

import model.CarRentModel;
import objects.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class CustomerListViewModel
{
  private ObservableList<Customer> customerList;
  private CarRentModel carRentModel;
  private StringProperty searchBar;

  public CustomerListViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    carRentModel.AddObserver("customers", this::refreshCustomers);
    searchBar = new SimpleStringProperty();
    customerList = FXCollections.observableArrayList();
  }

  private void refreshCustomers(PropertyChangeEvent propertyChangeEvent)
  {
    customerList.clear();
    customerList.addAll((List<Customer>) propertyChangeEvent.getNewValue());
  }

  public void RefreshCustomers()
  {
    carRentModel.askCustomers();
  }

  public void getCustomers()
  {
    customerList.clear();
    customerList.addAll(carRentModel.getCustomers());
  }

  public void removeCustomer(Customer customer)
  {
    carRentModel.removeCustomer(customer);
  }

  public void login(Customer customer)
  {
    carRentModel.customerLogin(customer);
  }

  public void sort()
  {
    getCustomers();
    if (searchBar.get().equals("") || searchBar.get().isEmpty())
    {
      getCustomers();
    }
    else
    {
      for (Customer customer : customerList)
      {
        if (!customer.toString().contains(searchBar.get()))
        {
          customerList.remove(customer);
        }
      }
    }
    searchBar.set("");
  }

  public StringProperty searchBarProperty()
  {
    return searchBar;
  }

  public ObservableList<Customer> getCustomerList()
  {
    return customerList;
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
