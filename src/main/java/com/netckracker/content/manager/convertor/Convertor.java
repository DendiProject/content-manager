/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.convertor;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.TagDto;
import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.model.VerbDto;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static jdk.nashorn.internal.objects.NativeDebug.map;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliza
 */
@Component
public class Convertor { 

@Autowired
private ModelMapper modelMapper;

    public NodeDto convertNodeToDto(Node node) {
    NodeDto nodeDto = modelMapper.map(node, NodeDto.class);
    return nodeDto;        
    }
    public TagDto convertTagToDto (Tag tag){
    TagDto tagDto = modelMapper.map(tag, TagDto.class);
    return tagDto;        
    }
    public VerbDto convertVerbToDto (Verb verb){
    VerbDto verbDto = modelMapper.map(verb, VerbDto.class);
    return verbDto;        
    }
            
}
