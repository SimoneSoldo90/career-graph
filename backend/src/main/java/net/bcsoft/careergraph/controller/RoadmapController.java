package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Roadmap;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.IRoadmapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoadmapController {
    private final IRoadmapService roadmapService;

    public RoadmapController(IRoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping("/roadmaps/")
    public ResponseEntity<List <RoadmapDTO>> getRoadmapList(){
        List <RoadmapDTO> roadmapDTOList = null;
        String sErrorMsg = "";
        try{
            roadmapDTOList =  roadmapService.findAll();
        }catch (NoContentException e){
            sErrorMsg = "Error getting list: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(roadmapDTOList != null){
            responseEntity = ResponseEntity.ok(roadmapDTOList);
        }else{
            responseEntity =  ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @PostMapping("/roadmaps/")
    public ResponseEntity <RoadmapDTO> createRoadmap(@RequestBody RoadmapDTO roadmapDTO){
        try{
            roadmapService.create(roadmapDTO);
        }catch(BadRequestException e){

        }
        return roadmapDTO
    }

    @GetMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO getRoadmapId(@PathVariable Long roadmapId){
        return roadmapService.findById(roadmapId);
    }

    @PutMapping("/roadmaps/{roadmapId}")
    public RoadmapDTO updateRoadmap(@RequestBody RoadmapDTO roadmapDTO){
        try{
            roadmapService.update(roadmapDTO);
        }catch (ConflictException e){

        }
        return roadmapDTO;
    }

    @DeleteMapping("/roadmaps/{roadmapId}")
    public void deleteRoadmap(@PathVariable Long roadmapId){
        roadmapService.delete(roadmapId);
    }
}
