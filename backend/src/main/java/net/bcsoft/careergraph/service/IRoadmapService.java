package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;

import java.util.List;

public interface IRoadmapService {
    public List<RoadmapDTO> findAll() throws NoContentException;
    public RoadmapDTO create(RoadmapDTO roadmapDTO) throws BadRequestException;
    public RoadmapDTO findById(Long roadmapId) throws NotFoundException;
    public RoadmapDTO update(RoadmapDTO roadmapDTO) throws ConflictException;
    public void deleteRoadmap(Long roadmapId) throws NotFoundException, ConflictException;

}
