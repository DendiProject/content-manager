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


   /* @Query(value = "SELECT * FROM NODE n JOIN TAG t ON n.TAG_ID=t.TAG_ID WHERE n.TAG_ID = ?1",
            countQuery = "SELECT count(*) FROM NODE n JOIN TAG t ON n.TAG_ID=t.TAG_ID WHERE n.TAG_ID = ? ORDER BY ?#{#pageable}\"",
            nativeQuery = true)
    Page<Node> findByTag(String tagId, Pageable pageable);
    
    @Query(value = "SELECT * FROM NODE n JOIN VERB v ON n.VERB_ID=v.VERB_ID WHERE n.VERB_ID = ?1 ORDER BY ?#{#pageable}\"",
            countQuery = "SELECT count(*) FROM NODE n JOIN VERB v ON n.VERB_ID=v.VERB_ID WHERE n.VERB_ID = ?1 ORDER BY ?#{#pageable}\"",
            nativeQuery = true)
    Page<Node> findByVerb(String tagId, Pageable pageable);*/
    
    @Query(value = "select * from Node n ",   
            nativeQuery = true)
    List<Node> findByTag();
    
    
    
}
