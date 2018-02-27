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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.netckracker.content.manager.model.NodeTag;
import com.netckracker.content.manager.model.NodeType;


/**
 *
 * @author eliza
 */
@Service
public class NodeServiceImpl implements NodeService{
    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public List<Node> findAll() {
         return Lists.newArrayList(nodeRepository.findAll());
    }

    @Override
    public Node findById(String id) {
        return  nodeRepository.findById(id);
    }
    
    @Override
    @Transactional    
    public String addNode(Node node) {
        Node savedNode=nodeRepository.save(node);        
        return savedNode.getId();
    } 

    @Override
    public List<Node> findByType(NodeType type) {
        return Lists.newArrayList(nodeRepository.findByNodeType(type));
    }

     
    
    
}
