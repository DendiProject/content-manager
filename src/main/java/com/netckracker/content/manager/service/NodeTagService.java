/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.NodeTag;
import com.netckracker.content.manager.model.Tag;
import java.util.List;

/**
 *
 * @author eliza
 */
public interface NodeTagService {
    public List<NodeTag> findAll();  
    public List<NodeTag> findByTag(Tag tag);   
    public  void  addNodeTag(NodeTag nodeTag);  
    
}
