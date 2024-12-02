package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewController;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;

  private Scene carListScene;
  private Scene locationListScene;
  private Scene customerListScene;
  private Scene reservationListScene;
  private Scene addCarScene;
  private Scene clientRegisterScene;
  private Scene customerLoginScene;
  private Scene customerMainMenuScene;
  private Scene selectDateScene;
  private Scene carlistForReservationScene;
  private Scene addLocationScene;
  private Scene contactPageScene;


  private Stage primaryStage;
  private Stage secondaryStage;


  public ViewHandler()
  {
    viewModelFactory = ViewModelFactory.getInstance();
    primaryStage = new Stage();
    secondaryStage = new Stage();
    primaryStage.setResizable(false);
    secondaryStage.setResizable(false);
  }

  public void startStaff()
  {
    openCarList();
  }

  public void startCustomer()
  {
    openCustomerLogin();
  }

  public void openAddCar()
  {
    addCarScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/AddCar.fxml");

      secondaryStage.setTitle("Tilføj bil");
      addCarScene = (new Scene(root));
    }
    catch (IOException e)
    {
      System.out.println("Failed to open Add Car fxml:" + e.getMessage());
      e.printStackTrace();
    }
    secondaryStage.setScene(addCarScene);
    secondaryStage.show();
  }

  public void openCarList()
  {
    carListScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/CarList.fxml");

      primaryStage.setTitle("car rent app");
      carListScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    primaryStage.setScene(carListScene);
    primaryStage.show();

  }

  public void openLocationList()
  {
    locationListScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/LocationList.fxml");

      locationListScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    primaryStage.setScene(locationListScene);
    primaryStage.show();
  }

  public void openCustomerList()
  {
    customerListScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/CustomerList.fxml");

      customerListScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    primaryStage.setScene(customerListScene);
    primaryStage.show();
  }

  public void openReservationList()
  {
    reservationListScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/ReservationList.fxml");

      reservationListScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    primaryStage.setScene(reservationListScene);
    primaryStage.show();
  }

  public void openClientRegisterScene()
  {
    clientRegisterScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/CustomerRegister.fxml");

      secondaryStage.setTitle("Opret kunde");
      clientRegisterScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    secondaryStage.setScene(clientRegisterScene);
    secondaryStage.show();
  }

  public void openCustomerLogin()
  {
    customerLoginScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/CustomerLogin.fxml");

      customerLoginScene = new Scene(root);
      primaryStage.setTitle("Car rental app");
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    primaryStage.setScene(customerLoginScene);
    primaryStage.show();
  }

  public void openCustomerMainMenu()
  {
    customerMainMenuScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/CustomerMainMenu.fxml");

      customerMainMenuScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    primaryStage.setScene(customerMainMenuScene);
    primaryStage.show();
  }

  public void openSelectDate()
  {
    selectDateScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/SelectDateView.fxml");

      selectDateScene = new Scene(root);
      secondaryStage.setTitle("Opret reservation");
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    secondaryStage.setScene(selectDateScene);
    secondaryStage.show();
  }

  public void openCarlistForReservation()
  {
    carlistForReservationScene = null;
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/CarlistForReservation.fxml");

      carlistForReservationScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    secondaryStage.setScene(carlistForReservationScene);
    secondaryStage.show();
  }

  private Parent loadFXML(String path) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = loader.load();

    ViewController ctrl = loader.getController();
    ctrl.init(this, viewModelFactory);
    primaryStage.setOnCloseRequest(event -> ctrl.close());
    return root;
  }

  public void closeSecondaryStage()
  {
    secondaryStage.close();
  }

  public void openAddLocation()
  {
    try
    {
      Parent root = loadFXML("../view/fxmlfiles/addLocation.fxml");

      secondaryStage.setTitle("Tilføj lokation");
      addLocationScene = new Scene(root);
    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    secondaryStage.setScene(addLocationScene);
    secondaryStage.show();
  }

  public void openContactPage()
  {
    Stage newStage = new Stage();
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../view/fxmlfiles/ContactPage.fxml"));
      Parent root = loader.load();

      newStage.setTitle("Kontakt side");
      contactPageScene = new Scene(root);

    }
    catch (IOException e)
    {
      System.out.println("Error loading fxml file: " + e.getMessage());
      e.printStackTrace();
    }
    newStage.setScene(contactPageScene);
    newStage.show();
  }
}
