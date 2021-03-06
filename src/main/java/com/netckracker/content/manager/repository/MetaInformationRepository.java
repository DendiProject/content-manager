/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;

import com.netckracker.content.manager.model.MetaInformation;
import com.netckracker.content.manager.model.Node;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliza
 */
@Repository
public interface MetaInformationRepository extends JpaRepository <MetaInformation, String> {
  MetaInformation  findByMetaInformationType(String metaInfType);
    
}
