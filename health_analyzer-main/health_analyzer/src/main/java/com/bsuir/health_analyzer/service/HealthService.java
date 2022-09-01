package com.bsuir.health_analyzer.service;

import com.bsuir.health_analyzer.model.HealthModel;
import com.bsuir.health_analyzer.repository.IHealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class HealthService implements IHealthService {
    @Autowired
    private IHealthRepository healthRepository;

    public void Save(HealthModel model) { healthRepository.save(model); }

    public Set<String> GetAllNames() { return healthRepository.GetAllNames(); }

    public List<HealthModel> GetAllByName(String name) { return healthRepository.findByName(name); }

    public List<HealthModel> getClientsByFilters(int cpu, int gpu, int ram) { return healthRepository.findByFilters(cpu,gpu,ram); }

    @Override
    public HealthModel GetLast(String name) {
        List<HealthModel> analytics = GetAllByName(name);
        analytics.sort(Comparator.comparing(HealthModel::getTime).reversed());
        return analytics.get(0);
    }
}
