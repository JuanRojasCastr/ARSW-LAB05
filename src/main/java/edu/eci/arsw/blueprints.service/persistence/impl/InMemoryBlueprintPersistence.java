/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.service.persistence.impl;

import edu.eci.arsw.blueprints.service.model.Blueprint;
import edu.eci.arsw.blueprints.service.model.Point;
import edu.eci.arsw.blueprints.service.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.service.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.service.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String,String>, Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 10), new Point(10, 10),
                new Point(11, 10), new Point(15, 10), new Point(15, 10), new Point(0,0)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        Blueprint bp1=new Blueprint("Pedro","casa#38", pts1);
        Blueprint bp2=new Blueprint("Juan","casa#39", pts1);
        Blueprint bp3=new Blueprint("Pedro","casa#40", pts1);
        Blueprint bp4=new Blueprint("Pedro","casa#41", pts1);
        Blueprint bp5=new Blueprint("Juan","casa#42", pts1);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp1);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp2);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp3);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp4);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp5);

        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> authorBlueprints = new HashSet<>();
        for (Tuple<String,String> key: blueprints.keySet()){
            if(key.o1.equals(author)){
                authorBlueprints.add(getBlueprint(author, key.o2));
            }
        }
        return authorBlueprints;
    }


}
