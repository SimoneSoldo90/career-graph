package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class StepController {
    IStepService stepService;

    @Autowired
    public StepController(IStepService stepService) {
        this.stepService = stepService;
    }

    @PostMapping("/steps/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<StepDTO> createStep(@RequestBody StepDTO stepDTO) {
            StepDTO stepDTO1 =null;
            String sErrorMsq = "";
            ResponseEntity responseEntity = null;
        try {
            stepDTO1 = stepService.create(stepDTO);
        } catch (BadRequestException e) {
            sErrorMsq = "Error creating list:" + e.getMessage();
        }

        if(stepDTO1 != null) {
            try {
                responseEntity = ResponseEntity.created(new URI("/steps/" + stepDTO1.id())).body(stepDTO1);
            } catch (URISyntaxException e) {
                responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsq);
            }
        return responseEntity;
    }

    @GetMapping("/steps/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List <StepDTO>> findSteps(){
            List <StepDTO> stepDTOList = null;
            String sErrorMsq = "";
        try {
             stepDTOList = stepService.findAll();
        } catch (NoContentException e){
            sErrorMsq = "Error getting list:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(stepDTOList != null){
            responseEntity = ResponseEntity.ok(stepDTOList);
        }else{
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @GetMapping("/steps/{stepId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <StepDTO> findStepById (@PathVariable Long stepId){
        StepDTO stepDTO =null;
        String sErrorMsq = "";
        try {
            stepDTO = stepService.findById(stepId);
        } catch (NotFoundException e) {
            sErrorMsq = "Error getting step:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(stepDTO != null){
            responseEntity = ResponseEntity.ok(stepDTO);
        }else{
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }


    @PutMapping("/steps/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<StepDTO> updateStep(@RequestBody StepDTO stepDTO){
        StepDTO stepDTO1 = null;
        String sErrorMsq = "";
        try{
             stepDTO1 = stepService.update(stepDTO1);
        }catch (ConflictException e){
            sErrorMsq = "Error updating step:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(stepDTO1 != null){
            responseEntity = ResponseEntity.ok(stepDTO1);
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsq);
        }
        return responseEntity;
    }

    /*@DeleteMapping("/steps/{stepId}")
    public void deleteStep(@PathVariable Long stepId){
        iStepService.delete(stepId);
    }*/

    @PostMapping("/step/{stepId}/resources")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResourceDTO> createResource(@PathVariable Long stepId, @RequestBody ResourceDTO resourceDTO){
        ResourceDTO resourceDTO1 = resourceDTO;
        String sErrorMsg = "";
        ResponseEntity responseEntity = null;
        try{
             resourceDTO = stepService.createResource(stepId, resourceDTO);
        }catch (BadRequestException e){
            sErrorMsg = "Error creating resource:" + e.getMessage();
        }

        if(resourceDTO != null) {
            try {
                    responseEntity = ResponseEntity.created(new URI("/resource/" + resourceDTO1.stepId())).body(resourceDTO);
            }catch(URISyntaxException e){
            responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsg);
        }
        return responseEntity;
    }

    @GetMapping("/step/{stepId}/resources")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List <ResourceDTO>> findResources(@PathVariable Long stepId){
        List<ResourceDTO> resourceDTOList = null;
        String sErrorMsq = "";
        try{
             resourceDTOList = stepService.findAllResource(stepId);
        }catch (NoContentException e){
            sErrorMsq = "Error getting list:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTOList != null){
            responseEntity = ResponseEntity.ok(resourceDTOList);
        }else{
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @GetMapping("/steps/{stepId}/resources/{resourceId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResourceDTO> findByIdResource(@PathVariable Long stepId, @PathVariable Long resourceId){
        ResourceDTO resourceDTO = null;
        String sErrorMsq = "";
        try{
             resourceDTO = stepService.findByResourceId(resourceId);
        }catch (NotFoundException e){
            sErrorMsq = "Error getting resource:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTO != null){
            responseEntity = ResponseEntity.ok(resourceDTO);
        }else{
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/steps/{stepId}/resources/{resourceId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable Long stepId, @PathVariable Long resourceId, @RequestBody ResourceDTO resourceDTO){
        ResourceDTO resourceDTO1 = null;
        String sErrorMsq = "";
        try{
            resourceDTO1 = stepService.updateResource(resourceDTO);
        }catch (ConflictException e){
            sErrorMsq = "Error updating resource:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTO1 != null){
            responseEntity = ResponseEntity.ok(resourceDTO1);
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsq);
        }
        return responseEntity;
    }

    /*@DeleteMapping("/steps/{stepId}/resources/{resourceId}")
    public void deleteResource(@PathVariable Long stepId, @PathVariable Long resourceId){
    return iStepService.delete(resourceId);
    }*/

    @PostMapping("/steps/{stepId}/roadmap-links/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<RoadmapLinkDTO> createStepRoadmapLink(@PathVariable Long stepId, @RequestBody RoadmapLinkDTO roadmapLinkDTO){
        RoadmapLinkDTO roadmapLinkDTO1 = null;
        String sErrorMsq = "";
        ResponseEntity responseEntity = null;
        try{
            roadmapLinkDTO1 = stepService.createRoadmapLink(roadmapLinkDTO);
        }catch (BadRequestException e){
            sErrorMsq = "Error creating roadmaplink:" + e.getMessage();
        }

        if(roadmapLinkDTO1 != null){
            try {
                responseEntity = ResponseEntity.created(new URI("/roadmap-links/" + roadmapLinkDTO1.id())).body(roadmapLinkDTO1);
            }catch (URISyntaxException e){
                responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsq);
        }
        return responseEntity;
    }

    @GetMapping("/steps/{stepId}/roadmap-links/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<RoadmapLinkDTO>> findStepRoadmapLinkList(@PathVariable Long stepId){
        List<RoadmapLinkDTO> roadmapLinkDTOList = null;
        String sErrorMsq = "";
        try{
            roadmapLinkDTOList = stepService.findAllRoadmapLink(stepId);
        }catch (NoContentException e){
            sErrorMsq = "Error getting list:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(roadmapLinkDTOList != null){
            responseEntity = ResponseEntity.ok(roadmapLinkDTOList);
        }else{
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @GetMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<RoadmapLinkDTO> findByRoadmapLinkId(@PathVariable Long stepId, @PathVariable Long roadmapLinkId){
        RoadmapLinkDTO roadmapLinkDTO = null;
        String sErrorMsg = "";
        try{
             roadmapLinkDTO = stepService.findByRoadmapLinkId(roadmapLinkId);
        }catch (NotFoundException e){
            sErrorMsg = "Error getting roadmaplink:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(roadmapLinkDTO != null){
            responseEntity = ResponseEntity.ok(roadmapLinkDTO);
        }else{
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<RoadmapLinkDTO> updateStepRoadmapLink(@PathVariable Long stepId, @PathVariable Long roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO) {
        RoadmapLinkDTO roadmapLinkDTO1 = null;
        String sErrorMsg = "";
        try {
            roadmapLinkDTO1 = stepService.updateRoadmapLink(roadmapLinkDTO);
        } catch (ConflictException e) {
            sErrorMsg = "Error updating roadmaplink:" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(roadmapLinkDTO != null){
            responseEntity = ResponseEntity.ok(roadmapLinkDTO);
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsg);
        }
        return responseEntity;
    }

    @DeleteMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){

    }
}
