/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.queue;

import com.netckracker.content.manager.resource.Resource;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliza
 */
public class Producer  implements Runnable{
    
   private final BlockingQueue<Resource> queue;
    @Autowired
    private NodeServiceImpl nodeService;
    
   public Producer(BlockingQueue queue) { 
        this.queue = queue; 
    }


    @Override
    public void run() {
       // consumer.addNode();
      /* List<Resource> resources=nodeService.getResources();
       for (Resource resource: resources )
       {
           try {
               queue.put(resource);
           } catch (InterruptedException ex) {
               Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
           }
       }*/
    }   

  
}
