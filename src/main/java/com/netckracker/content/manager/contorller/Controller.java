/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.queue.Consumer;
import com.netckracker.content.manager.queue.Producer;
import com.netckracker.content.manager.resource.Resource;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eliza
 */
@RestController
public class Controller {
    
    @Autowired
    private NodeServiceImpl nodeService;        
     
    @RequestMapping(value = "/tag/addtag", method = RequestMethod.POST)
    public ResponseEntity<Void> addTag(@RequestBody  String nodeId,@RequestBody  String tagName){
        nodeService.addTag(nodeId, tagName);       
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/verb/addverb", method = RequestMethod.POST)
    public ResponseEntity<Void> addVerb(@RequestBody  String nodeId,@RequestBody  String verbName){
        nodeService.addVerb(nodeId, verbName);       
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
    @RequestMapping(value = "/tag/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<NodeDto>> getNodeByTag(
        @RequestParam(required = true, value = "name") String name) {
        
        List<NodeDto> nodes=nodeService.findByTag(name, 0, 0);
        if (nodes == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            return new ResponseEntity<>(nodes, HttpStatus.OK);
        }        
    }
    
        @RequestMapping(value = "/verb/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<NodeDto>> getNodeByVer(
         @RequestParam(required = true, value = "name")String name) {
        
        List<NodeDto> nodes=nodeService.findByVerb(name, 0, 0);
       
        
        if (nodes == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            return new ResponseEntity<>(nodes, HttpStatus.OK);
        }        
    }
    @RequestMapping(value = "/node/addnode", method = RequestMethod.POST)
    public ResponseEntity<NodeDto> addVerb(@RequestBody  byte array[], String type){
        Resource resource=new Resource(array, type, null, 0);
        nodeService.getResources().add(resource);
        NodeDto node=new NodeDto();
       // nodeService.addNode(array, type, type, 0);
        return new ResponseEntity<>(node, HttpStatus.OK);
    }
    
}
