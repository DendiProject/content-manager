/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.service.NodeService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
   
    @ApiOperation("Add tag by nodeId")
    @RequestMapping(value = "/tag/addtag/{nodeId}", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<Void> addTag(@PathVariable ("nodeId") String nodeId,@RequestParam String tagName){
        nodeService.addTag(nodeId, tagName);       
       
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation("Add verb by nodeId")
    @RequestMapping(value = "/verb/addverb/{nodeId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<Void> addVerb(@PathVariable  String nodeId,@RequestParam  String verbName){
        nodeService.addVerb(nodeId, verbName);       
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
    @ApiOperation("Get nodeList by tagName")
    @RequestMapping(value = "/node/bytag/{tagName}", params = { "page", "size" },method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public  ResponseEntity<?> getNodesByTag(@PathVariable  String  tagName, @RequestParam( "page" ) int page, @RequestParam( "size" ) int size ){
        
        if (size==0&&page==0)
        {
            page=0;
            size=6;
        }
        
        List<NodeDto> nodes=nodeService.findByTag(tagName,page, size);
        if (nodes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{           
            return new ResponseEntity<>(nodes, HttpStatus.OK);
        }
    }
    
    @ApiOperation("Get nodeList by verbName")
    @RequestMapping(value = "/node/byverb/{verbName}",params = { "page", "size" }, method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<?> getNodeByVerb(@PathVariable String verbName , @RequestParam( "page" ) int page, @RequestParam( "size" ) int size) {   
        if (size==0&&page==0)
        {
            page=0;
            size=6;
        }
        List<NodeDto> nodes=nodeService.findByVerb(verbName, page, size);      
        if (nodes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            return new ResponseEntity<>(nodes, HttpStatus.OK);
        }         
    }
    
    @ApiOperation("Add node image")
    @RequestMapping(value = "/node/addnodeimg", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<NodeDto> addNode(@RequestParam  String fileName, @RequestParam String type, @RequestParam String size,  @RequestParam String extension) throws InterruptedException{
       NodeDto node=nodeService.addNodeImg(fileName, type, null, size, extension); 
       return new ResponseEntity<>(node, HttpStatus.OK);
    }
    
    @ApiOperation("Delete node by Id")
    @RequestMapping(value = "/node/deletenode/{nodeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNode(@PathVariable String nodeId) throws InterruptedException{
       nodeService.deleteNode(nodeId);
       return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation("Delete text node by Id")
    @RequestMapping(value = "/node/deletenodetext/{nodeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNodeText(@PathVariable String nodeId) throws InterruptedException{
       nodeService.deleteNodeText(nodeId);
       return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation("Add node text")
    @RequestMapping(value = "/node/addnodetext", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<NodeDto> addNodeText(@RequestParam  String textName, @RequestParam  String type, @RequestParam String size, @RequestParam String content) throws InterruptedException{
       NodeDto node=nodeService.addNodeText(textName, null, type, size,  content);
       return new ResponseEntity<>(node, HttpStatus.OK);
    }
    
    @ApiOperation("Get node text")
    @RequestMapping(value = "/node/getnodetext/{nodeId}", method = RequestMethod.GET)
    public ResponseEntity<?> getNodeText(@PathVariable String nodeId) {
        String text=nodeService.getNodeText(nodeId);
            if (text.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            return new ResponseEntity<>(text, HttpStatus.OK);
        }  
    }
    
}
