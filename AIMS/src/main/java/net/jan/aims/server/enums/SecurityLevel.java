/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.enums;

/**
 *
 * @author Jan
 */
public enum SecurityLevel {

    SL1, SL2, SL3, SL4, SL5, SL6, SL7, SL8, SL9, SL10, SL11, SL12, SL13;

    public boolean isHigherThan(SecurityLevel toBeTested) {
        boolean isHigher = false;
        if (Integer.valueOf(this.name().split("L")[1]) > Integer.valueOf(toBeTested.name().split("L")[1])) {
            isHigher = true;
        }
        return isHigher;
    }
    
    
}
