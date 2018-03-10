/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;


import com.netckracker.content.manager.model.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliza
 */
@Repository
public interface TagRepository extends JpaRepository <Tag, String> {
    Tag findByTagId(String tagId);
    Tag findByName(String tagName);
    List<Tag> findFirst10ByNameContaining(String firstLetters);
     
    
}
