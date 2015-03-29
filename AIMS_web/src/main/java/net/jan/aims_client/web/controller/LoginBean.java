/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.controller;

import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims_client.web.beans.NavigationBean;
import javax.inject.Named;
import net.jan.aims.aims_server.services.CurrentUserProducer;

/**
 *
 * @author Jan
 */
@Named(value = "login")
@RequestScoped
public class LoginBean implements Serializable {

    @EJB
    private CurrentUserProducer userProducer;
    
    @Inject
    private NavigationBean navigationBean;

    @PostConstruct
    public void init(){
        member = new AIMSMember();
    }

    private AIMSMember member;
    
    public AIMSMember getMember() {
        return member;
    }

    public void setMember(AIMSMember member) {
        this.member = member;
    }

    public String login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.login(member.getEmail(), member.getPassword());
            Principal principal = request.getUserPrincipal();

            if (request.isUserInRole("Member")) {
                String msg = "User: " + principal.getName() + ", Role: Member";
                System.out.println(msg);
            }
            return navigationBean.accessAllowed();
        } catch (ServletException e) {
            System.err.println("Failed" + e.getLocalizedMessage());
        }
        return "loginFailed";
    }

    public String logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {

        }
        return null;
    }
    
}
