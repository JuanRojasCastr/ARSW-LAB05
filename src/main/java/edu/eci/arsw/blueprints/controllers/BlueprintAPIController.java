/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import edu.eci.arsw.blueprints.service.model.Blueprint;
import edu.eci.arsw.blueprints.service.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.service.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
        @Autowired
        BlueprintsServices bps = null;
        @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE )
        public ResponseEntity<?> manejadorGetRecursoBlueprint(){
            Set<Blueprint> bp = bps.getAllBlueprints();
            return new ResponseEntity<>(new Gson().toJson(bp),HttpStatus.OK);
        }
}

