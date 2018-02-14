/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;



/**
 *
 * @author eliza
 */
@Entity
@Table
public class Node implements Serializable {

    @Id   
    @Column(name = "node_id")        
    private String id;

    @Column
    private String name;

    @Column
    private String source;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private NodeType nodeType;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    } 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node that=(Node) obj;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name!= null) return false;
        if (source != null ? !source.equals(that.source) : that.name!= null) return false;
        
        return true;
    }
     
    @Override
	public int hashCode() {
        int result = 1;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }
    
}
