/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.contorller;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.service.NodeService;
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
    @Autowired
    private NodeRepository nodeRepository;
    
    @GetMapping("node/{id}")
	public ResponseEntity<Node> getNodeById(@PathVariable("id") String id) {
		Node node = (Node) nodeService.findById(id);
		return new ResponseEntity<Node>(node, HttpStatus.OK);
	}        
   
}
