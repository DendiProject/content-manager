/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;


import com.netckracker.content.manager.convertor.Convertor;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.repository.NodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.TagDto;
import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.model.VerbDto;
import com.netckracker.content.manager.repository.NodeTypeRepository;
import com.netckracker.content.manager.repository.TagRepository;
import com.netckracker.content.manager.repository.VerbRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;



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
    private TagRepository tagRepository;
    @Autowired
    private NodeTypeRepository nodeTypeRepository;
    @Autowired
    private Convertor convertor;
    final static String IMAGE_RESOURCE_PATH = "/filestorage/";



    @Transactional
    @Override
    public synchronized String addNode(byte[] array, String typeName, boolean userResources, int size) {  

        
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public void deleteNode(String id) {        
        Node node=nodeRepository.findById(id);
        String hash=node.getCheckSum();        
        String fileName=node.getName()+"."+node.getNodeType().getName();
        nodeRepository.delete(node);
        if (nodeRepository.findByCheckSum(hash)==null)
            {
                File f=new File(IMAGE_RESOURCE_PATH+fileName);
                f.delete();
            }
    }

    @Override
    public byte[] findById(String id) {
        byte[] array = null;
        File f=new File(nodeRepository.findById(id).getNodeSource());
        FileInputStream fin;
        try {
            fin = new FileInputStream(f);
            fin.read(array);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NodeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NodeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return array;
    }

    @Transactional
    @Override
    public void addVerb(String nodeId, String verbName) {
        if (nodeRepository.findById(nodeId).getUserId()==null)
        {
            Verb verb=new Verb();
            verb=verbRepository.findByName(verbName);
            if (verb==null)
            {
                Verb newVerb=new Verb();
                newVerb.setName(verbName);
                Verb saved=verbRepository.save(newVerb);
                Node node=nodeRepository.findById(nodeId);
                node.getVerbList().add(saved);
            }
            else {
                Node node=nodeRepository.findById(nodeId);
                node.getVerbList().add(verb);
            }
        }        
    }
    
    @Transactional
    @Override
    public void addTag(String nodeId, String tagName) {
        if (nodeRepository.findById(nodeId).getUserId()==null)
        {
            Tag tag=new Tag();
            tag=tagRepository.findByName(tagName);
            if (tag==null)
            {
                Tag newTag=new Tag();
                newTag.setName(tagName);
                tagRepository.save(newTag);
                Tag saved=tagRepository.findByName(tagName);
                Node node=nodeRepository.findById(nodeId);
                node.getTagList().add(saved);                 
                nodeRepository.save(node);
            }
            else {
                Node node=nodeRepository.findById(nodeId);
                node.getTagList().add(tag);
                nodeRepository.save(node);
            }
        }      
    }   

    @Override
    public List<NodeDto> findByVerb(String nameVerb,  int page, int size) {
        Verb verb=verbRepository.findByName(nameVerb);
        List <Node> nodes=new ArrayList<Node>();    
       nodes=nodeRepository.findByVerb(verb.getId(), new PageRequest(page, size)).getContent();
       return nodes.stream()
               .map(node->convertor.convertNodeToDto(node))
               .collect(Collectors.toList());   
    }

    @Override
    public List<NodeDto> findByTag(String nameTag,  int page, int size) {
       Tag tag=tagRepository.findByName(nameTag);     
       List <Node> nodes=new ArrayList<Node>();    
       nodes=nodeRepository.findByTag(tag.getId(), new PageRequest(page, size)).getContent();
       return nodes.stream()
               .map(node->convertor.convertNodeToDto(node))
               .collect(Collectors.toList());

    }

    @Override
    public List<VerbDto> findVerbByLetters(String letters) {    
        List <Verb> verbs=new ArrayList<Verb>(); 
        verbs=verbRepository.findFirst10ByNameLike(letters);
        return verbs.stream()
               .map(verb->convertor.convertVerbToDto(verb))
               .collect(Collectors.toList());
       
    }

    @Override
    public List<TagDto> findTagByLetters(String letters) {
       List <Tag> tags=new ArrayList<Tag>(); 
        tags=tagRepository.findFirst10ByNameContaining(letters);
        return tags.stream()
               .map(tag->convertor.convertTagToDto(tag))
               .collect(Collectors.toList());
    }
   
}
