package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.exception.*;

import java.util.List;

public interface ISkillService {


    List<SkillDTO> findAllSkills() throws NoContentException, InternalException;
    SkillDTO createSkill(SkillDTO skillDTO) throws BadRequestException, InternalException;
    SkillDTO updateSkill(SkillDTO skillDTO) throws ConflictException, InternalException;
    SkillDTO findSkillById(Long skillId) throws NotFoundException, InternalException;
    List<SkillDTO> findSkillByStepId(Long stepId) throws NotFoundException, InternalException;
    List<ResourceDTO> findAllResource(Long skillId) throws NoContentException, InternalException;
    ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO)throws BadRequestException, InternalException;
    ResourceDTO findResourceById(Long skillId, Long resourceId) throws NotFoundException, InternalException;
    ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException, InternalException;
    void deleteSkill(Long id ) throws ConflictException, NotFoundException;
    void deleteResource(Long resourceId) throws ConflictException, NotFoundException;


}
