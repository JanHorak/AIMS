/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.jan.aims.aimsserver.enums.EnumGender;

/**
 *
 * @author Jan
 */
@FacesConverter("net.jan.aims_client.converter.GenderConverter")
public class GenderConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return (EnumGender) EnumGender.valueOf((String) value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return EnumGender.valueOf((String) value).toString();
    }

}
