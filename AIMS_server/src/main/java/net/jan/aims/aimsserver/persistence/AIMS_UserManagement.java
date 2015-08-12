/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.BasicMember;

/**
 *
 * @author Jan
 */
public interface AIMS_UserManagement {
    
    public void updateUser(AIMSMember newOne);
    public void rejectUser(AIMSMember applicant);
    public void saveApplicant(BasicMember member);
    public BasicMember findUserByMail(String eMail);
    public List<AIMSMember> getAllMembers();
    public List<AIMSMember> getAllApplicants();
    public boolean isUserExisting(BasicMember member);
    public void setEntityManager(EntityManager em);
    public void deleteUserByMail(AIMSMember user);
    
}
