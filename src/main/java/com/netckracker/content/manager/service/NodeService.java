/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.repository.NodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliza
 */

public interface NodeService {
    List<Node> findAll();  
    List<Node> findById(String id); 
    public Node addNode(Node node);
    public Node deleteNode(String id);
   
}
