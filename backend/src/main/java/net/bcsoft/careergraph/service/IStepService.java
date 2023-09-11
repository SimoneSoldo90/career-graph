package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.StepDTO;

import java.util.List;

public interface IStepService {
    StepDTO create(StepDTO stepDTO);
    List<StepDTO> findAll();
    List <StepDTO> findByRoadmapId(Long roadmapId);
    StepDTO findById(Long stepId);
    StepDTO update(StepDTO stepDTO);
    //StepDTO delete(Long stepId);
}
