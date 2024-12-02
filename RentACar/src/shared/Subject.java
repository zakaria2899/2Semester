package shared;

import java.beans.PropertyChangeListener;

public interface Subject
{
  void AddObserver(String name, PropertyChangeListener listener);
  void RemoveObserver(String name, PropertyChangeListener listener);
  void AddObserver(PropertyChangeListener listener);
  void RemoveObserver(PropertyChangeListener listener);
}
