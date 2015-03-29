/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jan
 */
@ManagedBean(name = "navBean")
@RequestScoped
public class NavigationBean implements Serializable {

    public String accessDenied() {
        return "denied";
    }

    public String accessAllowed() {
        return "/content/aims/index.xhtml";
    }

    public String toMemberManagement() {
        return "management";
    }
    
    public String toProfile(){
        return "profile";
    }

}