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
import com.netckracker.content.manager.model.NodeVerb;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.repository.NodeTagRepository;
import com.netckracker.content.manager.repository.NodeVerbRepository;
import com.netckracker.content.manager.repository.TagRepository;
import com.netckracker.content.manager.repository.VerbRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author eliza
 */
@Service
public class NodeServiceImpl implements NodeService{
    @Autowired
    private NodeRepository nodeRepository;    
    @Autowired
    private VerbRepository verbRepository;
    @Autowired
    private NodeVerbRepository nodeVerbRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private NodeTagRepository nodeTagRepository;


    @Transactional
    @Override
    public String addNode(byte[] array, String typeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public void deleteNode(String id) {
        Node node=nodeRepository.findById(id);
        nodeRepository.delete(node); 
    }

    @Override
    public byte[] findById(String id) {
        byte[] array = null;
        try {
          array=Files.readAllBytes(Paths.get(nodeRepository.findById(id).getSource()));
          String type=nodeRepository.findById(id).getNodeType().getName();
        } catch (IOException ex) {
            Logger.getLogger(NodeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    @Transactional
    @Override
    public void addVerb(String nodeId, String verbName) {
        Verb verb=new Verb();
        verb=verbRepository.findByName(verbName);
        if (verb==null)
        {
            Verb newVerb=new Verb();
            newVerb.setName(verbName);
            Verb saved=verbRepository.save(newVerb);
            NodeVerb nodeVerb=new NodeVerb();
            nodeVerb.setNode(nodeRepository.findById(nodeId));
            nodeVerb.setVerb(saved);
            NodeVerb savedNodeVerb=nodeVerbRepository.save(nodeVerb);
        }
        
    }

    @Transactional
    @Override
    public void addTag(String nodeId, String tagName) {
        Tag tag=new Tag();
        tag=tagRepository.findByName(tagName);
        if (tag==null)
        {
            Tag newTag=new Tag();
            newTag.setName(tagName);
            Tag saved=tagRepository.save(newTag);
            NodeTag nodeTag=new NodeTag();
            nodeTag.setNode(nodeRepository.findById(nodeId));
            nodeTag.setTag(saved);
            NodeTag savedNodeTag=nodeTagRepository.save(nodeTag);
        }
    }   

    @Override
    public List<String> findByVerb(String nameVerb,   int page, int size) {
        Verb verb=verbRepository.findByName(nameVerb);
        List<String> nodesId=new ArrayList<>();
        Pageable pageable = new PageRequest(page, size);
        Iterator iter =nodeVerbRepository.findByVerb(verb, pageable).iterator();
        while (iter.hasNext())
                {
                    NodeVerb nodeVerb = (NodeVerb) iter.next();
                    nodesId.add(nodeVerb.getNode().getId());
                }
        
       return nodesId;
    }

    @Override
    public List<String> findByTag(String nameTag,  int page, int size) {
        Tag tag=tagRepository.findByName(nameTag);
        List<String> nodesId=new ArrayList<>();
        Pageable pageable = new PageRequest(page, size);
        Iterator iter =nodeTagRepository.findByTag(tag, pageable).iterator();
        while (iter.hasNext())
                {
                    NodeTag nodeTag = (NodeTag) iter.next();
                    nodesId.add(nodeTag.getNode().getId());
                }
        
       return nodesId;
    }

    @Override
    public List<String> findVerbByLetters(String letters) {        
        List<String> verbs=new ArrayList<>();   
        
        Iterator iter =verbRepository.findFirst10ByNameLike(letters).iterator();
        while (iter.hasNext())
                {
                    Verb verb = (Verb) iter.next();
                    verbs.add(verb.getName());
                }
        return verbs;
    }

    @Override
    public List<String> findTagByLetters(String letters) {
        List<String> tags=new ArrayList<>();   
        
        Iterator iter =tagRepository.findFirst10ByNameLike(letters).iterator();
        while (iter.hasNext())
                {
                    Tag tag = (Tag) iter.next();
                    tags.add(tag.getName());
                }
        return tags;
    }
   
}
