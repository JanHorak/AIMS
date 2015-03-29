/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_client.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims.aims_server.enums.EnumRank;
import net.jan.aims.persistence.AIMS_UserManagement;

/**
 *
 * @author Jan
 */
@SessionScoped
@Named(value = "management")
public class ManagementBean implements Serializable {

    @EJB
    private AIMS_UserManagement dataAccess;

    private List<AIMSMember> allMembers;
    private AIMSMember selectedMember;
    private EnumRank selectedRank;

    private List<AIMSMember> applicantMembers = new ArrayList<>();
    private List<AIMSMember> nonApplicantMembers = new ArrayList<>();

    @PostConstruct
    public void init() {
        selectedMember = new AIMSMember();
        this.allMembers = (List<AIMSMember>) dataAccess.getAllMembers();
        this.allMembers.stream().forEach((m) -> {
            if (m.isApplicant()) {
                applicantMembers.add(m);
            } else {
                nonApplicantMembers.add(m);
            }
        });
    }

    public List<AIMSMember> getApplicantMembers() {
        return applicantMembers;
    }

    public void setApplicantMembers(List<AIMSMember> applicantMembers) {
        this.applicantMembers = applicantMembers;
    }

    public List<AIMSMember> getNonApplicantMembers() {
        return nonApplicantMembers;
    }

    public void setNonApplicantMembers(List<AIMSMember> nonApplicantMembers) {
        this.nonApplicantMembers = nonApplicantMembers;
    }

    public String getCountOfNonApplicants() {
        return String.valueOf(nonApplicantMembers.size());
    }

    public String getCountOfApplicants() {
        return String.valueOf(applicantMembers.size());
    }

    public AIMSMember getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(AIMSMember selectedMember) {
        this.selectedMember = selectedMember;
    }

    public EnumRank[] getRanks() {
        return EnumRank.values();
    }

    public EnumRank getSelectedRank() {
        return selectedRank;
    }

    public void setSelectedRank(EnumRank selectedRank) {
        this.selectedRank = selectedRank;
    }

    public List<AIMSMember> getAllMembers() {
        return allMembers;
    }

    public AIMSMember getMemberById(String id) {
        for (AIMSMember m : allMembers) {
            if (String.valueOf(m.getId()).equals(id)) {
                return m;
            }
        }
        return null;
    }

}
