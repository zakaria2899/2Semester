package viewModel;

import model.CarRentModel;
import objects.Location;
import objects.MyDate;
import objects.Reservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarRentModelImpl;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class ReservationListViewModel
{
  private CarRentModel carRentModel;
  private ObservableList<Reservation> reservations;
  private StringProperty searchBar;


  public ReservationListViewModel(CarRentModel carRentModel)
  {
    this.carRentModel = carRentModel;
    carRentModel.AddObserver("reservations", this::refreshReservations);
    reservations = FXCollections.observableArrayList();
    searchBar = new SimpleStringProperty();
  }

  private void refreshReservations(PropertyChangeEvent propertyChangeEvent)
  {
    reservations.clear();
    reservations.addAll((List<Reservation>) propertyChangeEvent.getNewValue());
  }

  public void refreshList()
  {
    carRentModel.askReservations();
  }

  public void getReservations()
  {
    reservations.clear();
    reservations.addAll(carRentModel.getReservations());
  }

  public ObservableList<Reservation> getReservationList()
  {
    return reservations;
  }

  public StringProperty searchBarProperty()
  {
    return searchBar;
  }

  public void removeReservation(Reservation reservation)
  {
    carRentModel.removeReservation(reservation);
  }

  public void sort()
  {
    getReservations();
    if (searchBar.get().equals("") || searchBar.get().isEmpty())
    {
      getReservations();
    }
    else
    {
      for (Reservation reservation : reservations)
      {
        if (!reservation.getCar().toString().contains(searchBar.get()) && !reservation.getCustomer().toString().contains(searchBar.get()))
        {
          reservations.remove(reservation);
        }
      }
    }
    searchBar.set("");
  }

  public void close()
  {
    carRentModel.stopClient();
  }
}
