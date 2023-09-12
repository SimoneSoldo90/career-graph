package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;

import java.util.List;

public interface ISkillService {

    List<SkillDTO> findAll();
    SkillDTO create(SkillDTO skillDTO);
    SkillDTO update(SkillDTO skillDTO);
    SkillDTO findById(Long skillId);
    List<SkillDTO> findByStepId(Long stepId);
    List<ResourceDTO> findAllResource(Long skillId);
    ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO);
    ResourceDTO findResourceById(Long skillId, Long resourceId);
    ResourceDTO updateResource(Long skillId, Long resourceId, ResourceDTO resourceDTO);

}
