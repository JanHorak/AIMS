/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.persistence;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims.aims_server.entities.BasicMember;
import net.jan.aims.aims_server.entities.Rank;

/**
 *
 * @author Jan
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserManager implements AIMS_UserManagement {

    @PersistenceContext(unitName = "aimsDS")
    private EntityManager em;

    @Override
    public void updateRankOfUser(BasicMember member, Rank newRank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateNameOfUser(BasicMember member, String newName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEmailOfUser(BasicMember member, String newEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmUser(BasicMember member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BasicMember findUserByMail(String eMail) {
        return (BasicMember) em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", eMail).getSingleResult();
    }

    @Override
    public List<AIMSMember> getAllMembers() {
        return (List<AIMSMember>) em.createNamedQuery("AIMSMEMBER_findALL").getResultList();
    }

    @Override
    public List<AIMSMember> getAllApplicants() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveApplicant(BasicMember member) {
        em.persist(member);
    }

}
