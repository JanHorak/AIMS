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
public enum EnumPost {

    SSTFCarpeDiem,
    SSTFDeProfundis,
    SSTFExemplumStatuere,
    SSTFLegeArtis,
    SSTFExVoto,
    SSTFBeneMeriti,
    StarfleetAcademy,
    StarfleetCommand,
    StarfleetSecurity,
    StarfleetScience,
    StarfleetJudgeAdvocatGeneral,
    StarfleetTactical,
    StarfleetMedical,
    RSKB,
    Spielmeister;
    
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
