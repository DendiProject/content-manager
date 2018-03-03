/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author eliza
 */
@Entity
@Table
public class MetaInformationType implements Serializable {
    @Id   
    @Column(name = "meta_type_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String metaInfTypeId;
    
    @Column(name = "meta_type_name")
    private String typeMeta;       

    public String getMetaInfTypeId() {
        return metaInfTypeId;
    }

    public String getTypeMeta() {
        return typeMeta;
    }

    public void setMetaInfTypeId(String metaInfTypeId) {
        this.metaInfTypeId = metaInfTypeId;
    }

    public void setTypeMeta(String typeMeta) {
        this.typeMeta = typeMeta;
    }   

}
