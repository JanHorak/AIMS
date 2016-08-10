/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.validators;


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
public class RegisterValidator extends GeneralValidator {

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

}
