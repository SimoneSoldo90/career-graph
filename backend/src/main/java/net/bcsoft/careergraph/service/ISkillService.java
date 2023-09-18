package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;

import java.util.List;

public interface ISkillService {

    List<SkillDTO> findAllSkills() throws NoContentException;
    SkillDTO createSkill(SkillDTO skillDTO) throws BadRequestException;
    SkillDTO updateSkill(SkillDTO skillDTO) throws ConflictException;
    SkillDTO findSkillById(Long skillId) throws NotFoundException;
    List<SkillDTO> findSkillByStepId(Long stepId) throws NotFoundException;
    List<ResourceDTO> findAllResource(Long skillId) throws NoContentException;
    ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO)throws BadRequestException;
    ResourceDTO findResourceById(Long skillId, Long resourceId) throws NotFoundException;
    ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException;
    void deleteSkill(Long id ) throws ConflictException, NotFoundException;
    void deleteResource(Long resourceId) throws ConflictException, NotFoundException;

}
