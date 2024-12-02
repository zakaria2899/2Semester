package core;

import model.CarRentModel;
import model.CarRentModelImpl;
import viewModel.*;
import viewModel.*;

public class ViewModelFactory
{
  private CarListViewModel carListViewModel;
  private LocationListViewModel locationListViewModel;
  private CustomerListViewModel customerListViewModel;
  private ReservationListViewModel reservationListViewModel;
  private AddCarViewModel addCarViewModel;
  private CustomerRegisterViewModel customerRegisterViewModel;
  private CustomerLoginViewModel customerLoginViewModel;
  private CustomerMainMenuViewModel customerMainMenuViewModel;
  private SelectDateViewModel selectDateViewModel;
  private CarlistForReservationViewModel carlistForReservationViewModel;


  private CarRentModel carRentModel;
  private static ViewModelFactory instance;

  private ViewModelFactory()
  {
    carRentModel = ModelFactory.getInstance().getCarRentModel();
    carListViewModel = new CarListViewModel(carRentModel);
    locationListViewModel = new LocationListViewModel(carRentModel);
    customerListViewModel  = new CustomerListViewModel(carRentModel);
    reservationListViewModel = new ReservationListViewModel(carRentModel);
    addCarViewModel = new AddCarViewModel(carRentModel);
    customerRegisterViewModel = new CustomerRegisterViewModel(carRentModel);
    customerLoginViewModel = new CustomerLoginViewModel(carRentModel);
    customerMainMenuViewModel = new CustomerMainMenuViewModel(carRentModel);
    selectDateViewModel = new SelectDateViewModel(carRentModel);
    carlistForReservationViewModel = new CarlistForReservationViewModel(carRentModel);
  }

  public static ViewModelFactory getInstance()
  {
    if (instance == null)
    {
      instance = new ViewModelFactory();
    }
    return instance;
  }

  public CarListViewModel getCarListViewModel()
  {
    return carListViewModel;
  }

  public LocationListViewModel getLocationListViewModel()
  {
    return locationListViewModel;
  }

  public CustomerListViewModel getCustomerListViewModel()
  {
    return customerListViewModel;
  }

  public ReservationListViewModel getReservationListViewModel()
  {
    return reservationListViewModel;
  }

  public AddCarViewModel getAddCarViewModel()
  {
    return addCarViewModel;
  }

  public CustomerRegisterViewModel getCustomerRegisterViewModel()
  {
    return customerRegisterViewModel;
  }

  public CustomerLoginViewModel getCustomerLoginViewModel()
  {
    return customerLoginViewModel;
  }

  public CustomerMainMenuViewModel getCustomerMainMenuViewModel()
  {
    return customerMainMenuViewModel;
  }

  public SelectDateViewModel getSelectDateViewModel()
  {
    return selectDateViewModel;
  }

  public CarlistForReservationViewModel getCarlistForReservationViewModel()
  {
    return carlistForReservationViewModel;
  }
}
