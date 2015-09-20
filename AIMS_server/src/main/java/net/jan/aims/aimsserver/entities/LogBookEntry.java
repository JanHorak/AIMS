/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "LOGBOOKENTRY.findByOwnerID", query = "SELECT e FROM LogBookEntry e WHERE e.owner.id = :id")
})
public class LogBookEntry implements Serializable, Comparable<LogBookEntry> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = LogBook.class)
    private LogBook owner;

    private String topic;

    private String subTopic;

    private String content;

    private LocalDateTime timeOfEntry;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(LocalDateTime timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogBook getOwner() {
        return owner;
    }

    public void setOwner(LogBook owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(LogBookEntry o) {
        if (this.timeOfEntry.isBefore(o.timeOfEntry)) {
            return -1;
        }
        if (this.timeOfEntry.isAfter(o.timeOfEntry)) {
            return 1;
        }
        return 0;
    }

}
