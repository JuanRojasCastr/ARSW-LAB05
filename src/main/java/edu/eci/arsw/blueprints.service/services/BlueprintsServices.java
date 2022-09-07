/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.service.services;


import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import edu.eci.arsw.blueprints.service.filters.Filter;
import edu.eci.arsw.blueprints.service.model.Blueprint;
import edu.eci.arsw.blueprints.service.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.service.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp=null;
    @Autowired
    Filter bpf;
    
    public void addNewBlueprint(Blueprint bp){
        try {
            bpp.saveBlueprint(bp);
        }catch (Exception e){
            throw new UnsupportedOperationException("Error with the operation on services.");
        }
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return null;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException {
        Blueprint blueprint;
        try {
             blueprint = bpp.getBlueprint(author, name);
             blueprint =  bpf.filterPoints(blueprint);
        }catch (Exception e){
            throw new UnsupportedOperationException("Error with the operation on services.");
        }
        return blueprint;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> blueprints;
        Set<Blueprint> blueprintsFiltered = new HashSet<>();
        try {
            blueprints = bpp.getBlueprintsByAuthor(author);
            for(Blueprint bp: blueprints){
                bp = bpf.filterPoints(bp);
                blueprintsFiltered.add(bp);
            }
        }catch (BlueprintNotFoundException e){
            throw e;
        }
        return blueprintsFiltered;
    }

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthorBpName(String author, String bpname) throws BlueprintNotFoundException{
        Set<Blueprint> blueprints;
        Set<Blueprint> blueprintsFiltered = new HashSet<>();
        try {
            System.out.println(bpname);
            blueprints = bpp.getBlueprintsByAuthor(author);
            for(Blueprint bp: blueprints){
                System.out.println(bp.getName());
                if (bp.getName().equals(bpname)) {
                    bp = bpf.filterPoints(bp);
                    blueprintsFiltered.add(bp);
                }
            }
        }catch (BlueprintNotFoundException e){
            throw e;
        }
        return blueprintsFiltered;
    }
    
}
