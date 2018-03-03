/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author eliza
 */
@Entity
@Table
public class Verb  implements Serializable{
    @Id   
    @Column(name = "verb_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String verbId;

    @Column(name = "verb_name")
    private String name;
    
    public String getId() {
        return verbId;
    }

    public void setId(String id) {
        this.verbId = verbId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
