package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.StepDTO;

import java.util.List;

public interface IStepService {
    public StepDTO create(StepDTO stepDTO);
    public List<StepDTO> getAll();
    public StepDTO getById(Integer stepId);
    public StepDTO update(Integer stepId, StepDTO stepDTO);
    public StepDTO delete(Integer stepId);
}
