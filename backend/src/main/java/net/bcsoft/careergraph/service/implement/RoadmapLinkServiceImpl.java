package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.entity.RoadmapLink;
import net.bcsoft.careergraph.mapper.RoadmapLinkMapper;
import net.bcsoft.careergraph.service.IRoadmapLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapLinkServiceImpl implements IRoadmapLinkService {

    RoadmapLinkMapper roadmapLinkMapper;

    @Autowired
    public RoadmapLinkServiceImpl(RoadmapLinkMapper roadmapLinkMapper) {
        this.roadmapLinkMapper = roadmapLinkMapper;
    }

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

    public List<RoadmapLinkDTO> findByStepId(Long stepId){
        List<RoadmapLink> roadmapLinkList = roadmapLinkMapper.selectById(stepId);
        List<RoadmapLinkDTO> result = new ArrayList<>();
        for (RoadmapLink roadmapLink : roadmapLinkList) {
            result.add(new RoadmapLinkDTO(roadmapLink.getStepId(), roadmapLink.getRoadmapId()));
        }
        return result;
    }
}
