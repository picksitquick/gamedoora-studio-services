package com.gamedoora.backend.studioservices.api;

import com.gamedoora.backend.studioservices.assembler.StudioServicesAssembler;
import com.gamedoora.backend.studioservices.exceptions.NotFoundException;
import com.gamedoora.model.dao.Studios;
import com.gamedoora.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/studios")
public class StudioServicesController extends BaseController{

    private StudioServicesAssembler studioServicesAssembler;

    @Autowired
    public void setStudioServicesAssembler(StudioServicesAssembler studioServicesAssembler){
        this.studioServicesAssembler = studioServicesAssembler;
    }

    public StudioServicesAssembler getStudioServicesAssembler(){
        return studioServicesAssembler;
    }

    @PostMapping(
            value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Studios> createStudio(@RequestBody Studios studios) {
        return createResponse(studioServicesAssembler.createStudio(studios), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Studios> updateStudios(
            @PathVariable("id") long id, @RequestBody Studios studios) {
        Studios studioRes = studioServicesAssembler.updateStudio(id, studios);
        if (null == studioRes) {
            throw new NotFoundException(MessageFormat.format("User by id {0} not found", id));
        }
        return createResponse(studioRes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id") long id) {
        studioServicesAssembler.deleteStudio(id);
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        studioServicesAssembler.deleteAllStudios();
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Studios>> getAllStudios(@RequestParam(required = false) String name) {
        return createResponse(studioServicesAssembler.getAllStudios(name) , HttpStatus.OK);
    }

}
