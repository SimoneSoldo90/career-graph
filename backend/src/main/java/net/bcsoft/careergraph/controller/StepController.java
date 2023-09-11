package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StepController {
    IStepService iStepService;

    @Autowired
    public StepController(IStepService iStepService) {
        this.iStepService = iStepService;
    }

    @PostMapping("/steps")
    public StepDTO createStep(@RequestBody StepDTO stepDTO){
        return iStepService.create(stepDTO); // Implementare
    }

    @GetMapping("/steps")
    public List<StepDTO> findSteps(){
        return iStepService.findAll();
    }

    @GetMapping("/steps/{stepId}")
    public StepDTO findStepById(@PathVariable Long stepId){
        return iStepService.findById(stepId); // Implementare
    }

    @PutMapping("/steps")
    public StepDTO updateStep(@RequestBody StepDTO stepDTO){
        return iStepService.update(stepDTO);
    }

    /*@DeleteMapping("/steps/{stepId}")
    public void deleteStep(@PathVariable Long stepId){
        iStepService.delete(stepId);
    }*/

    @PostMapping("/step/{stepId}/resources")
    public ResourceDTO createResource(@PathVariable Integer stepId, @RequestBody ResourceDTO resourceDTO){
        return null;
    }

    @GetMapping("/step/{stepId}/resources")
    public List <ResourceDTO> getResource(@PathVariable Integer stepId){
        return null;
    }

    @GetMapping("/steps/{stepId}/resources/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Integer stepId, @PathVariable Integer resourceId){
        return null;
    }

    @PutMapping("/steps/{stepId}/resources/{resourceId}")
    public ResourceDTO updateResourceById(@PathVariable Integer stepId, @PathVariable Integer resourceId, @RequestBody ResourceDTO resourceDTO){
        return null;
    }

    @DeleteMapping("/steps/{stepId}/resources/{resourceId}")
    public void deleteResource(@PathVariable Integer stepId, @PathVariable Integer resourceId){

    }

    @PostMapping("/steps/{stepId}/roadmap-links/")
    public RoadmapLinkDTO createStepRoadmapLink(@PathVariable Integer stepId, @RequestBody RoadmapLinkDTO roadmapLinkDTO){
        return null;
    }

    @GetMapping("/steps/{stepId}/roadmap-links/")
    public List<RoadmapLinkDTO> getStepRoadmapLinkList(@PathVariable Integer stepId){
        return null;
    }

    @GetMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    public RoadmapLinkDTO getStepRoadmapLinkById(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){
        return null;
    }

    @PutMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    public RoadmapLinkDTO updateStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId, RoadmapLinkDTO roadmapLinkDTO){
        return null;
    }

    @DeleteMapping("/steps/{stepId}/roadmap-links/{roadmapLinkId}")
    public void deleteStepRoadmapLink(@PathVariable Integer stepId, @PathVariable Integer roadmapLinkId){

    }
}
