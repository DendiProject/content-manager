/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.service.NodeServiceImpl;
import com.netckracker.content.manager.service.StorageService;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
    
    @ApiOperation("Add file by Id")
    @RequestMapping(value = "/file/addfile/{nodeId}", method = RequestMethod.POST)             
    public ResponseEntity<Void> addFile(@RequestParam MultipartFile file,@PathVariable String nodeId) throws IOException{
       
        if (!file.isEmpty())
        {
            InputStream is =  new BufferedInputStream(file.getInputStream());
            byte[] array=new byte[is.available()];
            is.read(array, 0, is.available());
            is.close();
            Path tmp=Files.createTempFile(nodeId, null);
            Files.write(tmp, array);
            Node node=nodeRepository.findById(nodeId);
            node.setNodeSource(tmp.toString());
            Map<String, byte[]> newFile = new HashMap<>();
            newFile.put("content", array);
            newFile.put("nodeId", nodeId.getBytes());
           // storageService.store(array, nodeId);
            rabbitTemplate.convertAndSend(newFile);
        }
           
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation("Get file by Id")
    @RequestMapping(value = "/file/getfile/{nodeId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getNode(@PathVariable String nodeId) throws IOException {
        byte[] content=null;
        String type =nodeRepository.findById(nodeId).getNodeType().getName();
        content=storageService.load(nodeId);
        
        if (content == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{            
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", type);            
            return new ResponseEntity<byte[]>(content, responseHeaders, HttpStatus.OK);
            }
    }
        

}
