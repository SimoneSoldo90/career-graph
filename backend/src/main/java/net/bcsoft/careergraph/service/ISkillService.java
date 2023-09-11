package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Resource;

import java.util.List;

public interface ISkillService {

    List<SkillDTO> findAll();
    SkillDTO create(SkillDTO skillDTO);
    SkillDTO update(SkillDTO skillDTO);
    SkillDTO findById(Long skillId);
    List<SkillDTO> findByStepId(Long stepId);
    List<Resource> findAllResource(Long skillId);
    Resource createResource(Long skillId, Resource resource);
    Resource findResourceById(Long skillId, Long resourceId);
    Resource updateResource(Long skillId, Long resourceId, Resource resource);

}
