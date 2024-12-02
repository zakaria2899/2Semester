package core;

import model.CarRentModel;
import model.CarRentModelImpl;

public class ModelFactory
{
  private CarRentModel carRentModel;
  private static ModelFactory instance;

  private ModelFactory()
  {
    carRentModel = new CarRentModelImpl(new ClientFactory().getSocketClient());
  }

  public static ModelFactory getInstance()
  {
    if (instance == null)
    {
      instance = new ModelFactory();
    }
    return instance;
  }

  public CarRentModel getCarRentModel()
  {
    return carRentModel;
  }
}
