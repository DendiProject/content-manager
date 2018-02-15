/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author eliza
 */
@Entity
@Table
public class MetaInformationType implements Serializable {
    @Id   
    @Column(name = "meta_type_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private String metaInfTypeId;
    
    @Column(name = "meta_type")
    private String typeMeta;       
    
    public String getId() {
        return metaInfTypeId;
    }

    public void setId(Long id) {
        this.metaInfTypeId =metaInfTypeId;
    }
    public String gettypeMeta() {
        return typeMeta;
    }

    public void setName(String name) {
        this.typeMeta = typeMeta;
    }

}
