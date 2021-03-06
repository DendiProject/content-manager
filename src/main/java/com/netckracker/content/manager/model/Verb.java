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
public class Verb  implements Serializable{
    @Id   
    @Column(name = "verb_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String verbId;

    @Column(name = "verb_name")
    private String name;
       
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerbId() {
        return verbId;
    }

    public void setVerbId(String verbId) {
        this.verbId = verbId;
    }
    @Override
    public int hashCode() {
        int result=1;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ( !(obj instanceof Tag) ) return false;
        final Verb verb = (Verb) obj;
        if ( !verb.getName().equals(getName())) return false;
        return true;
    }
}
