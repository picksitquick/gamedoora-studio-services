package com.gamedoora.backend.studioservices.repository;

import com.gamedoora.model.dao.Studios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studios , Long> {

    List<Studios> findByName(String name);

}
