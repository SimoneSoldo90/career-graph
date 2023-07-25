package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.service.IRoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoadmapController {
    private final IRoadmapService roadmapService;

    public RoadmapController(IRoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping("/roadmaps/")
    public List <RoadmapDTO> getRoadmapList(){
        return roadmapService.getAll();
    }

    @PostMapping("/roadmaps/")
    public RoadmapDTO createRoadmap(@RequestBody RoadmapDTO roadmapDTO){
        return roadmapService.create(roadmapDTO);
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO getRoadmapId(@PathVariable Integer roadmapId){
        return roadmapService.getById(roadmapId);
    }

    @PutMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO updateRoadmap(@PathVariable Integer roadmapId, @RequestBody RoadmapDTO roadmapDTO){
        return roadmapService.update(roadmapId, roadmapDTO);
    }

    @DeleteMapping("/roadmaps/{roadmapId}")
    public void deleteRoadmap(@PathVariable Integer roadmapId){
        roadmapService.delete(roadmapId);
    }
}
