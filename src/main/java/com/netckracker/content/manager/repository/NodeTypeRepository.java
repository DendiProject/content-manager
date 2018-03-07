/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;

import com.netckracker.content.manager.model.NodeText;
import com.netckracker.content.manager.model.NodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliza
 */
@Repository
public interface NodeTypeRepository  extends JpaRepository <NodeType, String>  {
    NodeType findByTypeId(String id);
    NodeType findByName(String name);
    
    
}
