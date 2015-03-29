/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aims_server.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import net.jan.aims.aims_server.enums.EnumRank;

/**
 *
 * @author Jan
 */
@Entity
public class Rank extends MediaFile implements Serializable {
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumRank enumRank;

    public EnumRank getEnumRank() {
        return enumRank;
    }

    public void setEnumRank(EnumRank enumRank) {
        this.enumRank = enumRank;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass()).append("\n")
                .append("ID: \t")
                .append(getId()).append("\n")
                .append("Name: \t")
                .append(getName()).append("\n")
                .append("Enum: \t")
                .append(getEnumRank().toString()).append("\n")
                .append("Data: \t")
                .append(getFileData().toString()).append("\n");
        return sb.toString();
    }
    
    
    
}
