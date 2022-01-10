package com.qnocks.universemanagesystem.repository;

import com.qnocks.universemanagesystem.domain.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
