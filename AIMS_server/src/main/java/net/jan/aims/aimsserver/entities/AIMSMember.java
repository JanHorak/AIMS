/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.EnumRank;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AIMSMEMBER_findByMail", query = "SELECT m FROM AIMSMember m WHERE m.email = :mail"),
    @NamedQuery(name = "AIMSMEMBER_findALL", query = "SELECT m FROM AIMSMember m"),
    @NamedQuery(name = "AIMSMEMBER_findById", query = "SELECT m FROM AIMSMember m WHERE m.id = :id"),
    @NamedQuery(name = "AIMSMEMBER_deleteByMail", query = "DELETE FROM AIMSMember m WHERE m.email = :mail"),
    @NamedQuery(name = "AIMSMEMBER_findALLApplicants", query = "SELECT m FROM AIMSMember m WHERE m.applicant = TRUE")
})
public class AIMSMember extends BasicMember implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private EnumRank rank;

    public EnumRank getRank() {
        return rank;
    }

    public void setRank(EnumRank rank) {
        this.rank = rank;
    }

    @Enumerated(EnumType.STRING)
    private EnumPost post;

    public EnumPost getPost() {
        return post;
    }

    public void setPost(EnumPost post) {
        this.post = post;
    }

}
