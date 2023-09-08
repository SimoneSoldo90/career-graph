package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapLinkDTO;

import java.util.List;

public interface IRoadmapLinkService {
    RoadmapLinkDTO create(Integer stepId, RoadmapLinkDTO roadmapLinkDTO);
    List<RoadmapLinkDTO> getAll(Integer stepId);
    RoadmapLinkDTO getById(Integer stepId, Integer roadmapId);
    RoadmapLinkDTO update(Integer stepId, Integer roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO);
    List<RoadmapLinkDTO> findByStepId(Long stepId);
    void delete(Integer stepId, Integer roadmapLinkId);
}
