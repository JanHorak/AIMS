/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.test.enums;

import net.jan.aims.aimsserver.enums.EnumRank;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class EnumTest {
    
    @Test
    public void shouldReturnFormattedName(){
        assertThat("Unexpected Name", EnumRank.ChiefWarrantOfficer.getFormattedString(), is("Chief Warrant Officer"));

        assertThat("Unexpected Name", EnumRank.Admiral.getFormattedString(), is("Admiral"));

        assertThat("Unexpected Name", EnumRank.LieutenantJuniorgrade.getFormattedString(), is("Lieutenant Juniorgrade"));
 
        assertThat("Unexpected Name", EnumRank.RearAdmiral.getFormattedString(), is("Rear Admiral"));
    }
}
