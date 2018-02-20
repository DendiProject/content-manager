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
@Table (name="node_verbs")
public class NodeVerb  implements Serializable {
    @Id
    @Column(name="node_verb_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nodeVerbId;
    
    @ManyToOne
    @JoinColumn(name = "verb_id")
    private Verb verb;
    
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;
    
}
