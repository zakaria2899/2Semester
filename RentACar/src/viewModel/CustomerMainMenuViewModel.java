package viewModel;

import model.CarRentModel;
import objects.Customer;
import objects.MyDate;
import objects.Reservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomerMainMenuViewModel
{
  private StringProperty fullName;
  private Customer customer;
  private CarRentModel carRentModel;
  private List<Reservation> reservationList;
  private ObservableList<Reservation> reservationObservableList;

  public CustomerMainMenuViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    carRentModel.AddObserver("login", this::refreshLogin);
    carRentModel.AddObserver("reservations", this::refreshReservations);
    fullName = new SimpleStringProperty();
    reservationList = new ArrayList<>();
    reservationObservableList = FXCollections.observableArrayList();
  }

  private void refreshReservations(PropertyChangeEvent propertyChangeEvent)
  {
    if (reservationList != null)
    {
      reservationList.clear();
    }
    reservationList.addAll((List<Reservation>) propertyChangeEvent.getNewValue());
    if (customer != null)
    {
      sortReservations();
    }
  }

  public void refreshList()
  {
    carRentModel.askReservations();
  }

  public void getReservations()
  {
    reservationList.clear();
    reservationList.addAll(carRentModel.getReservations());
    if (customer != null)
    {
      sortReservations();
    }
  }

  private void sortReservations()
  {
    reservationObservableList.clear();
    for (Reservation reservation : reservationList)
    {
      if (reservation.getCustomer().getCprNumber().equals(customer.getCprNumber()))
      {
        reservationObservableList.add(reservation);
      }
    }
  }

  private void refreshLogin(PropertyChangeEvent propertyChangeEvent)
  {
    customer = null;
    customer = (Customer) propertyChangeEvent.getNewValue();
    fullName.set(customer.getFirstName() + " " + customer.getLastName());
  }

  public StringProperty fullNameProperty()
  {
    return fullName;
  }

  public ObservableList<Reservation> getReservationObservableList()
  {
    return reservationObservableList;
  }

  public boolean validateReservation(Reservation reservation)
  {
    if (reservation.getStartDate().isBefore(new MyDate()) || reservation.getStartDate().daysBetween(new MyDate()) == 0)
    {
      return false;
    }
    return true;
  }

  public void cancelReservation(Reservation reservation)
  {
    carRentModel.removeReservation(reservation);
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
