/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.utilities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.jan.aims.aimsserver.entities.AIMSMember;
import javax.inject.Named;
import net.jan.aims.aimsserver.persistence.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jan
 */
@Named(value = "login")
@RequestScoped
public class LoginBean implements Serializable {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);
    
    private static final long serialVersionUID = 1L;

    @EJB
    private UserManager userManager;

    @Inject
    private NavigationBean navigationBean;

    @PostConstruct
    public void init() {
        member = new AIMSMember();
    }

    private AIMSMember member;

    public AIMSMember getMember() {
        return member;
    }

    public void setMember(AIMSMember member) {
        this.member = member;
    }
//
//    public String login() throws ValidatorException {
//        if (userManager.isUserExisting(member)) {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
//            try {
//                request.login(member.getEmail(), member.getPassword());
//                LOGGER.info("User logged in with mail: {}", member.getEmail());
//                return navigationBean.accessAllowed();
//            } catch (ServletException e) {
//                LOGGER.error("Login failed: {}", e.toString());
//            }
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Mail or password are incorrect"));
//        }
//        return "loginFailed";
//    }
//
//    public String logout() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
//        try {
//            request.logout();
//        } catch (ServletException ex) {
//            LOGGER.error("Logout failed: {}", ex.toString());
//        }
//        return navigationBean.toLogin();
//    }

}
