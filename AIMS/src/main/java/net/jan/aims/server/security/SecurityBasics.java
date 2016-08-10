/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.security;

import org.jboss.security.Util;

/**
 *
 * @author Jan
 */
public class SecurityBasics {
    
    public static String createPWSHA256Hash(String pw){
        return Util.createPasswordHash("SHA-256", "BASE64", null, null, pw);
    }
    
}
