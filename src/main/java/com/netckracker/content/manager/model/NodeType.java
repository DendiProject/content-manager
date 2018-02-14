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
public class NodeType implements Serializable {
    @Id   
    @Column(name = "id_type")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @Column
    private String name;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
