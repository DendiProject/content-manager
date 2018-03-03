/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

 import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class MetaInformation implements Serializable{

    @Id
    @Column(name = "meta_inf_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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

    public String getValue() {
        return value;
    }

    public Node getNode() {
        return node;
    }

    public MetaInformationType getMetaInformationType() {
        return metaInformationType;
    }

    public void setMetaInfId(String metaInfId) {
        this.metaInfId = metaInfId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setMetaInformationType(MetaInformationType metaInformationType) {
        this.metaInformationType = metaInformationType;
    }


    
}
