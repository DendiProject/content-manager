/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.google.common.collect.Lists;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeTag;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.NodeTagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eliza
 */
@Service
public class NodeTagServiceImpl implements NodeTagService{
    @Autowired
    private NodeTagRepository nodeTagRepository;

    @Override
    public List<NodeTag> findAll() {
        return Lists.newArrayList(nodeTagRepository.findAll());
    }

    @Override
    public List<NodeTag> findByTag(Tag tag) {
      return  Lists.newArrayList(nodeTagRepository.findByTag(tag));
    }

    @Override
    @Transactional
    public void addNodeTag(NodeTag nodeTag) {
        NodeTag savedNodeTag=nodeTagRepository.save(nodeTag);        
    }
    
}
