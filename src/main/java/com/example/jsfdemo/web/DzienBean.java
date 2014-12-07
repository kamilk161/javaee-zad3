package com.example.jsfdemo.web;

import com.example.jsfdemo.domain.Dzien;
import com.example.jsfdemo.domain.Pogoda;
import com.example.jsfdemo.service.DzienManager;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextFactory;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by kamil on 23.11.14.
 */
@ManagedBean
@ViewScoped
public class DzienBean implements Serializable {

    public DzienManagerBean getDzienManagerBean() {
        return dzienManagerBean;
    }

    public void setDzienManagerBean(DzienManagerBean dzienManagerBean) {
        this.dzienManagerBean = dzienManagerBean;
    }

    @ManagedProperty(value="#{dzienManagerBean}")
    private DzienManagerBean dzienManagerBean;

    private Dzien dzien;

    private Integer editId = null;

    public Integer getEditId() {
        return editId;
    }

    public void setEditId(Integer editId) {
        this.editId = editId;
    }

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if(externalContext.getRequestParameterMap().get("id") != null)
            editId = new Integer(externalContext.getRequestParameterMap().get("id"));
        if(editId == null)
            dzien = new Dzien(null, Pogoda.DESZCZOWA, "");
        else {
            dzien = getDzienManagerBean().getDzienManager().getAllDni().get(editId);
        }
    }

    public Dzien getDzien() {
        return dzien;
    }

    public String removeDzien(Dzien dzien) {
        dzienManagerBean.getDzienManager().removeDzien(dzien);
        return "listDzien";
    }

    public void setDzien(Dzien dzien) {
        this.dzien = dzien;
    }

    public String addDzien() {
        if(editId != null) {
            dzienManagerBean.getDzienManager().getAllDni().remove(dzienManagerBean.getDzienManager().getAllDni().get(editId));
            editId = null;
        }
        dzienManagerBean.getDzienManager().dodajDzien(dzien);
        dzien = new Dzien(new Date(), Pogoda.DESZCZOWA, "");
        return "success";
    }

    public void listener(AjaxBehaviorEvent event) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dzien.getData());
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            URL url = new URL(String.format("http://api4u.hostingasp.pl/api/imieniny/short/%d/%d", day, month));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            JSONObject obj = new JSONObject(sb.toString());
            String imiona = (String)obj.get("Imiona");
            dzien.setImieniny(imiona.replaceAll(" i ", ","));
            conn.disconnect();
        }
        catch (MalformedURLException e) {}
        catch (IOException e) {} catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
