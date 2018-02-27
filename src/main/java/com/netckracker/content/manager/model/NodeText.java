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
public class NodeText implements Serializable {

    @Id
    @Column(name="text_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String textId;
    
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;
    
   @Column(name="text")
    private String text;
     

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }
    
    public void setText(String text) {
        this.text=text;
    }
    
        public String getText() {
        return text;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
    
    
}
