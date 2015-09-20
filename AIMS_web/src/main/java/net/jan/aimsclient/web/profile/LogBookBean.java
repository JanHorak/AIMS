/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aimsclient.web.profile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import net.jan.aims.aimsserver.annotations.CurrentUser;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.LogBook;
import net.jan.aims.aimsserver.entities.LogBookEntry;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.SecurityLevel;
import net.jan.aims.aimsserver.persistence.AIMS_LogBookManagement;
import net.jan.aimsclient.web.notification.NotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jan
 */
@ViewScoped
@ManagedBean(name = "logbookBean")
public class LogBookBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBookBean.class);

    private static final long serialVersionUID = 1L;

    @Inject
    @CurrentUser
    private AIMSMember currentUser;

    @EJB
    private AIMS_LogBookManagement logBookManagement;

    private List<LogBook> logBooks;

    private String topicForNewEntry;

    private String subTopicForNewEntry;

    private String contentForNewEntry;

    private LogBook selectedLogBook;

    // New Logbookvalues
    private List<String> selectedTags;
    private List<EnumPost> tagList;
    private SecurityLevel selectedSecurityLevel;
    private List<SecurityLevel> securityLevelList;
    private String titleForNewLogbook;

    @PostConstruct
    public void init() {
        this.topicForNewEntry = "";
        this.subTopicForNewEntry = "";
        this.contentForNewEntry = "";
        this.selectedLogBook = new LogBook();
        this.selectedLogBook.setEntries(new ArrayList<>());
        this.selectedTags = new ArrayList<>();
        this.logBooks = currentUser.getLogBookList();
        this.securityLevelList = Arrays.asList(SecurityLevel.values());
        this.tagList = Arrays.asList(EnumPost.values());
        this.titleForNewLogbook = "";
    }

    public void saveNewLogBookEntry() {
        LogBookEntry entry = new LogBookEntry();
        entry.setOwner(selectedLogBook);
        entry.setSubTopic(subTopicForNewEntry);
        entry.setTopic(topicForNewEntry);
        entry.setTimeOfEntry(LocalDateTime.now());
        entry.setContent(contentForNewEntry);
        logBookManagement.saveNewEntry(selectedLogBook, entry);

        NotificationHandler.fireNotification("New entry saved", selectedLogBook.getTitle() + " is updated");
        resetInputs();
        refreshLogBooks();
    }

    public void saveNewLogBook() {
        LogBook newLogBook = new LogBook();
        newLogBook.setEntries(new ArrayList<>());
        newLogBook.setSecurityLevel(selectedSecurityLevel);
        newLogBook.setTitle(titleForNewLogbook);
        newLogBook.setOwner(currentUser);
        Set<EnumPost> p = new HashSet<>();
        selectedTags.forEach((post) -> {
            p.add(EnumPost.valueOf(post));
        });
        newLogBook.setTagList(p);

        logBookManagement.saveNewLogBook(newLogBook);
        NotificationHandler.fireNotification("Saved", newLogBook.getTitle() + " is saved");
        resetInputs();
        refreshLogBooks();

    }

    public void refreshLogBooks() {
        this.logBooks = logBookManagement.getLogBooksByUserId(currentUser.getId());
    }

    private void resetInputs() {
        this.selectedSecurityLevel = null;
        this.topicForNewEntry = "";
        this.subTopicForNewEntry = "";
        this.contentForNewEntry = "";
        this.selectedLogBook = null;
        this.selectedTags.clear();
    }

    public String getTitleForNewLogbook() {
        return titleForNewLogbook;
    }

    public void setTitleForNewLogbook(String titleForNewLogbook) {
        this.titleForNewLogbook = titleForNewLogbook;
    }

    public String getTopicForNewEntry() {
        return topicForNewEntry;
    }

    public void setTopicForNewEntry(String topicForNewEntry) {
        this.topicForNewEntry = topicForNewEntry;
    }

    public String getSubTopicForNewEntry() {
        return subTopicForNewEntry;
    }

    public void setSubTopicForNewEntry(String subTopicForNewEntry) {
        this.subTopicForNewEntry = subTopicForNewEntry;
    }

    public List<LogBook> getLogBooks() {
        return logBooks;
    }

    public void setLogBooks(List<LogBook> logBooks) {
        this.logBooks = logBooks;
    }

    public LogBook getSelectedLogBook() {
        return selectedLogBook;
    }

    public void setSelectedLogBook(LogBook selectedLogBook) {
        this.selectedLogBook = selectedLogBook;
    }

    public SecurityLevel getSelectedSecurityLevel() {
        return selectedSecurityLevel;
    }

    public void setSelectedSecurityLevel(SecurityLevel selectedSecurityLevel) {
        this.selectedSecurityLevel = selectedSecurityLevel;
    }

    public List<SecurityLevel> getSecurityLevelList() {
        return securityLevelList;
    }

    public void setSecurityLevelList(List<SecurityLevel> securityLevelList) {
        this.securityLevelList = securityLevelList;
    }

    public List<String> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(List<String> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public List<EnumPost> getTagList() {
        return tagList;
    }

    public void setTagList(List<EnumPost> tagList) {
        this.tagList = tagList;
    }

    public String getContentForNewEntry() {
        return contentForNewEntry;
    }

    public void setContentForNewEntry(String logbookText) {
        this.contentForNewEntry = logbookText;
    }
}
