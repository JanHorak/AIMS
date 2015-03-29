/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.jan.aims.aims_server.annotations.CurrentUser;
import net.jan.aims.aims_server.entities.AIMSMember;

/**
 *
 * @author Jan
 */
@SessionScoped
@ManagedBean(name = "menubean")
public class MenuBean implements Serializable {

    @CurrentUser
    @Inject
    private AIMSMember member;

    public AIMSMember getMember() {
        return member;
    }

    public void setMember(AIMSMember member) {
        this.member = member;
    }

}
