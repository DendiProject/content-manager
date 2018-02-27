/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.service.NodeService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eliza
 */
@RestController
public class Controller {
    
    @Autowired
    private NodeService nodeService;        
     
    @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getNodeById( @PathVariable(value = "id")String id) throws IOException {
        
       Node node = (Node) nodeService.findById(id);  
       //byte[] array; 
        if (node == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            File f=new File(node.getSource());
            byte[] fileInArray = new byte[(int)f.length()];
            FileInputStream fin = new FileInputStream(f);
            fin.read(fileInArray);
            return new ResponseEntity<>(fileInArray, HttpStatus.OK);
        }  
    }

    @RequestMapping(value = "/node/{id}", method = RequestMethod.POST)
    public synchronized ResponseEntity <String> addNode( @RequestBody byte [] array) throws IOException {
        
       Node newNode=new Node();
       String savedNodeId=nodeService.addNode(newNode);
      return new ResponseEntity<>(savedNodeId, HttpStatus.OK);
  }
    
   /* @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<byte[]>> getNodesByTag( @PathVariable(value = "tag")String tag) throws IOException {
        
       List<Node> nodes =  nodeService.findByTag(tag);
       List <byte[]> array = null;        
        if (nodes == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Iterator iter =nodes.iterator();
        while (iter.hasNext())
                {
                    Node node = (Node) iter.next();
                    File f=new File(node.getSource());
                    byte[] fileInArray = new byte[(int)f.length()];
                    FileInputStream fin = new FileInputStream(f);
                    fin.read(fileInArray);
                    array.add(fileInArray);                    
                }
            return new ResponseEntity<>(array, HttpStatus.OK);
        }  
    }
    */
  /*  @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<byte[]>> getNodesByVerb( @PathVariable(value = "verb")String verb) throws IOException {        
       List<Node> nodes =  nodeService.findByVerb(verb);
       List <byte[]> array = null;        
        if (nodes == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Iterator iter =nodes.iterator();
        while (iter.hasNext())
                {
                    Node node = (Node) iter.next();
                    File f=new File(node.getSource());
                    byte[] fileInArray = new byte[(int)f.length()];
                    FileInputStream fin = new FileInputStream(f);
                    fin.read(fileInArray);
                    array.add(fileInArray);                    
                }
            return new ResponseEntity<>(array, HttpStatus.OK);
        } 
   // }*/

       
   
}
