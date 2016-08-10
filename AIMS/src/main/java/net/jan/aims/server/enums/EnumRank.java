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
public enum EnumRank {

    Admiral, Cadet, Captain, ChiefWarrantOfficer, Commander,
    Commodore, Ensign, FleetAdmiral, Lieutenant, LieutenantCommander,
    LieutenantJuniorgrade, RearAdmiral, ViceAdmiral,
    WarrantOfficerSecondClass, WarrantOfficerThirdClass;

    // @Todo: Exception if the incoming String is no file
    public static EnumRank getEnumByFileName(String fileName) {
        fileName = fileName.split("\\.")[0];
        return EnumRank.valueOf(fileName);
    }

    public String getFormattedString() {
        return this.name().replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }
    
    

}
