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
@Table(name = "verbs")
public class Verb  implements Serializable{
    @Id   
    @Column(name = "verb_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private String verbId;

    @Column(name = "verb_name")
    private String name;
    
    public String getId() {
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
