/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims_server.utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims.aims_server.enums.EnumRank;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jan
 */
public abstract class Utilities {
    
    
    public static byte[] getBytesOfFile(String filePath){
        byte[] restult = null;
        File f = new File(filePath);
        try {
            restult = Files.readAllBytes(f.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return restult;
    }
}
