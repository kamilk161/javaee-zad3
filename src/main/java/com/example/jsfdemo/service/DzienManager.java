package com.example.jsfdemo.service;

import com.example.jsfdemo.domain.Dzien;

import javax.faces.bean.ApplicationScoped;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 23.11.14.
 */
@ApplicationScoped
public class DzienManager implements Serializable {

    public DzienManager() {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("baza.out"));
            dni = (ArrayList<Dzien>) is.readObject();
        } catch (IOException e) {
            dni = new ArrayList<Dzien>();
        } catch (ClassNotFoundException e) {
            dni = new ArrayList<Dzien>();
        }
    }
    private List<Dzien> dni;

    public void dodajDzien(Dzien dzien) {
        dni.add(dzien);
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("baza.out"));
            os.writeObject(dni);
        } catch (IOException e) {}
    }

    public List<Dzien> getAllDni() {
        return dni;
    }

    public void removeDzien(Dzien dzien) {
        dni.remove(dzien);
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("baza.out"));
            os.writeObject(dni);
        } catch (IOException e) {}
    }
}
