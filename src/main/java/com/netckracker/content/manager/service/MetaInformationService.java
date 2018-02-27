/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.model.MetaInformation;
import com.netckracker.content.manager.model.MetaInformationType;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeType;
import java.util.List;

/**
 *
 * @author eliza
 */
public interface MetaInformationService {
    public List<MetaInformation> findAll();  
    public List<MetaInformation> findByMetaInformationTypeAndValue(MetaInformationType mataType, String value);  
    public List<MetaInformation> findByNode(Node n); 
    public void addMetaInformation(MetaInformation metaInformation);    
}
