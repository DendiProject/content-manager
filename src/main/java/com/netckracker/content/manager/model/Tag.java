/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author eliza
 */
@Entity
@Table
public class Tag implements Serializable {
    @Id   
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    
}
