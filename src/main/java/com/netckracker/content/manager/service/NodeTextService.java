/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeText;
import com.netckracker.content.manager.model.NodeVerb;
import com.netckracker.content.manager.model.Verb;
import java.util.List;

/**
 *
 * @author eliza
 */
public interface NodeTextService {
     public List<NodeText> findAll();  
    public NodeText findByNode(Node node);   
    public  void  addNodeText(NodeText nodeText);  
    
}
