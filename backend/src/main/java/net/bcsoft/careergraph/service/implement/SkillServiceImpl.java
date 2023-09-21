package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.mapper.ResourceMapper;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.mapper.StepSkillMapper;
import net.bcsoft.careergraph.service.ISkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService {


    /*
    ### JSON skill
{
    id: int, // non presente se POST request
	step_id: int,
    title: string,
    description: string,
	resources: [] // presente solo se GET response
}
     */

    SkillMapper skillMapper;
    StepSkillMapper stepSkillMapper;
    ResourceMapper resourceMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    public SkillServiceImpl(SkillMapper skillMapper, StepSkillMapper stepSkillMapper, ResourceMapper resourceMapper) {
        this.skillMapper = skillMapper;
        this.stepSkillMapper = stepSkillMapper;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public List<SkillDTO> findAllSkills() throws NoContentException, InternalException {
        List<Skill> skillList;
        try {
            skillList = skillMapper.findAll();
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(skillList == null){
            throw new NoContentException("skill non trovate");
        }
        List<SkillDTO> skillDTOList = new ArrayList<>();
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for(Skill skill : skillList){
            List<Resource> resourceList = resourceMapper.selectBySkillId(skill.getId());
            for (Resource resource : resourceList){
                ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
                resourceDTOList.add(resourceDTO);
            }
            SkillDTO skillDTO = new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), resourceDTOList);
            skillDTOList.add(skillDTO);
        }
        return skillDTOList;
    }

    @Override
    public SkillDTO findSkillById(Long skillId) throws NotFoundException, InternalException{

        Skill result;
        try {
            result = skillMapper.findById(skillId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("skill con id = " + skillId + " non trovata");
        }
        List<Resource> resourceList;
        try {
            resourceList = resourceMapper.selectBySkillId(skillId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for(Resource resource : resourceList){
            ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
            resourceDTOList.add(resourceDTO);
        }
        return new SkillDTO(result.getId(), result.getTitle(), result.getDescription(), resourceDTOList);
    }

    @Override
    @Transactional
    public SkillDTO createSkill(SkillDTO skillDTO) throws BadRequestException, InternalException{
        Skill skill = skillDTO.toEntity();
        try {
            skillMapper.insert(skill);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        Skill result;
        try {
            result = skillMapper.findById(skill.getId());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            LOGGER.warn("l'oggetto di tipo Skill non e' stato inserito correttamente");
            throw new BadRequestException("Skill non creata");
        }
        return new SkillDTO(result.getId(), result.getTitle(), result.getDescription(), null);
    }


    @Override
    @Transactional
    public SkillDTO updateSkill(SkillDTO skillDTO) throws ConflictException, InternalException{
        Skill skill = skillDTO.toEntity();
        Skill oldSkill;
        try {
            oldSkill = skillMapper.findById(skillDTO.id());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(oldSkill == null){
            LOGGER.warn("non e' stato possibile modificare l'oggetto di tipo Skill con id = " + skillDTO.id() + ", in quanto non e' stato trovato");
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        try {
            skillMapper.update(skill);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        return new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), null);

    }



    @Override
    public List<SkillDTO> findSkillByStepId(Long stepId) throws InternalException{
        List<Skill> skillList;
        try {
            skillList = skillMapper.findByStepId(stepId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        List<SkillDTO> result = new ArrayList<>();
        for (Skill skill : skillList) {
            result.add(new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), null));
        }
        return result;
    }

    @Override
    public List<ResourceDTO> findAllResource(Long skillId) throws NoContentException, InternalException{
        List<Resource> resourceList;
        try {
            resourceList = resourceMapper.selectBySkillId(skillId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        if(resourceList == null){
            throw new NoContentException("resource not find");
        }
        for(Resource resource : resourceList){
            ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getStepId(),  resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
            resourceDTOList.add(resourceDTO);
        }
        return resourceDTOList;
    }

    @Override
    @Transactional
    public ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO) throws BadRequestException, InternalException{
        Resource resource = resourceDTO.toEntity();
        try {
            resourceMapper.insert(resource);
        } catch(RuntimeException e) {
            LOGGER .error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        Resource result;
        try {
            result = resourceMapper.selectById(resource.getId());
        } catch(RuntimeException e) {
            LOGGER .error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            LOGGER.warn("l'oggetto di tipo Resource non e' stato inserito correttamente");
            throw new BadRequestException("resource not created");
        }
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
    }


    @Override
    public ResourceDTO findResourceById(Long skillId, Long resourceId) throws NotFoundException, InternalException{
        Resource result;
        try {
            result = resourceMapper.selectById(resourceId);
        } catch(RuntimeException e) {
            LOGGER .error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("resource not found");
        }
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
    }


    @Override
    @Transactional
    public ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException, InternalException{
        Resource oldResource;
        try {
            oldResource = resourceMapper.selectById(resourceDTO.id());
        } catch(RuntimeException e) {
            LOGGER .error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(oldResource == null){
            LOGGER.warn("non e' stato possibile modificare l'oggetto di tipo Resource con id =" + resourceDTO.id() + ", in quanto non e' stato trovato");
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
            Resource resource = resourceDTO.toEntity();
            try {
                resourceMapper.update(resource);
            } catch(RuntimeException e) {
                LOGGER .error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
            return new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getStepId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
    }

    @Override
    public void deleteSkill(Long id) throws ConflictException, NotFoundException {
        Skill result = skillMapper.findById(id);
        if(result != null) {
            try {
                skillMapper.delete(id);
            }catch (RuntimeException e) {
                LOGGER.warn("non e' stato possibile eliminare l'oggetto di tipo Skill con id = " + id + ", in quanto non e' stato trovato");
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }

    }

    @Override
    public void deleteResource(Long resourceId) throws ConflictException, NotFoundException {
        Resource result = resourceMapper.selectById(resourceId);
        if(result != null) {
            try {
                resourceMapper.delete(resourceId);
            }catch (RuntimeException e) {
                LOGGER.warn("non e' stato possibile eliminare l'oggetto di tipo Resource con id = " + resourceId + ", in quanto non e' stato trovato");
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }

}
