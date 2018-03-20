/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.listener;

import com.netckracker.content.manager.service.FileSystemStorageService;
import com.netckracker.content.manager.service.NodeServiceImpl;
import com.netckracker.content.manager.service.StorageService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author eliza
 */
@Component
public class NodeListener {

    @Autowired
    private StorageService fileService;

     public void receiveMessage(Map<String, String> newNode) {
         byte[] content=newNode.get("content").getBytes();
         String nodeId=newNode.get("nodeId");

         
      fileService.store(content, nodeId);
    }
}
