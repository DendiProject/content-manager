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


/**
 *
 * @author eliza
 */
@Service
@Repository
@Transactional
public class NodeServiceImpl implements NodeService{
    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public List<Node> findAll() {
         return Lists.newArrayList(nodeRepository.findAll());
    }

    @Override
    public List<Node> findById(String id) {
        return Lists.newArrayList(nodeRepository.findById(id));
    }
    @Transactional
    @Override
    public Node addNode(Node node) {
        Node savedNode=nodeRepository.saveAndFlush(node);
        return node;
    } 

    @Override
    public Node deleteNode(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
}
