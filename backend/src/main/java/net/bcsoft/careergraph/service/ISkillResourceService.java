package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;

import java.util.List;

public interface ISkillResourceService {

    public ResourceDTO create(Integer skillId, ResourceDTO resourceDTO);
    public List<ResourceDTO> getAll(Integer skillId);
    public ResourceDTO getById (Integer skillId, Integer resourceId);
    public ResourceDTO update(Integer skillId, Integer resourceId, ResourceDTO resourceDTO);
    public ResourceDTO delete(Integer skillId, Integer resourceId);

}
