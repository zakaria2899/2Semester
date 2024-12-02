package application;

import core.ModelFactory;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class StaffApp extends Application
{

  @Override public void start(Stage primaryStage) throws Exception
  {
    ModelFactory modelFactory = ModelFactory.getInstance();
    ViewModelFactory viewModelFactory = ViewModelFactory.getInstance();
    ViewHandler viewHandler = new ViewHandler();
    viewHandler.startStaff();
  }
}
