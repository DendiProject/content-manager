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
public class NodeTag implements Serializable {
    
    @Id
    @Column(name="node_tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nodeTagId;
    
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;
    
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
