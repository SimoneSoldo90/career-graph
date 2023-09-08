package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.entity.Roadmap;
import net.bcsoft.careergraph.mapper.RoadmapMapper;
import net.bcsoft.careergraph.service.IRoadmapService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapServiceImpl implements IRoadmapService {
    RoadmapMapper roadmapMapper;

    public RoadmapServiceImpl(RoadmapMapper roadmapMapper) {
        this.roadmapMapper = roadmapMapper;
    }

    @Override
    public List<RoadmapDTO> findAll() {
        List <Roadmap> roadmapList = roadmapMapper.selectAll();
        List <RoadmapDTO> roadmapDTOList = new ArrayList<>();
        for(Roadmap roadmap : roadmapList){
            RoadmapDTO roadmapDTO = new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
            roadmapDTOList.add(roadmapDTO);
        }
        return roadmapDTOList;
    }

    @Override
    public RoadmapDTO create(RoadmapDTO roadmapDTO) {
        Roadmap roadmap = roadmapDTO.toEntity();
        roadmapMapper.insert(roadmap);
        Roadmap result = roadmapMapper.selectById(roadmap.getId());
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), null );
    }

    @Override
    public RoadmapDTO findById(Long roadmapId) {
        Roadmap result = roadmapMapper.selectById(roadmapId);
        return new RoadmapDTO(result.getId(), result.getTitle(), result.getDescription(), null);
    }

    @Override
    public RoadmapDTO update( RoadmapDTO roadmapDTO) {
        Roadmap roadmap = roadmapDTO.toEntity();
        roadmapMapper.update(roadmap);
        return new RoadmapDTO(roadmap.getId(), roadmap.getTitle(), roadmap.getDescription(), null);
    }

    @Override
    public void delete(Long roadmapId) {
        System.out.println("Funziona");
    }
}
