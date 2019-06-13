package com.erickmob.xyinc.repository;

import com.erickmob.xyinc.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {

    Optional<Poi> findById(Long id);

}
