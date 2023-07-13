package com.gamedoora.backend.studioservices.assembler;

import com.gamedoora.backend.studioservices.repository.StudioRepository;
import com.gamedoora.model.dao.Studios;
import com.gamedoora.model.dao.Users;
import com.gamedoora.model.dto.StudiosDTO;
import com.gamedoora.model.mapper.StudiosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//using @Component makes it more flexible for our changes, it includes @Service, but withe additional resources.
//use DTOs
@Service
public class StudioServicesAssembler {

    private StudioRepository studioRepository;

    private StudiosMapper studioMapper;

    @Autowired
    public void setStudioMapper(StudiosMapper studioMapper){
        this.studioMapper = studioMapper;
    }

    public StudiosMapper getStudioMapper(){
        return studioMapper;
    }

    @Autowired
    public void setStudioRepository(StudioRepository studioRepository){
        this.studioRepository = studioRepository;
    }

    public StudioRepository getStudioRepository(){
        return studioRepository;
    }

    public StudiosDTO createStudio(StudiosDTO studiosDto){
        Studios studios = studioMapper.studiosDtoToStudios(studiosDto);
        studioRepository.save(studios);
        return studiosDto;
    }

    public StudiosDTO updateStudio(long id , StudiosDTO studiosDto){

        Optional<Studios> studiosRes = studioRepository.findById(id);
        if(studiosRes.isEmpty()){
            return null;
        }
        Studios studioUpdate = studiosRes.get();
        studioUpdate.setName(studiosRes.get().getName());
        studioRepository.save(studioUpdate);

        return studiosDto;
    }

    public void deleteStudio(long id){
        studioRepository.deleteById(id);
    }

    public void deleteAllStudios(){
        studioRepository.deleteAll();
    }

    public List<StudiosDTO> getAllStudios(String name){
        List<StudiosDTO> studiosDto = new ArrayList<>();
        if (name == null) {
            studioRepository.findAll().forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        } else {
            studioRepository.findByName(name).forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        }
        return studiosDto.isEmpty() ? null : studiosDto;
    }

    public List<StudiosDTO> getAllStudiosByVisibility(boolean visible) {
        List<StudiosDTO> studiosDto = new ArrayList<>();
        if (visible) {
            studioRepository.findByVisibility(visible).forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        } else {
            studioRepository.findAll().forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        }
        return studiosDto.isEmpty() ? null : studiosDto;
    }

    public List<StudiosDTO> getAllStudiosByCommunity(int community) {
        List<StudiosDTO> studiosDto = new ArrayList<>();
        if (community == 1) {
            studioRepository.findByCommunity(community).forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        } else {
            studioRepository.findAll().forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        }
        return studiosDto.isEmpty() ? null : studiosDto;
    }

    public List<StudiosDTO> getAllStudiosByRegistration(boolean registration) {
        List<StudiosDTO> studiosDto = new ArrayList<>();
        if (registration) {
            studioRepository.findByRegistration(true).forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        } else {
            studioRepository.findAll().forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        }
        return studiosDto.isEmpty() ? null : studiosDto;
    }

    public List<StudiosDTO> getAllStudiosByUsers(Users users){
        List<StudiosDTO> studiosDto = new ArrayList<>();
        if (users != null) {
            studioRepository.findByUser(users).forEach(studio -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studio)));
        } else {
            studioRepository.findAll().forEach(studios -> studiosDto.add(getStudioMapper().studiosToStudiosDto(studios)));
        }
        return (studiosDto.isEmpty() ? null : studiosDto);
    }
}
