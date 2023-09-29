package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.entity.Roadmap;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.mapper.RoadmapMapper;
import net.bcsoft.careergraph.service.IRoadmapService;
import net.bcsoft.careergraph.service.IStepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoadmapServiceImpl implements IRoadmapService {

    private final Logger LOGGER = LoggerFactory.getLogger(RoadmapServiceImpl.class);
    RoadmapMapper roadmapMapper;
    IStepService stepService;
    private final Logger LOGGER = LoggerFactory.getLogger(RoadmapServiceImpl.class);

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
            LOGGER.error(e.getMessage(), e);
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
                LOGGER.error(e.getMessage(), e);
                throw new InternalException(e.getMessage());
            }
        } catch (NoContentException e) {
            stepDTOList = new ArrayList<>();
        }
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), stepDTOList);
    }

    @Override
    public List<RoadmapDTO> findAll() throws NoContentException, InternalException {
        List <Roadmap> roadmapList;
        try {
            roadmapList = roadmapMapper.selectAll();
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        List <RoadmapDTO> roadmapDTOList = new ArrayList<>();
        if(roadmapList == null){
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
    public RoadmapDTO create(RoadmapDTO roadmapDTO) throws BadRequestException, InternalException {
        Roadmap roadmap = roadmapDTO.toEntity();
        try {
            roadmapMapper.insert(roadmap);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        Roadmap result;
        try {
            result = roadmapMapper.selectById(roadmap.getId());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            LOGGER.warn("L'oggetto di tipo Roadmap non è stato inserito correttamente");
            throw new BadRequestException("Errore di creazione, inseriti dati non corretti");
        }
        LOGGER.info("creata roadmap con id " + result.getId());
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), null);
    }

    @Override
    @Transactional
    public RoadmapDTO update( RoadmapDTO roadmapDTO) throws ConflictException, InternalException {
        Roadmap roadmap = roadmapDTO.toEntity();
        Roadmap oldRoadmap;
        try {
            oldRoadmap = roadmapMapper.selectById(roadmapDTO.id());
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        if(oldRoadmap == null){
            LOGGER.warn("Non è stato possibile aggiornare l'oggetto di tipo Roadmap con id" + roadmapDTO.id() + "poiché non esistente");
            throw new ConflictException("non e' stato possibile effettuare la modifica");
        }
        try {
            roadmapMapper.update(roadmap);
        } catch(RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InternalException(e.getMessage());
        }
        LOGGER.info("update roadmap con id " + roadmap.getId());
        return new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
    }

    @Override
    public void deleteRoadmap(Long roadmapId) throws NotFoundException, ConflictException{
        Roadmap result = roadmapMapper.selectById(roadmapId);
        if(result != null) {
            try {
                roadmapMapper.delete(roadmapId);
                LOGGER.info("creata roadmap con id " + result.getId());
            }catch (RuntimeException e) {
                LOGGER.warn("Non è stato possibile eliminare la Roadmap con id: " + roadmapId );
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }
}
