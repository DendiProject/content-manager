/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.model.TagDto;
import com.netckracker.content.manager.model.VerbDto;
import java.util.List;

/**
 *
 * @author eliza
 */

public interface NodeService {
    //public String findByType(String nameType);  
    public List<NodeDto> findByVerb(String nameVerb, int page, int size); 
    public List<NodeDto> findByTag(String nameTag,  int page, int size);
    public byte[] findById(String id); 
    public  NodeDto  addNodeImg(String fileName, String type, String userId, String size, String extension); 
    public  void  deleteNode(String id); 
    public void addVerb(String nodeId, String verbName);
    public void addTag(String nodeId, String tagName);
    public List<VerbDto> findVerbByLetters(String letters);
    public List<TagDto> findTagByLetters(String letters);
    
    
   
}
