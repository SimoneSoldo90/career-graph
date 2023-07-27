package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;

import java.util.ArrayList;
import java.util.List;

public interface IRoadmapLinkService {
    public RoadmapLinkDTO create(Integer stepId, RoadmapLinkDTO roadmapLinkDTO);
    public List<RoadmapLinkDTO> getAll(Integer stepId);
    public RoadmapLinkDTO getById(Integer stepId, Integer roadmapId);
    public RoadmapLinkDTO update(Integer stepId, Integer roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO);
    public void delete(Integer stepId, Integer roadmapLinkId);
}
