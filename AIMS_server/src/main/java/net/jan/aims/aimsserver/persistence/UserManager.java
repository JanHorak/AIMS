/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.persistence;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.BasicMember;

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
    public BasicMember findUserByMail(String eMail) {
        return (BasicMember) em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", eMail).getSingleResult();
    }

    @Override
    public List<AIMSMember> getAllMembers() {
        return (List<AIMSMember>) em.createNamedQuery("AIMSMEMBER_findALL").getResultList();
    }

    @Override
    public List<AIMSMember> getAllApplicants() {
        return (List<AIMSMember>) em.createNamedQuery("AIMSMEMBER_findALLApplicants").getResultList();
    }

    @Override
    public void saveApplicant(BasicMember member) {
        em.persist(member);
    }

    @Override
    public boolean isUserExisting(BasicMember member) {
        Query q = em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", member.getEmail());
        AIMSMember tmp;
        boolean isExisting = false;
        try {
            tmp = (AIMSMember) q.getSingleResult();
            if (tmp != null) {
                isExisting = true;
            }
        } catch (NoResultException ex) {
            isExisting = false;
        }
        return isExisting;
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public void updateUser(AIMSMember newOne) {
        //AIMSMember user = (AIMSMember) em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", newOne.getEmail()).getSingleResult();
        em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", newOne.getEmail()).getSingleResult();
        em.merge(newOne);
    }

    @Override
    public void rejectUser(AIMSMember applicant) {
        Set<String> groups = applicant.getSystemGroup();
        groups.clear();
        updateUser(applicant);
    }

    @Override
    public void deleteUserByMail(AIMSMember user) {
        em.createNamedQuery("AIMSMEMBER_deleteByMail").setParameter("mail", user.getEmail()).executeUpdate();
    }

}
