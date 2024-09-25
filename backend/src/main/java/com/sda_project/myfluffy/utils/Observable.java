package com.sda_project.myfluffy.utils;

// Observable Interface
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}