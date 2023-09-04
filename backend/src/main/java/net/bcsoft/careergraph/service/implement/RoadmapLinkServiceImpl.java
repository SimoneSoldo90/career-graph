package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.service.IRoadmapLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapLinkServiceImpl implements IRoadmapLinkService {
    @Override
    public RoadmapLinkDTO create(Integer stepId, RoadmapLinkDTO roadmapLinkDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public List<RoadmapLinkDTO> getAll(Integer stepId) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public RoadmapLinkDTO getById(Integer stepId, Integer roadmapId) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public RoadmapLinkDTO update(Integer stepId, Integer roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public void delete(Integer stepId, Integer roadmapLinkId) {
        System.out.println("Funziona");
    }
}
