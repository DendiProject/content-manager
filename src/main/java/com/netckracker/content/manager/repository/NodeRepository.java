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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query(value = "select n.* from Node n Where n.node_id in"
        + "(Select t.Node_node_id from Node_tagList t where n.node_id=t.Node_node_id and t.tagList_tag_id=:tagid)"
        + "ORDER BY n.node_id --#pageable\n",            
         countQuery = "select count(*) from Node n Where n.node_id in"
        + "(Select t.Node_node_id from Node_tagList t where n.node_id=t.Node_node_id and t.tagList_tag_id=:tagid)"
        + "ORDER BY n.node_id --#pageable\n",
        nativeQuery = true)
    Page<Node> findByTag(@Param("tagid") String tagId, Pageable pageable);
    
    @Query(value = "select n.* from Node n Where n.node_id in"
        + "(Select v.Node_node_id from Node_verbList v where n.node_id=v.Node_node_id and v.verbList_verb_id=:verbid)"
        + "ORDER BY n.node_id --#pageable\n",            
         countQuery = "select count(*) from Node n Where n.node_id in"
        + "(Select v.Node_node_id from Node_verbList v where n.node_id=v.Node_node_id and v.verbList_verb_id=:verbid)"
        + "ORDER BY n.node_id --#pageable\n", 
        nativeQuery = true)
    Page<Node> findByVerb(@Param("verbid") String verbId, Pageable pageable);
    
    
}
