package com.example.jsfdemo.web;

import com.example.jsfdemo.domain.Pogoda;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by kamil on 07.12.14.
 */
@FacesConverter("com.example.jsfdemo.web.PogodaConverter")
public class PogodaConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return Pogoda.valueOf(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
