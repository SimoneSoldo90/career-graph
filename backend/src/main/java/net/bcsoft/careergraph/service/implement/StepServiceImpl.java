package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.RoadmapLink;
import net.bcsoft.careergraph.entity.Step;
import net.bcsoft.careergraph.mapper.ResourceMapper;
import net.bcsoft.careergraph.mapper.RoadmapLinkMapper;
import net.bcsoft.careergraph.mapper.StepMapper;
import net.bcsoft.careergraph.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StepServiceImpl implements IStepService {

    StepMapper stepMapper;
    IResourceService resourceService;
    RoadmapLinkMapper roadmapLinkMapper;
    ISkillService skillService;
    ResourceMapper resourceMapper;

    @Autowired
    public StepServiceImpl(StepMapper stepMapper, IResourceService resourceService, RoadmapLinkMapper roadmapLinkMapper, ISkillService skillService, ResourceMapper resourceMapper) {
        this.stepMapper = stepMapper;
        this.resourceService = resourceService;
        this.roadmapLinkMapper = roadmapLinkMapper;
        this.skillService = skillService;
        this.resourceMapper = resourceMapper;
    }

    /** JSON step
    {
        id: int, // non presente se create
        roadmap_id: int,
        order: int,
        title: string,
        description: string,
        resources: [], // presente solo se find
        roadmap_links: [], // presente solo se find
        skills: [] // presente solo se find
    }
     */


    @Override
    @Transactional
    public StepDTO create(StepDTO stepDTO) {
        Step step = stepDTO.toEntity();
        stepMapper.insert(step);
        Step result = stepMapper.selectById(step.getId());
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    public List<StepDTO> findAll() {
        List<Step> stepList = stepMapper.selectAll();
        List<StepDTO> stepDTOList = new ArrayList<>();
        for (Step step : stepList) {
            List<ResourceDTO> resourceDTOList = resourceService.findByStepId(step.getId());
            List<RoadmapLinkDTO> roadmapLinkDTOList = findAllRoadmapLink(step.getId());
            List<SkillDTO> skillDTOList = skillService.findByStepId(step.getId());
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public List<StepDTO> findByRoadmapId(Long roadmapId) {
        List<Step> stepList = stepMapper.findByRoadmapId(roadmapId);
        List<StepDTO> stepDTOList = new ArrayList<>();
        for(Step step : stepList){
            List<ResourceDTO> resourceDTOList = resourceService.findByStepId(step.getId());
            List<RoadmapLinkDTO> roadmapLinkDTOList = findAllRoadmapLink(step.getId());
            List<SkillDTO> skillDTOList = skillService.findByStepId(step.getId());
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public StepDTO findById(Long stepId) {
        Step result = stepMapper.selectById(stepId);
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    @Transactional
    public StepDTO update(StepDTO stepDTO) {
        Step step = stepDTO.toEntity();
        stepMapper.update(step);
        Step result = stepMapper.selectById(step.getId());
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    //Resource
    @Override
    public ResourceDTO createResource(Long stepId,ResourceDTO resourceDTO) {
        Resource resource = resourceDTO.toEntity();
        resourceMapper.insert(resource);
        Resource result = resourceMapper.selectById(resource.getId());
        return new ResourceDTO(result.getId(), result.getSkillId(), result.getResourceTypeId(), result.getUrl(), result.getDescription());
    }

    @Override
    public List<ResourceDTO> findAllResource() {
        List<Resource> resourceList = resourceMapper.selectAll();
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for (Resource resource : resourceList) {
            ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getUrl(), resource.getDescription());
            resourceDTOList.add(resourceDTO);
        }

        return resourceDTOList;
    }

    @Override
    public ResourceDTO findByIdResource(Long stepId, Long resourceId) {
        Resource result = resourceMapper.selectById(resourceId);
        return new ResourceDTO(result.getId(), result.getSkillId(), result.getResourceTypeId(), result.getUrl(), result.getDescription());
    }


    @Override
    public ResourceDTO updateResource(Integer stepId, Integer resourceId, ResourceDTO resourceDTO) {
        Resource resource = resourceDTO.toEntity();
        resourceMapper.update(resource);
        return new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getResourceTypeId(),resource.getUrl(), resource.getDescription());
    }

    @Override
    public RoadmapLinkDTO createRoadmapLink(Long stepId, RoadmapLinkDTO roadmapLinkDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public List<RoadmapLinkDTO> findAllRoadmapLink(Long stepId) {
        return null;
    }

    @Override
    public RoadmapLinkDTO findByIdRoadmapLink(Long stepId, Long roadmapId) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public RoadmapLinkDTO updateRoadmapLink(Long stepId, Long roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO) {
        System.out.println("Funziona");
        return null;
    }

    public StepDTO delete(Integer stepId) {
        System.out.println("Funziona!");
        return null;
    }
}
