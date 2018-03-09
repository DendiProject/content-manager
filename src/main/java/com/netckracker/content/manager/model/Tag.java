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
public class Tag implements Serializable {
    @Id   
    @Column(name = "tag_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String tagId;

    @Column (name = "tag_name")
    private String name;

    
    public String getId() {
        return tagId;
    }

    public void setId(Long id) {
        this.tagId = tagId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
    
    @Override
    public int hashCode() {
       int result=1;
        result = 31 * result + (name!= null ? name.hashCode() : 0);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ( !(obj instanceof Tag) ) return false;
        final Tag tag = (Tag) obj;
        if ( !tag.getName().equals(getName())) return false;
        return true;
    }
}
