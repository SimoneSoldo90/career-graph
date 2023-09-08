package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;

import java.util.List;

public interface IRoadmapService {
    public List<RoadmapDTO> findAll();
    public RoadmapDTO create(RoadmapDTO roadmapDTO);
    public RoadmapDTO findById(Long roadmapId);
    public RoadmapDTO update(RoadmapDTO roadmapDTO);
    public void delete(Long roadmapId);

}
