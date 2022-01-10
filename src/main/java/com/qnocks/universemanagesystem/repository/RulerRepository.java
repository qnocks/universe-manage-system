package com.qnocks.universemanagesystem.repository;

import com.qnocks.universemanagesystem.domain.Ruler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulerRepository extends JpaRepository<Ruler, Long> {

    List<Ruler> findAllByPlanetsIsNull();

    @Query(value = "SELECT * FROM ruler ORDER BY age LIMIT 10", nativeQuery = true)
    List<Ruler> findTop10Youngest();
}
