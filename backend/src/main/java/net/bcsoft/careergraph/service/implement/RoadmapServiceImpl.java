package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.mapper.RoadmapMapper;
import net.bcsoft.careergraph.service.IRoadmapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapServiceImpl implements IRoadmapService {
    RoadmapMapper roadmapMapper;

    public RoadmapServiceImpl(RoadmapMapper roadmapMapper) {
        this.roadmapMapper = roadmapMapper;
    }

    @Override
    public List<RoadmapDTO> getAll() {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public RoadmapDTO create(RoadmapDTO roadmapDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public RoadmapDTO getById(Integer roadmapId) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public RoadmapDTO update(Integer roadmapId, RoadmapDTO roadmapDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public void delete(Integer roadmapId) {
        System.out.println("Funziona");
    }
}
