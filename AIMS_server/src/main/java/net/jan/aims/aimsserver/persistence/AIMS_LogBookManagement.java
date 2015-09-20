/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.persistence;

import java.util.List;
import net.jan.aims.aimsserver.entities.LogBook;
import net.jan.aims.aimsserver.entities.LogBookEntry;

/**
 *
 * @author Jan
 */
public interface AIMS_LogBookManagement {

    public void saveNewEntry(LogBook owner, LogBookEntry entry);

    public List<LogBook> getLogBooksByUserId(Long id);

    public LogBook getLogBookById(Long id);

    public List<LogBookEntry> getEntriesByOwnerId(Long id);
    
    public void saveNewLogBook(LogBook logBook);

}
