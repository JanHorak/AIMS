/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.EnumRank;
import net.jan.aims.aimsserver.enums.SecurityLevel;

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
    private EnumPost post;

    @Enumerated(EnumType.STRING)
    private EnumRank rank;

    @Enumerated(EnumType.STRING)
    private SecurityLevel securityLevel;

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, targetEntity = LogBook.class, mappedBy = "owner", fetch = FetchType.EAGER)
    private List<LogBook> logBookList;

    @Embedded
    private Abilities abilities;

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public EnumRank getRank() {
        return rank;
    }

    public void setRank(EnumRank rank) {
        this.rank = rank;
    }

    public EnumPost getPost() {
        return post;
    }

    public void setPost(EnumPost post) {
        this.post = post;
    }

    public List<LogBook> getLogBookList() {
        return logBookList;
    }

    public void setLogBookList(List<LogBook> logBookList) {
        this.logBookList = logBookList;
    }

    
    
    public List<String> getAllOwnLogBookTitle() {
        List<String> title = new ArrayList<>();
        for (LogBook l : logBookList){
            title.add(l.getTitle());
        }
        return title;
    }
}
