package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.*;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Roadmap;
import net.bcsoft.careergraph.entity.RoadmapLink;
import net.bcsoft.careergraph.entity.Step;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.mapper.ResourceMapper;
import net.bcsoft.careergraph.mapper.RoadmapLinkMapper;
import net.bcsoft.careergraph.mapper.RoadmapMapper;
import net.bcsoft.careergraph.mapper.StepMapper;
import net.bcsoft.careergraph.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StepServiceImpl implements IStepService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    StepMapper stepMapper;
    RoadmapLinkMapper roadmapLinkMapper;
    ISkillService skillService;
    ResourceMapper resourceMapper;
    RoadmapMapper roadmapMapper;

    @Autowired
    public StepServiceImpl(StepMapper stepMapper, RoadmapLinkMapper roadmapLinkMapper, ISkillService skillService, ResourceMapper resourceMapper, RoadmapMapper roadmapMapper) {
        this.stepMapper = stepMapper;
        this.roadmapLinkMapper = roadmapLinkMapper;
        this.skillService = skillService;
        this.resourceMapper = resourceMapper;
        this.roadmapMapper = roadmapMapper;
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
    public StepDTO create(StepDTO stepDTO) throws BadRequestException, InternalException {
        Step step = stepDTO.toEntity();
        try {
            stepMapper.insert(step);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        Step result;
        try {
            result = stepMapper.selectById(step.getId());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new BadRequestException("step non creata");
        }
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    public List<StepDTO> findAll() throws NoContentException, InternalException{
        List<Step> stepList;
        try {
            stepList = stepMapper.selectAll();
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        List<StepDTO> stepDTOList = new ArrayList<>();
        if(stepDTOList == null){
            throw new NoContentException ("no step disponibili");
        }
        for (Step step : stepList) {
            List<ResourceDTO> resourceDTOList;
            try {
                resourceDTOList = findResourcesByStepId(step.getId());
            } catch(RuntimeException e) {
                LOGGER.error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
            List<RoadmapLinkDTO> roadmapLinkDTOList;
            try {
                roadmapLinkDTOList = findAllRoadmapLink(step.getId());
            } catch(RuntimeException e) {
                LOGGER.error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
            List<SkillDTO> skillDTOList;
            try{
                try {
                    skillDTOList = skillService.findSkillByStepId(step.getId());
                } catch(RuntimeException e) {
                    LOGGER.error(e.getMessage(), e);
                    throw new InternalException(e.getMessage());
                }
            }catch (NotFoundException e){
             skillDTOList = new ArrayList<>();
            }
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public List<StepDTO> findByRoadmapId(Long roadmapId) throws NotFoundException, NoContentException, InternalException {
        List<Step> stepList;
        try {
            stepList = stepMapper.findByRoadmapId(roadmapId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(stepList == null){
            throw new NotFoundException ("step non trovata");
        }
        List<StepDTO> stepDTOList = new ArrayList<>();
        for(Step step : stepList){
            List<ResourceDTO> resourceDTOList;
            try {
                resourceDTOList = findResourcesByStepId(step.getId());
            } catch(RuntimeException e) {
                LOGGER.error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
            List<RoadmapLinkDTO> roadmapLinkDTOList;
            try {
                roadmapLinkDTOList = findAllRoadmapLink(step.getId());
            } catch(RuntimeException e) {
                LOGGER.error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
            List<SkillDTO> skillDTOList;
            try {
                skillDTOList = skillService.findSkillByStepId(step.getId());
            } catch(RuntimeException e) {
                LOGGER.error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public StepDTO findById(Long stepId) throws NotFoundException, InternalException{
        Step result;
        try {
            result = stepMapper.selectById(stepId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("account con id = " + stepId + " non trovata");
        }
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    @Transactional
    public StepDTO update(StepDTO stepDTO) throws ConflictException, InternalException {
        Step step = stepDTO.toEntity();
        Step result;
        try {
            result = stepMapper.selectById(step.getId());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        try {
            stepMapper.update(step);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    //Resource
    @Override
    @Transactional
    public ResourceDTO createResource(Long stepId,ResourceDTO resourceDTO) throws BadRequestException, InternalException {
        Resource resource = resourceDTO.toEntity();
        try {
            resourceMapper.insert(resource);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        Resource result;
        try {
            result = resourceMapper.selectById(resource.getId());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new BadRequestException("resource non creata");
        }
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getDescription(), result.getUrl());
    }

    @Override
    public List<ResourceDTO> findResourcesByStepId(Long stepId) throws NoContentException, InternalException {
        List<Resource> resourceList;
        try {
            resourceList = resourceMapper.selectResourcesByStepId(stepId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
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
    public ResourceDTO findByResourceId( Long resourceId) throws NotFoundException, InternalException {
        Resource result;
        try {
            result = resourceMapper.selectById(resourceId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("resource non trovata");
        }
        return new ResourceDTO(result.getId(), result.getStepId(), result.getSkillId(), result.getResourceTypeId(), result.getUrl(), result.getDescription());
    }


    @Override
    public ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException, InternalException {
        Resource resource = resourceDTO.toEntity();
        Resource result;
        try {
            result = resourceMapper.selectById(resourceDTO.id());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        try {
            resourceMapper.update(resource);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        return new ResourceDTO(resource.getId(), resource.getStepId(), resource.getSkillId(), resource.getResourceTypeId(),resource.getUrl(), resource.getDescription());
    }

    @Override
    public RoadmapLinkDTO createRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws BadRequestException, InternalException {
        RoadmapLink roadmapLink = roadmapLinkDTO.toEntity();
        try {
            roadmapLinkMapper.insert(roadmapLink);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        RoadmapLink result;
        try {
            result = roadmapLinkMapper.selectById(roadmapLink.getId());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new BadRequestException("roadmaplink non creata");
        }
        return new RoadmapLinkDTO(result.getId(), result.getStepId(), result.getRoadmapId(), null, null);
    }

    @Override
    public List<RoadmapLinkDTO> findAllRoadmapLink(Long stepId) throws NoContentException, InternalException {
        List<RoadmapLink> roadmapLinkList;
        try {
            roadmapLinkList = roadmapLinkMapper.selectAll();
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        List<RoadmapLinkDTO> roadmapLinkDTOList = new ArrayList<>();
        if(roadmapLinkList == null){
            throw new NoContentException("no roadmaplink disponibili");
        }
        for (RoadmapLink roadmapLink : roadmapLinkList){
            Roadmap roadmap = roadmapMapper.selectById(roadmapLink.getRoadmapId());
            RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(roadmapLink.getId(), roadmapLink.getStepId(), roadmapLink.getRoadmapId(), roadmap.getTitle(), roadmap.getDescription());
            roadmapLinkDTOList.add(roadmapLinkDTO);
        }
        return roadmapLinkDTOList;
    }

    @Override
    public RoadmapLinkDTO findByRoadmapLinkId(Long roadmapLinkId) throws NotFoundException, InternalException {
        RoadmapLink result;
        try {
            result = roadmapLinkMapper.selectById(roadmapLinkId);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("roadmap_link non trovato");
        }
        Roadmap roadmap = roadmapMapper.selectById(result.getRoadmapId());
        return new RoadmapLinkDTO(result.getId(), result.getStepId(), result.getRoadmapId(), roadmap.getTitle(), roadmap.getDescription());
    }

    @Override
    public RoadmapLinkDTO updateRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws ConflictException, InternalException {
        RoadmapLink roadmapLink;
        try {
            roadmapLink = roadmapLinkDTO.toEntity();
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        RoadmapLink roadmapLink1;
        try {
            roadmapLinkMapper.selectById(roadmapLinkDTO.id());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(roadmapLink == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        try {
            roadmapLinkMapper.update(roadmapLink);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        return new RoadmapLinkDTO(roadmapLink.getId(), roadmapLink.getStepId(), roadmapLink.getRoadmapId(), null, null);
    }

    @Override
    public void deleteStep(Long stepId) throws ConflictException, NotFoundException {
        Step result = stepMapper.selectById(stepId);
        if(result != null) {
            try {
                stepMapper.delete(stepId);
            }catch (RuntimeException e) {
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }


    @Override
    public void deleteRoadmapLink(Long roadMapLinkId) throws ConflictException, NotFoundException {
        RoadmapLink result = roadmapLinkMapper.selectById(roadMapLinkId);
        if(result != null) {
            try {
                roadmapLinkMapper.delete(roadMapLinkId);
            }catch (RuntimeException e) {
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
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }
}

