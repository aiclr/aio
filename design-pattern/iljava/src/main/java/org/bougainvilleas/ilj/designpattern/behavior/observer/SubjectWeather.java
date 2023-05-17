package org.bougainvilleas.ilj.designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectWeather implements Subject {

  private String data;
  List<Observer> observers;

  public SubjectWeather() {
    observers = new ArrayList<>();
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    if (observers.contains(observer))
      observers.remove(observer);
  }

  @Override
  public void notifyObserver() {
    for (Observer observer : observers) {
      observer.update(data);
    }
  }
}
