/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan
 */
@Embeddable
public class Abilities implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull
    private int lebenspunkte;
    
    @NotNull
    private int mut;
    
    @NotNull
    private int wahrnehmung;
    
    @NotNull
    private int intelligenz;
    
    @NotNull
    private int geschicklichkeit;
    
    @NotNull
    private int wissen;
    
    @NotNull
    private int konstitution;
    
    @NotNull
    private int charisma;
    
    @NotNull
    private int koerperbeherrschung;
    
    @NotNull
    private int kreativitaet;
    
    @NotNull
    private int telepathie;
    
    @NotNull
    private int impulsivitaet;

    public int getLebenspunkte() {
        return lebenspunkte;
    }

    public void setLebenspunkte(int lebenspunkte) {
        this.lebenspunkte = lebenspunkte;
    }

    public int getMut() {
        return mut;
    }

    public void setMut(int mut) {
        this.mut = mut;
    }

    public int getWahrnehmung() {
        return wahrnehmung;
    }

    public void setWahrnehmung(int wahrnehmung) {
        this.wahrnehmung = wahrnehmung;
    }

    public int getIntelligenz() {
        return intelligenz;
    }

    public void setIntelligenz(int intelligenz) {
        this.intelligenz = intelligenz;
    }

    public int getGeschicklichkeit() {
        return geschicklichkeit;
    }

    public void setGeschicklichkeit(int geschicklichkeit) {
        this.geschicklichkeit = geschicklichkeit;
    }

    public int getWissen() {
        return wissen;
    }

    public void setWissen(int wissen) {
        this.wissen = wissen;
    }

    public int getKonstitution() {
        return konstitution;
    }

    public void setKonstitution(int konstitution) {
        this.konstitution = konstitution;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getKoerperbeherrschung() {
        return koerperbeherrschung;
    }

    public void setKoerperbeherrschung(int koerperbeherrschung) {
        this.koerperbeherrschung = koerperbeherrschung;
    }

    public int getKreativitaet() {
        return kreativitaet;
    }

    public void setKreativitaet(int kreativitaet) {
        this.kreativitaet = kreativitaet;
    }

    public int getTelepathie() {
        return telepathie;
    }

    public void setTelepathie(int telepathie) {
        this.telepathie = telepathie;
    }

    public int getImpulsivitaet() {
        return impulsivitaet;
    }

    public void setImpulsivitaet(int impulsivitaet) {
        this.impulsivitaet = impulsivitaet;
    }
    
    
    
    
    
}
