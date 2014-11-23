package com.example.jsfdemo.web;

import com.example.jsfdemo.domain.Dzien;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

/**
 * Created by kamil on 23.11.14.
 */
@FacesValidator("uniqueValidator")
public class UniqueValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        DzienBean bean = (DzienBean)uiComponent.getValueExpression("dzienBean").getValue(facesContext.getELContext());
        if (bean.getDzienManagerBean().getDzienManager().getAllDni().indexOf(new Dzien((Date)o, null, null)) != -1
                && bean.getEditId() == null) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Ten dzień już istnieje!");
            message.setSummary("Ten dzień już istnieje!");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
