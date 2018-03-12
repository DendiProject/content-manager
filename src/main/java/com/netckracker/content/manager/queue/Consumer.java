/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.queue;

import com.netckracker.content.manager.resource.Resource;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliza
 */
public class Consumer implements Runnable{
    @Autowired
    private NodeServiceImpl nodeService;

    
     private final BlockingQueue<Resource> queue;
    
   public Consumer(BlockingQueue queue) { 
        this.queue = queue; 
    }

    @Override
    public void run() {
        
        while(true) {
            
            try {
               Resource resource=queue.take();
              // String nodeId= nodeService.addNode(resource);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
