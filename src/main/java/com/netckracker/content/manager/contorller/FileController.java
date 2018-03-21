/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.service.NodeServiceImpl;
import com.netckracker.content.manager.service.StorageService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author eliza
 */
@RestController
public class FileController {
    @Autowired
    private NodeServiceImpl nodeService;   
    @Autowired
    private NodeRepository nodeRepository; 
    @Autowired
    private StorageService storageService; 
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @RequestMapping(value = "/file/addfile/{nodeId}", method = RequestMethod.POST, 
            consumes = MediaType.IMAGE_JPEG_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<Void> addFile(@RequestParam("file") MultipartFile file,@PathVariable String nodeId) throws IOException{
        FileInputStream fis=new FileInputStream((File) file);
        byte[] array=new byte[fis.available()];
        fis.read(array, 0, fis.available());
        fis.close();
        String content = new String(array);
        Map<String, String> newFile = new HashMap<>();
        newFile.put("content", content);
        newFile.put("nodeId", nodeId);
        newFile.put("userId",null);
        rabbitTemplate.convertAndSend(newFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/file/getfile/{nodeId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getNode(
         @RequestParam(required = true, value = "nodeId")String nodeId) throws IOException {
        
        byte[] content=storageService.load(nodeId);
        String type =nodeRepository.findById(nodeId).getNodeType().getName();
        
        if (content == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", type);            
            return new ResponseEntity<byte[]>(content,responseHeaders, HttpStatus.OK);
            }
        } 
    @RequestMapping(value = "/file/adduserfile/{nodeId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserFile(@RequestParam("file") MultipartFile file,@PathVariable String nodeId) throws IOException{
        FileInputStream fis=new FileInputStream((File) file);
        byte[] array=new byte[fis.available()];
        fis.read(array, 0, fis.available());
        fis.close();
        String content = new String(array);
        Map<String, String> newFile = new HashMap<>();
        newFile.put("content", content);
        newFile.put("nodeId", nodeId);
        newFile.put("userId",null);
        rabbitTemplate.convertAndSend(newFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
