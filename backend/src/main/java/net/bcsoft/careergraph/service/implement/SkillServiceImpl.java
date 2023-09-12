package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.mapper.ResourceMapper;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.mapper.StepSkillMapper;
import net.bcsoft.careergraph.service.ISkillService;
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

    @Autowired
    public SkillServiceImpl(SkillMapper skillMapper, StepSkillMapper stepSkillMapper, ResourceMapper resourceMapper) {
        this.skillMapper = skillMapper;
        this.stepSkillMapper = stepSkillMapper;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public List<SkillDTO> findAll() {
        try{
            List<Skill> skillList = skillMapper.findAll();
            List<SkillDTO> skillDTOList = new ArrayList<>();
            List<ResourceDTO> resourceDTOList = new ArrayList<>();
            for(Skill skill : skillList){
                List<Resource> resourceList = resourceMapper.findBySkillId(skill.getId());
                for (Resource resource : resourceList){
                    ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
                    resourceDTOList.add(resourceDTO);
                }
                SkillDTO skillDTO = new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), resourceDTOList);
                skillDTOList.add(skillDTO);
            }
            return skillDTOList;
        }catch(BadRequestException e){
            throw e;
        }
    }

    @Override
    public SkillDTO findById(Long skillId) throws NotFoundException {
        Skill result = skillMapper.findById(skillId);
        if(result == null){
            throw new NotFoundException("skill con id = " + skillId + " non trovata");
        }
        List<Resource> resourceList= resourceMapper.findBySkillId(skillId);
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for(Resource resource : resourceList){
            ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
            resourceDTOList.add(resourceDTO);
        }
        return new SkillDTO(result.getId(), result.getTitle(), result.getDescription(), resourceDTOList);
    }

    @Override
    @Transactional
    public SkillDTO create(SkillDTO skillDTO) {
        try{
            Skill skill = skillDTO.toEntity();
            skillMapper.insert(skill);
            Skill result = skillMapper.findById(skill.getId());
            return new SkillDTO(result.getId(), result.getTitle(), result.getDescription(), null);
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    @Transactional
    public SkillDTO update(SkillDTO skillDTO) {
        try{
            Skill skill = skillDTO.toEntity();
            skillMapper.update(skill);
            return new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), null);
        }catch (Exception e){
            throw e;
        }
    }



    @Override
    public List<SkillDTO> findByStepId(Long stepId) {
        try{
            List<Skill> skillList = skillMapper.findByStepId(stepId);
            List<SkillDTO> result = new ArrayList<>();
            for (Skill skill : skillList) {
                result.add(new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), null));
            }
            return result;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<ResourceDTO> findAllResource(Long skillId) {
        try{
            List<Resource> resourceList = resourceMapper.findBySkillId(skillId);
            List<ResourceDTO> resourceDTOList = new ArrayList<>();
            for(Resource resource : resourceList){
                ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getStepId(),  resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
                resourceDTOList.add(resourceDTO);
            }
            return resourceDTOList;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO) {
        try {
            Resource resource = resourceDTO.toEntity();
            resourceMapper.insert(resource);
            Resource result = resourceMapper.findById(resource.getId());
            return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public ResourceDTO findResourceById(Long skillId, Long resourceId) {
        try{
            Resource result = resourceMapper.findById(resourceId);
            return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    @Transactional
    public ResourceDTO updateResource(Long skillId, Long resourceId, ResourceDTO resourceDTO) {
        try{
            Resource resource = resourceDTO.toEntity();
            resourceMapper.update(resource);
            return new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getStepId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
        } catch (Exception e){
            throw e;
        }
    }

}
