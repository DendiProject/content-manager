/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.Hash;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private NodeService nodeService;    
    @Autowired
    private RabbitTemplate rabbitTemplate;


   
    @RequestMapping(value = "/tag/addtag/{nodeId}", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<Void> addTag(@PathVariable String nodeId,@RequestParam String tagName){
        nodeService.addTag(nodeId, tagName);       
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/verb/addverb/{nodeId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<Void> addVerb(@PathVariable  String nodeId,@RequestParam  String verbName){
        nodeService.addVerb(nodeId, verbName);       
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
    @RequestMapping(value = "/tag/{name}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<List<NodeDto>> getNodeByTag(@PathVariable String name) {
        
        List<NodeDto> nodes=nodeService.findByTag(name, 0, 2);
        if (nodes == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            return new ResponseEntity<>(nodes, HttpStatus.OK);
        }        
    }
    
        @RequestMapping(value = "/verb/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<NodeDto>> getNodeByVerb(
         @PathVariable String name) {
        
        List<NodeDto> nodes=nodeService.findByVerb(name, 0, 0);
       
        
        if (nodes == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            return new ResponseEntity<>(nodes, HttpStatus.OK);
        }        
    }
    @RequestMapping(value = "/node/addnodeimg", method = RequestMethod.POST)
    public ResponseEntity<NodeDto> addNode(@RequestBody  String fileName, String type, int size, String extension) throws InterruptedException{
       NodeDto node=nodeService.addNodeImg(fileName, type, null, size, extension); 
       return new ResponseEntity<>(node, HttpStatus.OK);
    }
    
}
