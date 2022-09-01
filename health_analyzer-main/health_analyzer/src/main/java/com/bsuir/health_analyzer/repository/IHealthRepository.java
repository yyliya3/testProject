package com.bsuir.health_analyzer.repository;

import com.bsuir.health_analyzer.model.HealthModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IHealthRepository extends JpaRepository<HealthModel, Integer> {
    List<HealthModel> findByName(String name);

    @Query("select h.name from Health h")
    Set<String> GetAllNames();

    List<HealthModel> findByFilters(Integer cpu, Integer gpu, Integer ram);


}
