/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliza
 */
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;
    
    @Override
    public void addTag(Tag tag) {
        Tag savedTag=tagRepository.save(tag);
    }

    @Override
    public Tag findById(String tagId) {
        return tagRepository.findByTagId(tagId);
    }
    
}
