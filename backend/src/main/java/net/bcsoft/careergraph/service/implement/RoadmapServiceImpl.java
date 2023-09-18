package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.entity.Roadmap;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.mapper.RoadmapMapper;
import net.bcsoft.careergraph.service.IRoadmapService;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapServiceImpl implements IRoadmapService {
    RoadmapMapper roadmapMapper;
    IStepService stepService;

    /*
    {
    id: int, // non presente se POST request
    title: string,
    description: string,
	steps: [] // presente solo se GET response di /roadmaps/{roadmapId}
}
     */

    public RoadmapServiceImpl(RoadmapMapper roadmapMapper, IStepService stepService) {
        this.roadmapMapper = roadmapMapper;
        this.stepService = stepService;
    }

    @Override
    public RoadmapDTO findById(Long roadmapId) throws NotFoundException {
        Roadmap result = roadmapMapper.selectById(roadmapId);
        if (result == null) {
            throw new NotFoundException("roadmap non trovata");
        }
        List<StepDTO> stepDTOList;
        try {
            stepDTOList = stepService.findByRoadmapId(roadmapId);
        } catch (NoContentException e) {
            stepDTOList = new ArrayList<StepDTO>();
        }
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), stepDTOList);
    }

    @Override
    public List<RoadmapDTO> findAll() throws NoContentException {
        List<Roadmap> roadmapList = roadmapMapper.selectAll();
        List<RoadmapDTO> roadmapDTOList = new ArrayList<>();
        if (roadmapList == null) {
            throw new NoContentException("no roadmap disponibili");
        }
        for (Roadmap roadmap : roadmapList) {
            RoadmapDTO roadmapDTO = new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
            roadmapDTOList.add(roadmapDTO);
        }
        return roadmapDTOList;
    }

    @Override
    @Transactional
    public RoadmapDTO create(RoadmapDTO roadmapDTO) throws BadRequestException {
        Roadmap roadmap = roadmapDTO.toEntity();
        roadmapMapper.insert(roadmap);
        Roadmap result = roadmapMapper.selectById(roadmap.getId());
        if (result == null) {
            throw new BadRequestException("roadmap non creata");
        }
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), null);
    }

    @Override
    @Transactional
    public RoadmapDTO update(RoadmapDTO roadmapDTO) throws ConflictException {
        Roadmap roadmap = roadmapDTO.toEntity();
        Roadmap oldRoadmap = roadmapMapper.selectById(roadmapDTO.id());
        if (oldRoadmap == null) {
            throw new ConflictException("non e' stato possibile effettuare la modifica");
        }
        roadmapMapper.update(roadmap);
        return new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
    }

    @Override
    public void deleteRoadmap(Long roadmapId) throws NotFoundException, ConflictException{
        Roadmap result = roadmapMapper.selectById(roadmapId);
        if(result != null) {
            try {
                roadmapMapper.delete(roadmapId);
            }catch (RuntimeException e) {
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }
}
