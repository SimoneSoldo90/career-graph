package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;

import java.util.List;

public interface IRoadmapService {
    public List<RoadmapDTO> getAll();
    public RoadmapDTO create(RoadmapDTO roadmapDTO);
    public RoadmapDTO getById(Long roadmapId);
    public RoadmapDTO update(RoadmapDTO roadmapDTO);
    public void delete(Long roadmapId);

}
