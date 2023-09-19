package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.exception.*;

import java.util.List;

public interface IStepService {
    StepDTO create(StepDTO stepDTO) throws BadRequestException, InternalException;

    List<StepDTO> findAll() throws NoContentException, InternalException;

    List<StepDTO> findByRoadmapId(Long roadmapId) throws NotFoundException, NoContentException, InternalException;

    StepDTO findById(Long stepId) throws NotFoundException, InternalException;

    StepDTO update(StepDTO stepDTO) throws ConflictException, InternalException;

    ResourceDTO createResource(Long stepId, ResourceDTO resourceDTO) throws BadRequestException, InternalException;


    List<ResourceDTO> findResourcesByStepId(Long stepId) throws NoContentException, InternalException;

    ResourceDTO findByResourceId(Long resourceId) throws NotFoundException, InternalException;

    ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException, InternalException;
    RoadmapLinkDTO createRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws BadRequestException, InternalException;
    List<RoadmapLinkDTO> findRoadmapLinksByStepId(Long stepId) throws NoContentException, InternalException;
    RoadmapLinkDTO findByRoadmapLinkId(Long roadmapLinkId) throws NotFoundException, InternalException;
    RoadmapLinkDTO updateRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws ConflictException, InternalException;
    void deleteStep(Long stepId) throws ConflictException, NotFoundException;
    void deleteRoadmapLink(Long roadMapLinkId) throws ConflictException, NotFoundException;
    void deleteResource(Long resourceId) throws ConflictException, NotFoundException;

}
