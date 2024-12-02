package view;

import core.ViewHandler;
import core.ViewModelFactory;

public interface ViewController
{
  void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
  void close();
}
