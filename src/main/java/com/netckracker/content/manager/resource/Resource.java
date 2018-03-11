/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.resource;

import java.util.List;

/**
 *
 * @author eliza
 */
public class Resource {
    private byte[] array;
    private String type;
    private  String userId;
    private int size;
    public Resource(byte[] array, String type, String userId, int size) {
        this.array = array;
        this.type = type;
        this.userId = userId;
        this.size = size;
    }
 
    
}
