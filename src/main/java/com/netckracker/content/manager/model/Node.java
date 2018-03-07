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
public class Node implements Serializable {    

    @Id   
    @Column(name = "node_id") 
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "node_name")   
    private String name;

    @Column(name = "node_source")   
    private String nodeSource;
    
    @Column(name = "check_sum")   
    private String checkSum;

    @Column(name = "user_id")   
    private String userId;
    
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private NodeType nodeType;
    
    
    @OneToMany(mappedBy="node")
    Set <MetaInformation> metaList;
    
    @OneToMany(mappedBy="node")
    Set <Tag> tagList; 
    
    @OneToMany(mappedBy="node")
    Set <Verb> verbList; 

    public String getNodeSource() {
        return nodeSource;
    }

    public void setNodeSource(String nodeSource) {
        this.nodeSource = nodeSource;
    }    
    
    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

   
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Set<MetaInformation> getMetaList() {
        return metaList;
    }

    public void setMetaList(Set<MetaInformation> metaList) {
        this.metaList = metaList;
    }

    public Set<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(Set<Tag> tagList) {
        this.tagList = tagList;
    }

    public Set<Verb> getVerbList() {
        return verbList;
    }

    public void setVerbList(Set<Verb> verbList) {
        this.verbList = verbList;
    }
    
}
