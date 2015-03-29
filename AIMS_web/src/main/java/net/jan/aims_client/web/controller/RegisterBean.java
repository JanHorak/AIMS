/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims.aims_server.entities.BasicMember;
import net.jan.aims.persistence.AIMS_UserManagement;

/**
 *
 * @author Jan
 */
@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean implements Serializable {

    @EJB
    private AIMS_UserManagement usermanager;
    
    private BasicMember member;

    @PostConstruct
    public void init() {
        member = new AIMSMember();
    }

    public BasicMember getMember() {
        return member;
    }

    public void setMember(BasicMember member) {
        this.member = member;
    }

    public void register() {
        member.setApplicant(true);
        member.setAdminrights(false);
        usermanager.saveApplicant(member);
        System.out.println("saved");
    }

}
