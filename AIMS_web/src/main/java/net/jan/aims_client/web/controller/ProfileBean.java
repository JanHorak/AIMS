/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;
import net.jan.aims.aims_server.annotations.CurrentUser;
import net.jan.aims.aims_server.entities.AIMSMember;

/**
 *
 * @author Jan
 */
@SessionScoped
@Named(value = "profile")
public class ProfileBean implements Serializable {

    @Inject
    @CurrentUser
    private AIMSMember member;

    public AIMSMember getMember() {
        return member;
    }

    public void setMember(AIMSMember member) {
        this.member = member;
    }

}
