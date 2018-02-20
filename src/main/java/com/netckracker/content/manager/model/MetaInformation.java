/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

 import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="meta_information")
public class MetaInformation implements Serializable{

    @Id
    @Column(name = "meta_inf_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private String metaInfId;
    
    @Column(name = "value")   
    private String value;
    
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;
    
    @ManyToOne
    @JoinColumn(name = "meta_type_id")
    private MetaInformationType metaInformationType;

    public String getMetaInfId() {
        return metaInfId;
    }

    public void setMetaInfId(String metaInfId) {
        this.metaInfId = metaInfId;
    }
    
    public String getValue() {
        return value;
    }
    
        public void setValue(String value) {
        this.value = value;
    }
    
}
