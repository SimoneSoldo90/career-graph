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

    List<StepDTO> findAll() throws NotFoundException, NoContentException;

    List<StepDTO> findByRoadmapId(Long roadmapId) throws NotFoundException, NoContentException;

    StepDTO findById(Long stepId) throws NotFoundException;

    StepDTO update(StepDTO stepDTO) throws ConflictException;

    //StepDTO delete(Long stepId);
    ResourceDTO createResource(Long stepId, ResourceDTO resourceDTO) throws BadRequestException;

    List<ResourceDTO> findAllResource(Long stepId) throws NoContentException ;

    ResourceDTO findByIdResource(Long stepId, Long resourceId) throws NotFoundException;

    ResourceDTO updateResource(Long stepId, Long resourceId, ResourceDTO resourceDTO) throws ConflictException;
    RoadmapLinkDTO createRoadmapLink(Long stepId, RoadmapLinkDTO roadmapLinkDTO) throws BadRequestException;
    List<RoadmapLinkDTO> findAllRoadmapLink(Long stepId) throws NoContentException;
    RoadmapLinkDTO findByIdRoadmapLink(Long stepId, Long roadmapId) throws NotFoundException;
    RoadmapLinkDTO updateRoadmapLink(Long stepId, Long roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO) throws ConflictException;

}
