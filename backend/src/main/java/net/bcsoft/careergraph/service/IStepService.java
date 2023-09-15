package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;

import javax.naming.NotContextException;
import java.util.List;

public interface IStepService {
    StepDTO create(StepDTO stepDTO) throws BadRequestException;

    List<StepDTO> findAll() throws NoContentException;

    List<StepDTO> findByRoadmapId(Long roadmapId) throws NotFoundException, NoContentException;

    StepDTO findById(Long stepId) throws NotFoundException;

    StepDTO update(StepDTO stepDTO) throws ConflictException;

    StepDTO delete(Long stepId);
    ResourceDTO createResource(Long stepId, ResourceDTO resourceDTO) throws BadRequestException;

    List<ResourceDTO> findResourcesByStepId(Long stepId) throws NoContentException ;

    ResourceDTO findByResourceId(Long resourceId) throws NotFoundException;

    ResourceDTO updateResource(ResourceDTO resourceDTO) throws ConflictException;
    RoadmapLinkDTO createRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws BadRequestException;
    List<RoadmapLinkDTO> findRoadmapLinksByStepId(Long stepId) throws NoContentException;
    RoadmapLinkDTO findByRoadmapLinkId(Long roadmapLinkId) throws NotFoundException;
    RoadmapLinkDTO updateRoadmapLink(RoadmapLinkDTO roadmapLinkDTO) throws ConflictException;

}
