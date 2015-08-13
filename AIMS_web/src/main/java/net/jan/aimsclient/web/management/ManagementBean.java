/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.EnumRank;
import net.jan.aims.aimsserver.persistence.AIMS_UserManagement;
import net.jan.aimsclient.web.notification.NotificationHandler;

/**
 *
 * @author Jan
 */
@ViewScoped
@ManagedBean(name = "management")
public class ManagementBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private AIMS_UserManagement dataAccess;

    private List<AIMSMember> allMembers;
    private AIMSMember selectedMember;

    private Map<String, EnumRank> ranks;
    private String selectedRank;

    private Map<String, EnumPost> postings;
    private String selectedPosting;

    private List<AIMSMember> applicantMembers = new ArrayList<>();
    private List<AIMSMember> nonApplicantMembers = new ArrayList<>();

    private boolean notifyUserAboutChange;

    @PostConstruct
    public void init() {
        updateAndResetTable();
    }

    private void updateAndResetTable() {
        applicantMembers = new ArrayList<>();
        nonApplicantMembers = new ArrayList<>();
        selectedMember = new AIMSMember();
        ranks = new HashMap<>();
        postings = new HashMap<>();
        this.allMembers = (List<AIMSMember>) dataAccess.getAllMembers();
        this.allMembers.stream().forEach((m) -> {
            if (m.isApplicant()) {
                applicantMembers.add(m);
            } else {
                nonApplicantMembers.add(m);
            }
        });
        for (EnumRank r : EnumRank.values()) {
            ranks.put(r.getFormattedString(), r);
        }

        for (EnumPost p : EnumPost.values()) {
            postings.put(p.getFormattedString(), p);
        }
        this.notifyUserAboutChange = true;
    }

    public String rejectUser() {
        dataAccess.rejectUser(selectedMember);
        dataAccess.deleteUserByMail(selectedMember);
        NotificationHandler.fireNotification("User rejected.", "User "
                + selectedMember.getForename() + " "
                + selectedMember.getLastname() + " is rejected.");
        updateAndResetTable();
        return null;
    }

    public String updateSelectedUser() {
        dataAccess.updateUser(selectedMember);
        NotificationHandler.fireNotification("User updated.", "User "
                + selectedMember.getForename() + " "
                + selectedMember.getLastname() + " is updated. "
                + this.notifyUserAboutChange);
        updateAndResetTable();
        return null;
    }

    public String confirmUser() {
        selectedMember.setApplicant(false);
        selectedMember.setRank(EnumRank.valueOf(selectedRank));
        selectedMember.setPost(EnumPost.valueOf(selectedPosting));
        dataAccess.updateUser(selectedMember);
        NotificationHandler.fireNotification("User confirmed.", "User "
                + selectedMember.getForename() + " "
                + selectedMember.getLastname() + " is joined to the current members.");
        updateAndResetTable();
        return null;
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

    public Map<String, EnumRank> getRanks() {
        return ranks;
    }

    public void setRanks(Map<String, EnumRank> ranks) {
        this.ranks = ranks;
    }

    public String getSelectedRank() {
        return selectedRank;
    }

    public void setSelectedRank(String selectedRank) {
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

    public Map<String, EnumPost> getPostings() {
        return postings;
    }

    public void setPostings(Map<String, EnumPost> postings) {
        this.postings = postings;
    }

    public String getSelectedPosting() {
        return selectedPosting;
    }

    public void setSelectedPosting(String selectedPosting) {
        this.selectedPosting = selectedPosting;
    }

    public boolean isNotifyUserAboutChange() {
        return notifyUserAboutChange;
    }

    public void setNotifyUserAboutChange(boolean notifyUserAboutChange) {
        this.notifyUserAboutChange = notifyUserAboutChange;
    }

}
