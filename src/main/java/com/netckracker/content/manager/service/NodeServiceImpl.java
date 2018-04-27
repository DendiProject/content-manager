/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;


import com.netckracker.content.manager.convertor.Convertor;
import com.netckracker.content.manager.model.MetaInformation;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.model.NodeText;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.repository.NodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.TagDto;
import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.model.VerbDto;
import com.netckracker.content.manager.repository.MetaInformationRepository;
import com.netckracker.content.manager.repository.NodeTextRepository;
import com.netckracker.content.manager.repository.NodeTypeRepository;
import com.netckracker.content.manager.repository.TagRepository;
import com.netckracker.content.manager.repository.VerbRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
    @Autowired
    private StorageService fileService;
    @Autowired
    private MetaInformationRepository metaRepository;
    @Autowired
    private NodeTextRepository textRepository;

    

      
    @Transactional
    @Override
    public  NodeDto addNodeImg( String fileName, String type, String userId, String size, String extension) {  
            Node node=new Node();
            NodeType nodeType=nodeTypeRepository.findByName(type);
            if (nodeType==null)
            {   
                NodeType newNodeType=new NodeType();
                newNodeType.setName(type);
                NodeType savedType=nodeTypeRepository.save(newNodeType);
                node.setNodeType(savedType);
            }
            else 
            {
                node.setNodeType(nodeType);
            }                
            node.setName(fileName+"."+extension);
            node.setUserId(userId);
            MetaInformation fileSize=new MetaInformation();
            fileSize.setMetaInformationType("size");
            fileSize.setValue(size);
            MetaInformation fileExtension=new MetaInformation();
            fileExtension.setMetaInformationType("extension");
            fileExtension.setValue(extension);
            MetaInformation savedFileSize=metaRepository.save(fileSize);
            MetaInformation savedFileExtension=metaRepository.save(fileExtension);
            node.getMetaList().add(savedFileSize);
            node.getMetaList().add(savedFileExtension);
            Node saved=nodeRepository.save(node);
            NodeDto newNode=convertor.convertNodeToDto(saved);
            return newNode;
    }
    

    @Transactional
    @Override
    public void deleteNode(String id) {        
        Node node=nodeRepository.findById(id);
        if (node!=null)
        {
            String hash=node.getCheckSum();        
        String fileName=node.getNodeSource();
        nodeRepository.delete(node.getId());
        if (nodeRepository.findByCheckSum(hash).isEmpty()&&fileName.isEmpty())
            {                
                fileService.delete(fileName);
            }
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
            Verb verb=verbRepository.findByName(verbName);
            if (verb==null)
            {
                Verb newVerb=new Verb();
                newVerb.setName(verbName);
                Verb saved=verbRepository.save(newVerb);
                Node node=nodeRepository.findById(nodeId);
                node.getVerbList().add(saved);
                nodeRepository.save(node);
            }
            else {
                Node node=nodeRepository.findById(nodeId);
                node.getVerbList().add(verb);
                nodeRepository.save(node);
            }
        }        
    }
    
    @Transactional
    @Override
    public void addTag(String nodeId, String tagName) {
        if (nodeRepository.findById(nodeId).getUserId()==null)
        {
            Tag tag=tagRepository.findByName(tagName);
            if (tag==null)
            {
                Tag newTag=new Tag();
                newTag.setName(tagName);                
                Tag saved=tagRepository.save(newTag);
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
       List <Node> nodes=nodeRepository.findByVerb(verb.getVerbId(), new PageRequest(page, size)).getContent();
       return nodes.stream()
               .map(node->convertor.convertNodeToDto(node))
               .collect(Collectors.toList());   
    }

    @Override
    public List<NodeDto> findByTag(String nameTag,  int page, int size) {
       Tag tag=tagRepository.findByName(nameTag);        
       List <Node> nodes=nodeRepository.findByTag(tag.getId(), new PageRequest(page, size)).getContent();
       return nodes.stream()
               .map(node->convertor.convertNodeToDto(node))
               .collect(Collectors.toList());

    }

    @Override
    public List<VerbDto> findVerbByLetters(String letters) {    
        List <Verb> verbs=new ArrayList<>(); 
        verbs=verbRepository.findFirst10ByNameStartingWith(letters);
        return verbs.stream()
               .map(verb->convertor.convertVerbToDto(verb))
               .collect(Collectors.toList());
       
    }

    @Override
    public List<TagDto> findTagByLetters(String letters) {
       List <Tag> tags=new ArrayList<>(); 
        tags=tagRepository.findFirst10ByNameStartingWith(letters);
        return tags.stream()
               .map(tag->convertor.convertTagToDto(tag))
               .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NodeDto addNodeText(String name, String userId, String type, String size, String content) {
        Node node=new Node();
        node.setName(name);
        node.setUserId(userId);
        NodeType nodeType=nodeTypeRepository.findByName(type);
            if (nodeType==null)
            {   
                NodeType newNodeType=new NodeType();
                newNodeType.setName(type);
                NodeType savedType=nodeTypeRepository.save(newNodeType);
                node.setNodeType(savedType);
            }
            else 
            {
                node.setNodeType(nodeType);
            }   
        
        MetaInformation fileSize=new MetaInformation();
        fileSize.setMetaInformationType("size");
        fileSize.setValue(size);
        MetaInformation savedFileSize=metaRepository.save(fileSize);
        node.getMetaList().add(savedFileSize);
        Node saved=nodeRepository.save(node);
        NodeText text=new NodeText();
        text.setNode(saved);
        text.setText(content);
        textRepository.save(text);
        NodeDto newNode=convertor.convertNodeToDto(saved);
        return newNode;
    }

    @Override
    public String getNodeText(String nodeId) {
        Node node=nodeRepository.findById(nodeId);
        if (node!=null)
        {
            NodeText text=textRepository.findByNode(node);
            return text.getText();
        }
        else return null;
    }

    @Override
    public void deleteNodeText(String nodeId) {
        Node node=nodeRepository.findById(nodeId);
        if (node!=null)
        {
            NodeText text=textRepository.findByNode(node);
            if (text!=null)
            {
                textRepository.delete(text.getTextId());
                nodeRepository.delete(nodeId);
            }
            
        }
        
    }
   
}
