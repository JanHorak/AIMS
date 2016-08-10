/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.validation.constraints.NotNull;
import net.jan.aims.aimsserver.enums.EnumGender;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Jan
 */
@MappedSuperclass
public abstract class BasicMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private long id;

    @NotNull
    private String email;

    @NotNull
    private String forename;

    @NotNull
    private String lastname;

    @NotNull
    private String password;

    @NotNull
    private boolean adminrights;

    @NotNull
    private boolean applicant;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumGender gender;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "member_group", joinColumns = @JoinColumn(name = "m_id"))
    @Column(name = "m_group")
    private Set<String> systemGroup = new HashSet<>();

    private byte[] avatar;

    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    public Set<String> getSystemGroup() {
        return systemGroup;
    }

    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    public void setSystemGroup(Set<String> systemGroup) {
        this.systemGroup = systemGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasAdminrights() {
        return adminrights;
    }

    public void setAdminrights(boolean adminrights) {
        this.adminrights = adminrights;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public boolean isInRole(String group) {
        return this.systemGroup.contains(group);
    }

    public boolean isApplicant() {
        return applicant;
    }

    public boolean isAdminrights() {
        return adminrights;
    }

    public void setApplicant(boolean applicant) {
        this.applicant = applicant;
    }

    public EnumGender getGender() {
        return gender;
    }

    public void setGender(EnumGender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "BasicMember{" + "id=" + id + ", email=" + email + ", forename=" + forename + ", lastname=" + lastname + ", password=" + password + ", adminrights=" + adminrights + ", applicant=" + applicant + ", gender=" + gender + ", systemGroup=" + systemGroup + '}';
    }

}
