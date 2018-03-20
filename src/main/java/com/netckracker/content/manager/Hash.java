/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import java.util.zip.CRC32;
import java.util.zip.Checksum;
import org.springframework.stereotype.Component;

//import org.springframework.util.DigestUtils;

/**
 *
 * @author eliza
 */
@Component
public class Hash {
    
    public String getChecksum(byte[] fileInArray) {
        Checksum checksum = new CRC32();
        checksum.update(fileInArray,0,fileInArray.length);
        long lngChecksum = checksum.getValue();     
        String sum = Long.toString(lngChecksum);
        return sum;        
    }    
    
}
