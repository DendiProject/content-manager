/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.NodeTag;
import com.netckracker.content.manager.model.NodeVerb;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.Verb;
import java.util.List;

/**
 *
 * @author eliza
 */
public interface NodeVerbService {
    public List<NodeVerb> findAll();  
    public List<NodeVerb> findByVerb(Verb verb);   
    public  void  addNodeVerb(NodeVerb nodeVerb);  
    
}
