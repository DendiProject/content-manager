/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.google.common.collect.Lists;
import com.netckracker.content.manager.model.MetaInformation;
import com.netckracker.content.manager.model.MetaInformationType;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.repository.MetaInformationRepository;
import com.netckracker.content.manager.repository.NodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eliza
 */
@Service
public class MetaInformationServiceImpl implements MetaInformationService {
    @Autowired
    private MetaInformationRepository metaInformationRepository;

    @Override
    public List<MetaInformation> findAll() {
        return Lists.newArrayList(metaInformationRepository.findAll());
    }

    @Override
    public List<MetaInformation> findByMetaInformationTypeAndValue(MetaInformationType mataType, String value) {
        return Lists.newArrayList(metaInformationRepository.findByMetaInformationTypeAndValue(mataType, value));
    }

    @Override
    public List<MetaInformation> findByNode(Node n) {
       return Lists.newArrayList(metaInformationRepository.findByNode(n));
    }

    @Override
    @Transactional
    public void addMetaInformation(MetaInformation metaInformation) {
        MetaInformation savedMeta=metaInformationRepository.save(metaInformation);        
    }
    
}
