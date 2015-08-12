/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import net.jan.aims.aimsserver.enums.EnumRank;

/**
 *
 * @author Jan
 */
@Entity
public class Rank extends MediaFile implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
    public String toString() {
        return "Rank{" + "enumRank=" + enumRank + " Name=" + getName() + " Enum=" + getEnumRank().toString() + " Data=" + Arrays.toString(getFileData())+'}';
    }
}
