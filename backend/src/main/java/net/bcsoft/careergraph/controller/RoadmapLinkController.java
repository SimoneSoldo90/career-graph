package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.service.IRoadmapLinkService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoadmapLinkController {
    private final IRoadmapLinkService roadmapLinkService;

    public RoadmapLinkController(IRoadmapLinkService roadmapLinkService) {
        this.roadmapLinkService = roadmapLinkService;
    }

    @PostMapping("/steps/{stepId}/roadmap-links/")
    public RoadmapLinkDTO createStepRoadmapLink(@PathVariable Integer stepId, @RequestBody RoadmapLinkDTO roadmapLinkDTO){
        return roadmapLinkService.create(stepId, roadmapLinkDTO);
    }

    @GetMapping("/steps/{stepId}/roadmap-links/")
    public List<RoadmapLinkDTO> getStepRoadmapLinkList(@PathVariable Integer stepId){
        return roadmapLinkService.getAll(stepId);
    }

    @GetMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    public RoadmapLinkDTO getStepRoadmapLinkById(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){
        return roadmapLinkService.getById(stepId, roadmapLinkId);
    }

    @PutMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    public RoadmapLinkDTO updateStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO){
        return roadmapLinkService.update(stepId, roadmapLinkId, roadmapLinkDTO);
    }

    @DeleteMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    public void deleteStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){
        roadmapLinkService.delete(stepId, roadmapLinkId);
    }
}
