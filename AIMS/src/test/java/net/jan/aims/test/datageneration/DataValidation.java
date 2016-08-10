/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.test.datageneration;

import java.io.File;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class DataValidation {

    private static final int AMOUNT_RANKS = 15;
    private static final int AMOUNT_COMMON = 3;
    private String[] pathsOfRanks;
    private String[] pathsOfCommonImages;

    @Test
    public void validateImages() {
        pathsOfRanks = new File("src/main/resources/images/ranks").list();
        assertThat("Other amount of Files expected", pathsOfRanks.length, is(AMOUNT_RANKS));

        pathsOfCommonImages = new File("src/main/resources/images/common").list();
        assertThat("Other amount of Files expected", pathsOfCommonImages.length, is(AMOUNT_COMMON));
    }
}
