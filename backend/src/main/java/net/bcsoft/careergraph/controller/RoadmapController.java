package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public RoadmapDTO getRoadmapId(@PathVariable Integer id){
        RoadmapDTO roadmapDTO = new RoadmapDTO();
        return roadmapDTO;
    }

    @PutMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO updateRoadmap(@RequestBody RoadmapDTO roadmapDTO){
        return roadmapDTO;
    }

    @DeleteMapping("/roadmaps/{roadmapId}")
    public void deleteRoadmap(@PathVariable Integer id){

    }
}