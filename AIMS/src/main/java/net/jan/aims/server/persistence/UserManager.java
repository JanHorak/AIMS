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
public class UserManager {

    @PersistenceContext(unitName = "aimsDS")
    private EntityManager em;

    public BasicMember findUserByMail(String eMail) {
        return (BasicMember) em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", eMail).getSingleResult();
    }

    public List<AIMSMember> getAllMembers() {
        return (List<AIMSMember>) em.createNamedQuery("AIMSMEMBER_findALL").getResultList();
    }

    public List<AIMSMember> getAllApplicants() {
        return (List<AIMSMember>) em.createNamedQuery("AIMSMEMBER_findALLApplicants").getResultList();
    }

    public void saveApplicant(BasicMember member) {
        em.persist(member);
    }

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
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public void updateUser(AIMSMember newOne) {
        em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", newOne.getEmail()).getSingleResult();
        em.merge(newOne);
    }

    public void rejectUser(AIMSMember applicant) {
        Set<String> groups = applicant.getSystemGroup();
        groups.clear();
        updateUser(applicant);
    }

    public void deleteUserByMail(AIMSMember user) {
        em.createNamedQuery("AIMSMEMBER_deleteByMail").setParameter("mail", user.getEmail()).executeUpdate();
    }

}
