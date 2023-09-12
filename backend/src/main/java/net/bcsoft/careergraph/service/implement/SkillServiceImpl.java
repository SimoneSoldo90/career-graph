package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.entity.StepSkill;
import net.bcsoft.careergraph.mapper.ResourceMapper;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.mapper.StepSkillMapper;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    }

    @Override
    public SkillDTO findById(Long skillId) {
        Skill result = skillMapper.findById(skillId);
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
        Skill skill = skillDTO.toEntity();
        skillMapper.insert(skill);
        Skill result = skillMapper.findById(skill.getId());
        return new SkillDTO(result.getId(), result.getTitle(), result.getDescription(), null);
    }


    @Override
    @Transactional
    public SkillDTO update(SkillDTO skillDTO) {
        Skill skill = skillDTO.toEntity();
        skillMapper.update(skill);
        return new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), null);
    }



    @Override
    public List<SkillDTO> findByStepId(Long stepId) {
        List<Skill> skillList = skillMapper.findByStepId(stepId);
        List<SkillDTO> result = new ArrayList<>();
        for (Skill skill : skillList) {
            result.add(new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription(), null));
        }
        return result;
    }

    @Override
    public List<ResourceDTO> findAllResource(Long skillId) {
        List<Resource> resourceList = resourceMapper.findBySkillId(skillId);
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for(Resource resource : resourceList){
            ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getStepId(),  resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
            resourceDTOList.add(resourceDTO);
        }
        return resourceDTOList;
    }

    @Override
    @Transactional
    public ResourceDTO createResource(Long skillId, ResourceDTO resourceDTO) {
        Resource resource = resourceDTO.toEntity();
        resourceMapper.insert(resource);
        Resource result = resourceMapper.findById(resource.getId());
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
    }

    @Override
    public ResourceDTO findResourceById(Long skillId, Long resourceId) {
        Resource result = resourceMapper.findById(resourceId);
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
    }

    @Override
    @Transactional
    public ResourceDTO updateResource(Long skillId, Long resourceId, ResourceDTO resourceDTO) {
        Resource resource = resourceDTO.toEntity();
        resourceMapper.update(resource);
        return new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getStepId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl());
    }
}
