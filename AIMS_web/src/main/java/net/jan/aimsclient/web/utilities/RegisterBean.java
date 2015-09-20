/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.utilities;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.BasicMember;
import net.jan.aims.aimsserver.enums.EnumGender;
import net.jan.aims.aimsserver.persistence.UserManager;
import net.jan.aimsclient.web.notification.NotificationHandler;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jan
 */
@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserManager usermanager;

    private BasicMember member;

    private Map<String, EnumGender> gender;
    private String selectedGender;

    @PostConstruct
    public void init() {
        member = new AIMSMember();
        gender = new HashMap<>();
        for (EnumGender g : EnumGender.values()) {
            gender.put(g.name(), g);
        }
    }

    public BasicMember getMember() {
        return member;
    }

    public void setMember(BasicMember member) {
        this.member = member;
    }

    public String register() {
        byte[] avatar = null;
        try {
            avatar = IOUtils.toByteArray(getClass().getResourceAsStream("/images/common/CK.png"));
        } catch (IOException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        member.setApplicant(true);
        member.setAdminrights(false);
        member.setGender(gender.get(selectedGender));
        member.setAvatar(avatar);
        NotificationHandler.fireNotification("Registration sent",
                "Your application is sent to the admins. You'll be notified by email.");
        usermanager.saveApplicant(member);
        return null;
    }

    public Map<String, EnumGender> getGender() {
        return gender;
    }

    public void setGender(Map<String, EnumGender> gender) {
        this.gender = gender;
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

}
