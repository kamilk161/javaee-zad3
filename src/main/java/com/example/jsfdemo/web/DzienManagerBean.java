package com.example.jsfdemo.web;

import com.example.jsfdemo.domain.Dzien;
import com.example.jsfdemo.service.DzienManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by kamil on 23.11.14.
 */
@ManagedBean
@SessionScoped
public class DzienManagerBean implements Serializable {
    @Inject
    private DzienManager dzienManager;

    public DzienManager getDzienManager() {
        return dzienManager;
    }

    public void setDzienManager(DzienManager dzienManager) {
        this.dzienManager = dzienManager;
    }

    public ArrayDataModel<Dzien> getAllDni() {
        Dzien[] dni = new Dzien[getDzienManager().getAllDni().size()];
        return new ArrayDataModel<Dzien>(getDzienManager().getAllDni().toArray(dni));
    }
}
