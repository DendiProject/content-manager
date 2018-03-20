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
    
    @Column(name = "meta_inf_type")
    private String metaInformationType;
    
    @Column(name = "meta_value")   
    private String value;       

    public String getMetaInfId() {
        return metaInfId;
    }

    public String getValue() {
        return value;
    }

    
    public String getMetaInformationType() {
        return metaInformationType;
    }

    public void setMetaInfId(String metaInfId) {
        this.metaInfId = metaInfId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setMetaInformationType(String metaInformationType) {
        this.metaInformationType = metaInformationType;
    }

    @Override
    public int hashCode() {
       int result=1;
        result = 31 * result + (metaInformationType!= null ? metaInformationType.hashCode() : 0);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ( !(obj instanceof MetaInformation) ) return false;
        final MetaInformation meta = (MetaInformation) obj;
        if ( !meta.getMetaInformationType().equals(getMetaInformationType())) return false;
        return true;
    }
    
}
