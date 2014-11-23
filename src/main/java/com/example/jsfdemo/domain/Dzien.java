package com.example.jsfdemo.domain;


import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Dzien implements Serializable{

    @NotNull(message = "Data nie może być nullem")
    private Date data = new Date();

    private Pogoda pogoda;

    @Pattern(regexp="(\\w+[,])*\\w+", message = "Imiona powinny być oddzielone przecinkami")
    private String imieniny = "";

    public Dzien(Date data, Pogoda pogoda, String imieniny) {
        super();
        this.data = data;
        this.pogoda = pogoda;
        this.imieniny = imieniny;
    }

    public Date getData() {
        return data;
    }


    public void setData(Date data) {
        this.data = data;
    }

    public Pogoda getPogoda() {
        return pogoda;
    }

    public void setPogoda(Pogoda pogoda) {
        this.pogoda = pogoda;
    }

    public String getImieniny() {
        return imieniny;
    }

    public void setImieniny(String imieniny) {
        this.imieniny = imieniny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dzien dzien = (Dzien) o;

        return !(data != null ? !data.equals(dzien.data) : dzien.data != null);

    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
