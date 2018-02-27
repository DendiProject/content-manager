/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.repository.TagRepository;
import com.netckracker.content.manager.repository.VerbRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliza
 */
public class VerbServiceImpl implements VerbService {

    @Autowired
    private VerbRepository verbRepository;
    
    @Override
    public void addVerb(Verb verb) {
        Verb savedVerb=verbRepository.save(verb);
    }

    @Override
    public Verb findByVerbId(String verbId) {
        return verbRepository.findByVerbId(verbId);
    }
    
    
}
