/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aims_server.entities;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import net.jan.aims.aims_server.enums.EnumRank;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AIMSMEMBER_findByMail", query = "SELECT m FROM AIMSMember m WHERE m.email = :mail"),
    @NamedQuery(name = "AIMSMEMBER_findALL", query = "SELECT m FROM AIMSMember m"),
    @NamedQuery(name = "AIMSMEMBER_findALLApplicants", query = "SELECT m FROM AIMSMember m WHERE m.applicant = TRUE")
})
public class AIMSMember extends BasicMember implements Serializable {

    @Enumerated(EnumType.STRING)
    private EnumRank rank;

    public EnumRank getRank() {
        return rank;
    }

    public void setRank(EnumRank rank) {
        this.rank = rank;
    }

    

    
}
