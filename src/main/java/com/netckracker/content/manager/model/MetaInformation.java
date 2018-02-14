/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

 import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table
public class MetaInformation implements Serializable{

    @Id
    private Long metaInfId;
    
    @Column
    private String value;
    
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;
    
    @ManyToOne
    @JoinColumn(name = "meta_inf_id")
    private MetaInformationType metaInformationType;

    public Long getMetaInfId() {
        return metaInfId;
    }

    public void setMetaInfId(Long metaInfId) {
        this.metaInfId = metaInfId;
    }
    
    public String getValue() {
        return value;
    }
    
        public void setValue(String value) {
        this.value = value;
    }
    
}
