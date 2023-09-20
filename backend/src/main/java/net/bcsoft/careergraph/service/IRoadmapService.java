package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.exception.*;

import java.util.List;

public interface IRoadmapService {
    List<RoadmapDTO> findAll() throws NoContentException, InternalException;
    RoadmapDTO create(RoadmapDTO roadmapDTO) throws BadRequestException, InternalException;
    RoadmapDTO findById(Long roadmapId) throws NotFoundException, InternalException;
    RoadmapDTO update(RoadmapDTO roadmapDTO) throws ConflictException, InternalException;
    void deleteRoadmap(Long roadmapId) throws NotFoundException, ConflictException;


}
