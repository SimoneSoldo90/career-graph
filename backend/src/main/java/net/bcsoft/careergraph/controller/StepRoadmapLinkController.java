package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
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
public class StepRoadmapLinkController {
    @PostMapping("/steps/{stepId}/roadmap-links/\n")
    RoadmapLinkDTO createStepRoadmapLink(@PathVariable Integer stepId, @RequestBody RoadmapLinkDTO roadmapLinkDTO){
        return new RoadmapLinkDTO(); // Implementare
    }

    @GetMapping("/steps/{stepId}/roadmap-links/\n")
    List<RoadmapLinkDTO> getStepRoadmapLink(@PathVariable Integer stepId){
        return new ArrayList<RoadmapLinkDTO>(); // Implementare
    }

    @GetMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    RoadmapLinkDTO getStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){
        return new RoadmapLinkDTO(); // Implementare
    }

    @PutMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    void updateStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){
        new RoadmapLinkDTO(); // Implementare
    }

    @DeleteMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    void deleteStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){
        new RoadmapLinkDTO(); // Implements
    }
}
