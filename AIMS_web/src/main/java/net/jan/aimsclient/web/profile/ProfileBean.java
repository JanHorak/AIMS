/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.profile;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;
import net.jan.aims.aimsserver.annotations.CurrentUser;
import net.jan.aims.aimsserver.entities.AIMSMember;

/**
 *
 * @author Jan
 */
@SessionScoped
@Named(value = "profile")
public class ProfileBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

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
