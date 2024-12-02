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

public class CustomerLoginViewModel
{
  private StringProperty userName;
  private StringProperty password;
  private ObservableList<Customer> customerList;

  private CarRentModel carRentModel;

  public CustomerLoginViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    carRentModel.AddObserver("customers", this::refreshList);

    userName = new SimpleStringProperty();
    password = new SimpleStringProperty();
    customerList = FXCollections.observableArrayList();

  }

  private void refreshList(PropertyChangeEvent propertyChangeEvent)
  {
    customerList.clear();
    customerList.addAll((List<Customer>)propertyChangeEvent.getNewValue());
  }

  public StringProperty userNameProperty()
  {
    return userName;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public void getCustomers()
  {
    customerList.clear();
    customerList.addAll(carRentModel.getCustomers());
  }

  public void refreshList()
  {
    carRentModel.askCustomers();
  }

  public boolean validateLogin()
  {
    refreshList();
    System.out.println(customerList.size());
    for (Customer customer : customerList)
    {
      if (customer.getCprNumber().equalsIgnoreCase(password.get()) && customer.getEmail().equalsIgnoreCase(userName.get()))
      {
        return true;
      }
    }
    return false;
  }

  public void login()
  {
    for (Customer customer : customerList)
    {
      if (customer.getCprNumber().equalsIgnoreCase(password.get()) && customer.getEmail().equalsIgnoreCase(userName.get()))
      {
        carRentModel.customerLogin(customer);
      }
    }
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
