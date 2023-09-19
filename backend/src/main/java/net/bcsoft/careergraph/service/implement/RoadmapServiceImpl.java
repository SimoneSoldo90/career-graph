package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.entity.Roadmap;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.entity.Step;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.mapper.RoadmapMapper;
import net.bcsoft.careergraph.service.IRoadmapService;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public RoadmapDTO findById(Long roadmapId) throws NotFoundException, InternalException {
        Roadmap result;
        try {
            result = roadmapMapper.selectById(roadmapId);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("roadmap non trovata");
        }
        List<StepDTO> stepDTOList;
        try {
            try{
                stepDTOList = stepService.findByRoadmapId(roadmapId);
            } catch(RuntimeException e) {
                throw new InternalException(e.getMessage());
            }
        } catch (NoContentException e) {
            stepDTOList = new ArrayList<StepDTO>();
        }
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), stepDTOList);
    }

    @Override
    public List<RoadmapDTO> findAll() throws NoContentException, InternalException {
        List <Roadmap> roadmapList;
        try {
            roadmapList = roadmapMapper.selectAll();
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        List <RoadmapDTO> roadmapDTOList = new ArrayList<>();
        if(roadmapList == null){
            throw new NoContentException("no roadmap disponibili");
        }
        for(Roadmap roadmap : roadmapList){
            RoadmapDTO roadmapDTO = new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
            roadmapDTOList.add(roadmapDTO);
        }
        return roadmapDTOList;
    }

    @Override
    @Transactional
    public RoadmapDTO create(RoadmapDTO roadmapDTO) throws BadRequestException, InternalException {
        Roadmap roadmap = roadmapDTO.toEntity();
        try {
            roadmapMapper.insert(roadmap);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        Roadmap result;
        try {
            result = roadmapMapper.selectById(roadmap.getId());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new BadRequestException("roadmap non creata");
        }
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), null );
    }



    @Override
    @Transactional
    public RoadmapDTO update( RoadmapDTO roadmapDTO) throws ConflictException, InternalException {
        Roadmap roadmap = roadmapDTO.toEntity();
        Roadmap oldRoadmap;
        try {
            oldRoadmap = roadmapMapper.selectById(roadmapDTO.id());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        if(oldRoadmap == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        try {
            roadmapMapper.update(roadmap);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        return new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
    }

    @Override
    public void delete(Long roadmapId) {
        System.out.println("Funziona");
    }
}
