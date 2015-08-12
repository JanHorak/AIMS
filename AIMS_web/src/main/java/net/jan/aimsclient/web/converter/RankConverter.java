/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.converter;

/**
 *
 * @author Jan
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.jan.aims.aimsserver.enums.EnumRank;

@FacesConverter("net.jan.aims_client.converter.RankConverter")
public class RankConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return EnumRank.valueOf(value.replaceAll(" ", ""));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((EnumRank) value).getFormattedString();
    }

}
