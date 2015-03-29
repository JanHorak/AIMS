/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.test.utilities;

import net.jan.aims_server.utilities.Utilities;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class UtilitiesTest {

    public UtilitiesTest() {
    }

    @Test
    public void testByteArrayExtractor() {
        String s = getClass().getClassLoader().getResource("images/ranks/Admiral.png").getFile();
        assertNotNull(s);
        assertNotNull(Utilities.getBytesOfFile(s));
        
    }
}
