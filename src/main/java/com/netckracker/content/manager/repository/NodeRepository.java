/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.Verb;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
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
    List<Node> findByCheckSum(String checkSum);
    //List<Node> findAll( );
    //List<Node> findByVerb(Verb verb, Pageable pageable );
    
    
}
