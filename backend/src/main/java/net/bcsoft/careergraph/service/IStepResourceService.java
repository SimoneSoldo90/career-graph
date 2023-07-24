package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;

import java.util.List;

public interface IStepResourceService {

    public ResourceDTO create(Integer stepId, ResourceDTO resourceDTO);
    public List<ResourceDTO> getAll(Integer stepId);
    public ResourceDTO getById(Integer stepId, Integer resourceId);
    public ResourceDTO update(Integer stepId, Integer resourceId, ResourceDTO resourceDTO);
    public ResourceDTO delete(Integer stepId, Integer resourceId);

}
