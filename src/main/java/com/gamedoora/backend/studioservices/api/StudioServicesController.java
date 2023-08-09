package com.gamedoora.backend.studioservices.api;

import com.gamedoora.backend.studioservices.assembler.StudioServicesAssembler;
import com.gamedoora.backend.studioservices.exceptions.NotFoundException;
import com.gamedoora.model.dao.Studios;
import com.gamedoora.model.dao.Users;
import com.gamedoora.model.dto.StudiosDTO;
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
    public ResponseEntity<StudiosDTO> createStudio(@RequestBody StudiosDTO studiosDto) {
        return createResponse(studioServicesAssembler.createStudio(studiosDto), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StudiosDTO> updateStudios(
            @PathVariable("id") long id, @RequestBody StudiosDTO studiosDto) {
        StudiosDTO studioRes = studioServicesAssembler.updateStudio(id, studiosDto);
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
    public ResponseEntity<List<StudiosDTO>> getAllStudios(@RequestParam(required = false) String name) {
        return createResponse(studioServicesAssembler.getAllStudios(name) , HttpStatus.OK);
    }

    @GetMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudiosDTO>> getAllStudiosByVisibility(@RequestParam(required = false) boolean visible) {
        return createResponse(studioServicesAssembler.getAllStudiosByVisibility(visible), HttpStatus.OK);
    }

    @GetMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudiosDTO>> getAllStudiosByCommunity(@RequestParam(required = false) int community) {
        return createResponse(studioServicesAssembler.getAllStudiosByCommunity(community), HttpStatus.OK);
    }

    @GetMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudiosDTO>> getAllStudiosByRegistration(@RequestParam(required = false) boolean registration) {
        return createResponse(studioServicesAssembler.getAllStudiosByRegistration(registration), HttpStatus.OK);
    }

    @GetMapping(
            value = "/studio/users/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudiosDTO>> getAllStudiosByUsers(@RequestParam(required = false) long user_id) {
        return createResponse(studioServicesAssembler.getAllStudiosByUsers(user_id), HttpStatus.OK);
    }

    @GetMapping(
            value = "/studio/users/{name}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudiosDTO>> getAllStudiosByUsersFirstName(@RequestParam(required = false) String user_name) {
        return createResponse(studioServicesAssembler.getAllStudiosByUsersFirstName(user_name), HttpStatus.OK);
    }
}
