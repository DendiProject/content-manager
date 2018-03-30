/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.initialize;

import com.netckracker.content.manager.service.FileSystemStorageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliza
 */
@Component
public class DirectoryCreator implements  InitializingBean{
    @Autowired
    private FileSystemStorageService storageService;

    @Override
    public void afterPropertiesSet() throws Exception {
        storageService.setRootLocation("/filestorage");
        
        storageService.init();
    }
    
}
