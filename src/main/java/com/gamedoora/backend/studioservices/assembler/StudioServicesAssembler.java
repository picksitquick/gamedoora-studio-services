package com.gamedoora.backend.studioservices.assembler;

import com.gamedoora.backend.studioservices.repository.StudioRepository;
import com.gamedoora.model.dao.Studios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudioServicesAssembler {


    private StudioRepository studioRepository;

    @Autowired
    public void setStudioRepository(StudioRepository studioRepository){
        this.studioRepository = studioRepository;
    }

    public StudioRepository getStudioRepository(){
        return studioRepository;
    }

    public Studios createStudio(Studios studios){
        studioRepository.save(studios);
        return studios;
    }

    public Studios updateStudio(long id , Studios studios){

        Optional<Studios> studiosRes = studioRepository.findById(id);
        if(studiosRes.isEmpty()){
            return null;
        }
        Studios studioUpdate = studiosRes.get();
        studioUpdate.setName(studiosRes.get().getName());
        studioRepository.save(studioUpdate);

        return studios;
    }

    public void deleteStudio(long id){
        studioRepository.deleteById(id);
    }

    public void deleteAllStudios(){
        studioRepository.deleteAll();
    }

    public List<Studios> getAllStudios(String name){
        List<Studios> studios = name == null ? studioRepository.findAll() : studioRepository.findByName(name);
        return studios.isEmpty() ? null : studios;
    }

}
