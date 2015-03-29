/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.test.datageneration;

import java.io.File;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class DataValidation {

    public DataValidation() {
    }

    private final int AMOUNT_RANKS = 15;
    private final int AMOUNT_COMMON = 1;
    private String[] pathsOfRanks;
    private String[] pathsOfCommonImages;

    @Test
    public void validateImages() {
        pathsOfRanks = new File("src/main/resources/images/ranks").list();
        assertThat(pathsOfRanks.length, is(AMOUNT_RANKS));
        System.out.println("--- Rankfiles validated");

        pathsOfCommonImages = new File("src/main/resources/images/common").list();
        assertThat(pathsOfCommonImages.length, is(AMOUNT_COMMON));
        System.out.println("--- CommonFiles validated");
    }
}
