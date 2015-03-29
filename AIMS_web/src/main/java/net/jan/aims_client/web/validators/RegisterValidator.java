/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.validators;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Jan
 */
@Named(value = "registerValidator")
@RequestScoped
public class RegisterValidator {

    public void nameValidator(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String msg = "";
        String name = value.toString();
        if (name.length() < 3) {
            msg = "Length must be higher than 3";
            FacesMessage message = new FacesMessage(msg);
            throw new ValidatorException(message);
        }
    }
    
    public void passwordValidator(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String msg = "";
        String name = value.toString();
        if (name.length() < 8) {
            msg = "Length must be higher than 8";
            FacesMessage message = new FacesMessage(msg);
            throw new ValidatorException(message);
        }
    }

    public void mailValidator(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String mail = value.toString();
        String msg = "";
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (!mail.matches(pattern)) {
            msg = "Malformed Mailaddress";
            FacesMessage message = new FacesMessage(msg);
            throw new ValidatorException(message);
        }

    }

}
