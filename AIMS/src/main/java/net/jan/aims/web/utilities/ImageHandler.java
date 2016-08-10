/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.utilities;

import net.jan.aimsclient.web.management.ManagementBean;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import javax.inject.Inject;
import net.jan.aims.aimsserver.entities.AIMSMember;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jan
 */
@ApplicationScoped
@ManagedBean(name = "imageHelper")
public class ImageHandler implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{member_id}")
    private String memberid;

    @Inject
    ManagementBean managementBean;

    public StreamedContent getImageById(AIMSMember m) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String param = fc.getExternalContext().getRequestParameterMap().get("member_id");
            if (!param.isEmpty()){
                return new DefaultStreamedContent(new ByteArrayInputStream(managementBean.getMemberById(param).get().getAvatar()), "image/png");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(m.getAvatar()), "image/png");
            }
            
        }

    }
    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

}
