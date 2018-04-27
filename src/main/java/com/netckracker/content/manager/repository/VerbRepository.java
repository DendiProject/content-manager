/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;

import com.netckracker.content.manager.model.Verb;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliza
 */
@Repository
public interface VerbRepository   extends JpaRepository <Verb, String> {
    Verb findByVerbId(String verbId);
    Verb findByName(String name);
    List<Verb> findFirst10ByNameStartingWith(String firstLetters);

}
