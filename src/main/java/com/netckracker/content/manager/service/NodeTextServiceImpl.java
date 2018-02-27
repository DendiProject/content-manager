/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.google.common.collect.Lists;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeText;
import com.netckracker.content.manager.repository.NodeTextRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eliza
 */
@Service
public class NodeTextServiceImpl implements NodeTextService{
    @Autowired
    private NodeTextRepository nodeTextRepository;

    @Override
    public List<NodeText> findAll() {
        return Lists.newArrayList(nodeTextRepository.findAll());
    }

    @Override
    public NodeText findByNode(Node node) {
        return  nodeTextRepository.findByNode(node);
    }

    @Override
    @Transactional
    public void addNodeText(NodeText nodeText) {
        NodeText savedNodeText=nodeTextRepository.save(nodeText);
    }
    
}
