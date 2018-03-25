/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author eliza
 */
public interface StorageService {
byte[] load( String nodeId)  throws FileNotFoundException, IOException;
void store(byte[] array, String nodeId);
void delete(String fileName);
public void init();
}
