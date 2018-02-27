/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.Tag;

/**
 *
 * @author eliza
 */
public interface TagService {
    public void addTag(Tag tag);
    public Tag findById(String tagId);
    
}
