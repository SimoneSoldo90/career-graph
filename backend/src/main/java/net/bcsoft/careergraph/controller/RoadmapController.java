package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.IRoadmapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.bcsoft.careergraph.exception.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RoadmapController {
    private final IRoadmapService roadmapService;

    public RoadmapController(IRoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping("/roadmaps/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List <RoadmapDTO>> findRoadmaps(){
        List <RoadmapDTO> roadmapDTOList = null;
        String sErrorMsg = "";
        try{
            roadmapDTOList =  roadmapService.findAll();
        }catch (NoContentException | InternalException e){
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
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <RoadmapDTO> createRoadmap(@RequestBody RoadmapDTO roadmapDTO){
        RoadmapDTO roadmapDTO1 = null;
        String sErrorMsg = "";
        ResponseEntity responseEntity = null;
        try{
            roadmapDTO1 = roadmapService.create(roadmapDTO);
        }catch(BadRequestException | InternalException e){
            sErrorMsg = "Error creating roadmap: " + e.getMessage();
        }

        if(roadmapDTO1 != null){
            try {
                responseEntity = ResponseEntity.created(new URI("/roadmaps/" + roadmapDTO1.id())).body(roadmapDTO1);
            } catch (URISyntaxException e) {
                responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
                //qui si potrebbe mettere il log
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsg);
        }
        return responseEntity;
    }

    @GetMapping("/roadmaps/{roadmapId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <RoadmapDTO> findRoadmapById(@PathVariable Long roadmapId){
        RoadmapDTO roadmapDTO = null;
        String sErrorMsg = "";
        try{
            roadmapDTO =  roadmapService.findById(roadmapId);
        }catch (NotFoundException | InternalException e){
            sErrorMsg = "Error getting roadmap: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(roadmapDTO != null){
            responseEntity = ResponseEntity.ok(roadmapDTO);
        }else{
            responseEntity =  ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/roadmaps/{roadmapId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <RoadmapDTO> updateRoadmap(@PathVariable Long roadmapId ,@RequestBody RoadmapDTO roadmapDTO){
        RoadmapDTO roadmapDTO1 = null;
        String sErrorMsg = "";

        if(roadmapId != roadmapDTO.id()){
            sErrorMsg= "ids in the roadmap mismatch the ones in the request body";
        }else{
            try{
                roadmapDTO1 = roadmapService.update(roadmapDTO);
            }catch (ConflictException | InternalException e){
                sErrorMsg = "error updating roadmap" + e.getMessage();
            }

        }
        ResponseEntity responseEntity = null;

        if(roadmapDTO1 != null){
            responseEntity = ResponseEntity.ok(roadmapDTO1);
        }
        else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsg);
        }
        return responseEntity;
    }

    @DeleteMapping("/roadmaps/{roadmapId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteRoadmap(@PathVariable Long roadmapId){
        ResponseEntity responseEntity = null;
        try{
            roadmapService.deleteRoadmap(roadmapId);
            responseEntity = ResponseEntity.noContent().build();
        }catch (NotFoundException e){
            responseEntity = ResponseEntity.notFound().build();
        }catch (ConflictException e){
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body("Errore cancellazione elemento");
        }
        return responseEntity;
    }
}
