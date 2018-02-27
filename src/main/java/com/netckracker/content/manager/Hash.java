/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.util.DigestUtils;

/**
 *
 * @author eliza
 */
public class Hash {
    
    public static String getHash(byte[] array)    {
        String hash=DigestUtils.sha1Hex(array);        
        return hash;       
    }
    
}
