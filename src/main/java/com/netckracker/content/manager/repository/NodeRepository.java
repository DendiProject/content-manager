/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliza
 */

@Repository
public interface NodeRepository extends JpaRepository <Node, String> {
    List<Node> findByNodeType(NodeType type);
    Node findById(String id);
    
}
