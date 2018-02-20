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
@Table(name = "tags")
public class Tag implements Serializable {
    @Id   
    @Column(name = "tag_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long tagId;

    @Column (name = "tag_name")
    private String name;
    
    public Long getId() {
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
    
}
