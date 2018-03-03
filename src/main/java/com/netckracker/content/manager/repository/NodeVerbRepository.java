/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;

import com.netckracker.content.manager.model.NodeTag;
import com.netckracker.content.manager.model.NodeVerb;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.Verb;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliza
 */
@Repository
public interface NodeVerbRepository extends JpaRepository <NodeVerb, String>{
    List<NodeVerb> findByVerb(Verb verb, Pageable pageable);  
    
}
