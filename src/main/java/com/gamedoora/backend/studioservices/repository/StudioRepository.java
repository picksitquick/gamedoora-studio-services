package com.gamedoora.backend.studioservices.repository;

import com.gamedoora.model.dao.Studios;
import com.gamedoora.model.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studios , Long> {

    List<Studios> findByName(String name);

    List<Studios> findByVisibility(boolean visibility);

    List<Studios> findByCommunity(int community);

    List<Studios> findByRegistration(boolean registration);

    List<Studios> findByUser(Users users);

}
