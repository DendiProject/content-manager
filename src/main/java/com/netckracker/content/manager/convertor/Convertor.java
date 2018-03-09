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

    public List<NodeDto> convertToDto(List <Node> nodes){        
        Type listType = new TypeToken<List <Node>>() {}.getType();
        List<NodeDto> nodeDto = new ArrayList<>();
        nodeDto=modelMapper.map(nodes, listType);
        return nodeDto;
    }
    
    public List<VerbDto> convertVerbToDto(List <Verb> verbs){        
        Type listType = new TypeToken<List <Verb>>() {}.getType();
        List<VerbDto> verbDto = modelMapper.map(verbs, listType);
        return verbDto;
    }
    
    public List<TagDto> convertTagToDto(List <Tag> tags){        
     Type listType = new TypeToken<List <Tag>>() {}.getType();
     List<TagDto> verbDto = modelMapper.map(tags, listType);
     return verbDto;
    } 
}
