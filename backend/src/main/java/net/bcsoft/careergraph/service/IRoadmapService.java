package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;

import java.util.List;

public interface IRoadmapService {
    public List<RoadmapDTO> getAll();
    public RoadmapDTO create(RoadmapDTO roadmapDTO);
    public RoadmapDTO getById(Integer roadmapId);
    public RoadmapDTO update(Integer roadmapId, RoadmapDTO roadmapDTO);
    public void delete(Integer roadmapId);

}
