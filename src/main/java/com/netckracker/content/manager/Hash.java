/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

//import org.springframework.util.DigestUtils;

/**
 *
 * @author eliza
 */
public class Hash {
    
    public String getChecksum(byte[] fileInArray) {
        Checksum checksum = new CRC32();
        checksum.update(fileInArray,0,fileInArray.length);
        long lngChecksum = checksum.getValue();     
        String sum = Long.toString(lngChecksum);
        return sum;        
    }    
    
}
