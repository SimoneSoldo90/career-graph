package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;

import java.util.List;

public interface ISkillService {

    List<SkillDTO> findAll() throws NoContentException;
    SkillDTO create(SkillDTO skillDTO) throws BadRequestException;
    SkillDTO update(SkillDTO skillDTO) throws ConflictException;
    SkillDTO findById(Long skillId) throws NotFoundException;
    List<SkillDTO> findByStepId(Long stepId) throws NotFoundException;
    List<ResourceDTO> findAllResource(Long skillId) throws NoContentException;
    ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO)throws BadRequestException;
    ResourceDTO findResourceById(Long skillId, Long resourceId) throws NotFoundException;
    ResourceDTO updateResource(Long skillId, Long resourceId, ResourceDTO resourceDTO) throws ConflictException;

}
