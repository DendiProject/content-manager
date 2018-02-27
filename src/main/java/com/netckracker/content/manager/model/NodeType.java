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
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String typeId;

    @Column(name = "type_name")
    private String name;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    


}
