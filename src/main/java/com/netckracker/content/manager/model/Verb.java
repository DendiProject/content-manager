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
public class Verb  implements Serializable{
    @Id   
    @Column(name = "verb_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long verbId;

    @Column
    private String name;
    
    public Long getId() {
        return verbId;
    }

    public void setId(Long id) {
        this.verbId = verbId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
