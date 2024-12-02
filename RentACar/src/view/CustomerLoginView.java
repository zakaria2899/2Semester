package view;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import viewModel.CustomerLoginViewModel;

public class CustomerLoginView implements ViewController
{
  @FXML TextField username;
  @FXML TextField password;
  @FXML Button login;
  @FXML Button register;
  @FXML Button help;

  private ViewHandler viewHandler;
  private CustomerLoginViewModel viewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getCustomerLoginViewModel();

    username.textProperty().bindBidirectional(viewModel.userNameProperty());
    password.textProperty().bindBidirectional(viewModel.passwordProperty());
    viewModel.getCustomers();
  }

  @Override public void close()
  {
    viewModel.close();
  }

  public void login()
  {
    if (viewModel.validateLogin())
    {
      viewModel.login();
      viewHandler.openCustomerMainMenu();
    }
    else
    {
      showAlert("Forkert login", "Forkert mail eller CPR-nummer (husk bindestreg).");
    }
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

  public void addCustomer()
  {
    viewHandler.openClientRegisterScene();
  }
}
