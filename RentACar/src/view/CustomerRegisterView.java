package view;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import viewModel.CustomerRegisterViewModel;

import javafx.scene.control.TextField;

public class CustomerRegisterView implements ViewController
{
  @FXML TextField firstName;
  @FXML TextField lastName;
  @FXML TextField email;
  @FXML TextField phone;
  @FXML TextField address;
  @FXML TextField city;
  @FXML TextField postalCode;
  @FXML TextField cpr6;
  @FXML TextField cpr4;
  @FXML DatePicker birthDate;

  @FXML Button registerButton;
  @FXML Button helpButton;
  @FXML Button loginButton;

  private ViewHandler viewHandler;
  private CustomerRegisterViewModel viewModel;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getCustomerRegisterViewModel();

    firstName.textProperty().bindBidirectional(viewModel.firstNameProperty());
    lastName.textProperty().bindBidirectional(viewModel.lastNameProperty());
    email.textProperty().bindBidirectional(viewModel.emailProperty());
    phone.textProperty().bindBidirectional(viewModel.phoneProperty());
    address.textProperty().bindBidirectional(viewModel.addressProperty());
    cpr6.textProperty().bindBidirectional(viewModel.cpr6Property());
    cpr4.textProperty().bindBidirectional(viewModel.cpr4Property());
    city.textProperty().bindBidirectional(viewModel.cityProperty());
    birthDate.valueProperty().bindBidirectional(viewModel.birthDateProperty());
    postalCode.textProperty().bindBidirectional(viewModel.postalCodeProperty());
  }

  public void registerCustomer()
  {
    if (!viewModel.validateFirstName())
    {
      showAlert("Fejl ved fornavn", "Indtast et gyldigt fornavn");
    }
    else if (!viewModel.validateLastName())
    {
      showAlert("Fejl ved efternavn", "Indtast et gyldigt efternavn");
    }
    else if (!viewModel.validateBirthDate())
    {
      showAlert("Fejl ved fødselsdato", "Vælg et gyldigt fødselsdato");
    }
    else if (!viewModel.validateCpr6())
    {
      showAlert("Fejl ved CPR-nummer", "Indtast et gyldigt CPR-nummer");
    }
    else if (!viewModel.validateCpr4())
    {
      showAlert("Fejl ved CPR-nummer", "Indtast et gyldigt CPR-nummer");
    }
    else if (!viewModel.validateNumber())
    {
      showAlert("Fejl ved telefon nummer", "Indtast et gyldigt telefon nummer");
    }
    else if (!viewModel.validateMail())
    {
      showAlert("Fejl ved email", "Indtast et gyldigt email");
    }
    else if (!viewModel.validateAddress())
    {
      showAlert("fejl ved adresse", "Indtast et gyldigt adresse");
    }
    else if (!viewModel.validateCity())
    {
      showAlert("Fejl ved by", "Indtast et gyldigt by");
    }
    else if (!viewModel.validatePostalCode())
    {
      showAlert("Fejl ved postnummer", "Indtast et gyldigt postnummer");
    }
    else
    {
      viewModel.registerCustomer();
      viewHandler.closeSecondaryStage();
    }

  }

  public void cancel()
  {
    viewHandler.closeSecondaryStage();
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
