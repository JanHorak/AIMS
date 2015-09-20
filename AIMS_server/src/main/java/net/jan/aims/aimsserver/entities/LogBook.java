/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.SecurityLevel;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "LOGBOOK.findByID", query = "SELECT l FROM LogBook l WHERE l.id = :id"),
    @NamedQuery(name = "LOGBOOK.findAllByUserID", query = "SELECT l FROM LogBook l WHERE l.owner.id = :id")
})
public class LogBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private SecurityLevel securityLevel;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = AIMSMember.class)
    private AIMSMember owner;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER, targetClass = EnumPost.class)
    private Set<EnumPost> tagList;

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, targetEntity = LogBookEntry.class, mappedBy = "owner", fetch = FetchType.EAGER)
    private List<LogBookEntry> entries;

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public Set<EnumPost> getTagList() {
        return tagList;
    }

    public void setTagList(Set<EnumPost> tagList) {
        this.tagList = tagList;
    }

    public List<LogBookEntry> getEntries() {
        List<LogBookEntry> list = new ArrayList<>(entries);
        Collections.sort(list);
        return list;
    }

    public void setEntries(List<LogBookEntry> entries) {
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AIMSMember getOwner() {
        return owner;
    }

    public void setOwner(AIMSMember owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LogBook{" + "id=" + id + ", securityLevel=" + securityLevel + ", title=" + title + ", owner=" + owner + ", tagList=" + tagList + ", entries=" + entries + '}';
    }
    
    

}
