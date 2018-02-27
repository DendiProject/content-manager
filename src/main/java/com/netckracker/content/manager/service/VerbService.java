/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.Verb;

/**
 *
 * @author eliza
 */
public interface VerbService {
    public void addVerb(Verb verb);
    public Verb findByVerbId(String verbId);
    
}
