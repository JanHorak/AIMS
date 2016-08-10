/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.LogBook;
import net.jan.aims.aimsserver.entities.LogBookEntry;

/**
 *
 * @author Jan
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LogBookManager {

    @PersistenceContext(unitName = "aimsDS")
    private EntityManager em;


    public void saveNewEntry(LogBook book, LogBookEntry entry) {
        LogBook old = em.find(LogBook.class, book.getId());
        entry.setOwner(old);
        em.persist(entry);
    }

    public List<LogBook> getLogBooksByUserId(Long id) {
        return (List<LogBook>) em.createNamedQuery("LOGBOOK.findAllByUserID").setParameter("id", id).getResultList();
    }

    public LogBook getLogBookById(Long id) {
        return (LogBook) em.createNamedQuery("LOGBOOK.findByID").setParameter("id", id).getSingleResult();
    }

    public List<LogBookEntry> getEntriesByOwnerId(Long id) {
        return (List<LogBookEntry>) em.createNamedQuery("LOGBOOKENTRY.findByOwnerID").setParameter("id", id).getResultList();
    }

    public void saveNewLogBook(LogBook logBook) {
        AIMSMember member = em.find(AIMSMember.class, logBook.getOwner().getId());
        logBook.setOwner(member);
        em.persist(logBook);
    }

}
