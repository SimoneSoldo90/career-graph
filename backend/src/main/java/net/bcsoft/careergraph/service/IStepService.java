package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.StepDTO;

import java.util.List;

public interface IStepService {
    StepDTO create(StepDTO stepDTO);

    List<StepDTO> findAll();

    List<StepDTO> findByRoadmapId(Long roadmapId);

    StepDTO findById(Long stepId);

    StepDTO update(StepDTO stepDTO);

    //StepDTO delete(Long stepId);
    ResourceDTO createResource(Long stepId, ResourceDTO resourceDTO);

    List<ResourceDTO> findAllResource(Long stepId);

    ResourceDTO findByIdResource(Long stepId, Long resourceId);

    ResourceDTO updateResource(Integer stepId, Integer resourceId, ResourceDTO resourceDTO);
    RoadmapLinkDTO createRoadmapLink(Long stepId, RoadmapLinkDTO roadmapLinkDTO);
    List<RoadmapLinkDTO> findAllRoadmapLink(Long stepId);
    RoadmapLinkDTO findByIdRoadmapLink(Long stepId, Long roadmapId);
    RoadmapLinkDTO updateRoadmapLink(Long stepId, Long roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO);

}
