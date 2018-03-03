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
public class NodeVerb  implements Serializable {
    @Id
    @Column(name="node_verb_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String nodeVerbId;
    
    @ManyToOne
    @JoinColumn(name = "verb_id")
    private Verb verb;
    
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;

    public String getNodeVerbId() {
        return nodeVerbId;
    }

    public void setNodeVerbId(String nodeVerbId) {
        this.nodeVerbId = nodeVerbId;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
    
    
}
