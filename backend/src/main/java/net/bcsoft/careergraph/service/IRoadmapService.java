package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.exception.*;

import java.util.List;

public interface IRoadmapService {
    public List<RoadmapDTO> findAll() throws NoContentException, InternalException;
    public RoadmapDTO create(RoadmapDTO roadmapDTO) throws BadRequestException, InternalException;
    public RoadmapDTO findById(Long roadmapId) throws NotFoundException, InternalException;
    public RoadmapDTO update(RoadmapDTO roadmapDTO) throws ConflictException, InternalException;
    public void delete(Long roadmapId);


}
