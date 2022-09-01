package com.bsuir.health_analyzer.service;

import com.bsuir.health_analyzer.model.HealthModel;

import java.util.List;
import java.util.Set;

public interface IHealthService {
    void Save(HealthModel model);
    Set<String> GetAllNames();
    List<HealthModel> GetAllByName(String ip);

    HealthModel GetLast(String name);
}
