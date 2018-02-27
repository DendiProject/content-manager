/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.google.common.collect.Lists;
import com.netckracker.content.manager.model.NodeVerb;
import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.repository.NodeVerbRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eliza
 */
@Service
public class NodeVerbServiceImpl implements NodeVerbService {
    @Autowired 
    private NodeVerbRepository nodeVerbRepository;

    @Override
    public List<NodeVerb> findAll() {
        return Lists.newArrayList(nodeVerbRepository.findAll());
    }

    @Override
    public List<NodeVerb> findByVerb(Verb verb) {
        return Lists.newArrayList(nodeVerbRepository.findByVerb(verb));
    }

    @Override
    @Transactional
    public void addNodeVerb(NodeVerb nodeVerb) {
       NodeVerb savedNodeVerb=nodeVerbRepository.save(nodeVerb);
    }
    
}
