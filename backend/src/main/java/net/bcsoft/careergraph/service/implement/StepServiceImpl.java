package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.RoadmapLink;
import net.bcsoft.careergraph.entity.Step;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
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
    RoadmapLinkMapper roadmapLinkMapper;
    ISkillService skillService;
    ResourceMapper resourceMapper;

    @Autowired
    public StepServiceImpl(StepMapper stepMapper, RoadmapLinkMapper roadmapLinkMapper, ISkillService skillService, ResourceMapper resourceMapper) {
        this.stepMapper = stepMapper;
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
    public StepDTO create(StepDTO stepDTO) throws BadRequestException {
        Step step = stepDTO.toEntity();
        stepMapper.insert(step);
        Step result = stepMapper.selectById(step.getId());
        if(result == null){
            throw new BadRequestException("step non creata");
        }
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    public List<StepDTO> findAll() throws NoContentException {
        List<Step> stepList = stepMapper.selectAll();
        List<StepDTO> stepDTOList = new ArrayList<>();
        if(stepDTOList == null){
            throw new NoContentException ("no step disponibili");
        }
        for (Step step : stepList) {
            List<ResourceDTO> resourceDTOList = findResourcesByStepId(step.getId());
            List<RoadmapLinkDTO> roadmapLinkDTOList = findRoadmapLinksByStepId(step.getId());
            List<SkillDTO> skillDTOList;
            try{
                skillDTOList = skillService.findSkillByStepId(step.getId());
            }catch (NotFoundException e){
             skillDTOList = new ArrayList<>();
            }
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public List<StepDTO> findByRoadmapId(Long roadmapId) throws NotFoundException, NoContentException {
        List<Step> stepList = stepMapper.findByRoadmapId(roadmapId);
        if(stepList == null){
            throw new NotFoundException ("step non trovata");
        }
        List<StepDTO> stepDTOList = new ArrayList<>();
        for(Step step : stepList){
            List<ResourceDTO> resourceDTOList = findResourcesByStepId(step.getId());
            List<RoadmapLinkDTO> roadmapLinkDTOList = findRoadmapLinksByStepId(step.getId());
            List<SkillDTO> skillDTOList = skillService.findSkillByStepId(step.getId());
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public StepDTO findById(Long stepId) throws NotFoundException{
        Step result = stepMapper.selectById(stepId);
        if(result == null){
            throw new NotFoundException("account con id = " + stepId + " non trovata");
        }
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    @Transactional
    public StepDTO update(StepDTO stepDTO) throws ConflictException {
        Step step = stepDTO.toEntity();
        Step result = stepMapper.selectById(step.getId());
        if(result == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        stepMapper.update(step);
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    //Resource
    @Override
    @Transactional
    public ResourceDTO createResource(Long stepId,ResourceDTO resourceDTO) throws BadRequestException {
        Resource resource = resourceDTO.toEntity();
        resourceMapper.insert(resource);
        Resource result = resourceMapper.selectById(resource.getId());
        if(result == null){
            throw new BadRequestException("resource non creata");
        }
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
    }

    @Override
    public List<ResourceDTO> findResourcesByStepId(Long stepId) throws NoContentException {
        List<Resource> resourceList = resourceMapper.selectByStepId(stepId);
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        if(resourceList == null){
            throw new NoContentException("no resource disponibili");
        }
        for (Resource resource : resourceList) {
            if(resource.getStepId().equals(stepId)){
                ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getUrl(), resource.getDescription());
                resourceDTOList.add(resourceDTO);
            }
        }

        return resourceDTOList;
    }

    @Override
    public ResourceDTO findByResourceId( Long resourceId) throws NotFoundException {
        Resource result = resourceMapper.selectById(resourceId);
        if(result == null){
            throw new NotFoundException("resource non trovata");
        }
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getUrl(), result.getDescription());
    }


    @Override
    public ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException {
        Resource resource = resourceDTO.toEntity();
        Resource result = resourceMapper.selectById(resourceDTO.id());
        resourceMapper.update(resource);
        return new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(),resource.getUrl(), resource.getDescription());
    }

    @Override
    public RoadmapLinkDTO createRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws BadRequestException {
        RoadmapLink roadmapLink = roadmapLinkDTO.toEntity();
        roadmapLinkMapper.insert(roadmapLink);
        RoadmapLink result = roadmapLinkMapper.selectById(roadmapLink.getId());
        if(result == null){
            throw new BadRequestException("roadmaplink non creata");
        }
        return new RoadmapLinkDTO(result.getId(), result.getStepId(), result.getRoadmapId());
    }

    @Override
    public List<RoadmapLinkDTO> findRoadmapLinksByStepId(Long stepId) throws NoContentException {
        List<RoadmapLink> roadmapLinkList = roadmapLinkMapper.selectByStepId(stepId);
        List<RoadmapLinkDTO> roadmapLinkDTOList = new ArrayList<>();
        if(roadmapLinkList == null){
            throw new NoContentException("no roadmaplink disponibili");
        }
        for (RoadmapLink roadmapLink : roadmapLinkList){
            RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(roadmapLink.getId(), roadmapLink.getStepId(), roadmapLink.getRoadmapId());
            roadmapLinkDTOList.add(roadmapLinkDTO);
        }
        return roadmapLinkDTOList;
    }

    @Override
    public RoadmapLinkDTO findByRoadmapLinkId(Long roadmapLinkId) throws NotFoundException {
        RoadmapLink result = roadmapLinkMapper.selectById(roadmapLinkId);
        if(result == null){
            throw new NotFoundException("roadmap_link non trovato");
        }
        return new RoadmapLinkDTO(result.getId(), result.getStepId(), result.getRoadmapId());
    }

    @Override
    public RoadmapLinkDTO updateRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws ConflictException {
        RoadmapLink roadmapLink = roadmapLinkDTO.toEntity();
        RoadmapLink roadmapLink1 = roadmapLinkMapper.selectById(roadmapLinkDTO.id());
        if(roadmapLink == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        roadmapLinkMapper.update(roadmapLink);
        return new RoadmapLinkDTO(roadmapLink.getId(), roadmapLink.getStepId(), roadmapLink.getRoadmapId());
    }

    public StepDTO delete(Long stepId) {
        System.out.println("Funziona!");
        return null;
    }
}
