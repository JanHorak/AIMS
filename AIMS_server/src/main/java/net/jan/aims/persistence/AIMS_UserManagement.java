/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.persistence;

import java.util.List;
import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims.aims_server.entities.BasicMember;
import net.jan.aims.aims_server.entities.Rank;

/**
 *
 * @author Jan
 */
public interface AIMS_UserManagement {
    
    public void updateRankOfUser(BasicMember member, Rank newRank);
    public void updateNameOfUser(BasicMember member, String newName);
    public void updateEmailOfUser(BasicMember member, String newEmail);
    public void confirmUser(BasicMember member);
    public void saveApplicant(BasicMember member);
    public BasicMember findUserByMail(String eMail);
    public List<AIMSMember> getAllMembers();
    public List<AIMSMember> getAllApplicants();
    
}
