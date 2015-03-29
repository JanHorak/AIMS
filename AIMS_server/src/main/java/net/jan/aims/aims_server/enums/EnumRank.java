/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aims_server.enums;

/**
 *
 * @author Jan
 */
public enum EnumRank {
    Admiral, Cadet, Captain, ChiefWarrantOfficer, Commander,
    Commodore, Ensign, FleetAdmiral, Lieutenant, LieutenantCommander, 
    LieutenantJuniorgrade, RearAdmiral, ViceAdmiral,
    WarrantOfficerSecondClass, WarrantOfficerThirdClass;
    
    
    public static EnumRank getEnumByFileName(String fileName){
        fileName = fileName.split("\\.")[0];
        return EnumRank.valueOf(fileName);
    }

}
