package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoadmapController {
    @GetMapping("/roadmaps/")
    public List <RoadmapDTO> getRoadmapList(){
        List <RoadmapDTO> roadmapDTOList = new ArrayList<>();
        return  roadmapDTOList;
    }

    @PostMapping("/roadmaps/")
    public RoadmapDTO createRoadmap(@RequestBody RoadmapDTO roadmapDTO){
        return roadmapDTO;
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO getRoadmapId(@PathVariable Integer roadmapId){
        RoadmapDTO roadmapDTO = new RoadmapDTO();
        return roadmapDTO;
    }

    @PutMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO updateRoadmap(@PathVariable Integer roadmapId, @RequestBody RoadmapDTO roadmapDTO){
        return roadmapDTO;
    }

    @DeleteMapping("/roadmaps/{roadmapId}")
    public void deleteRoadmap(@PathVariable Integer roadmapId){
    }
}
