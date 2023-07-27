package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.service.IStepResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StepResourcesController {

    IStepResourceService stepResourceService;
    public StepResourcesController(IStepResourceService stepResourceService){
        this.stepResourceService = stepResourceService;
    }


    @PostMapping("/step/{stepId}/resources")
    public ResourceDTO createResource(@PathVariable Integer stepId,@RequestBody ResourceDTO resourceDTO){
        return stepResourceService.create(stepId, resourceDTO);
    }

    @GetMapping("/step/{stepId}/resources")
    public List <ResourceDTO> getResource(@PathVariable Integer stepId){
        return stepResourceService.getAll(stepId);
    }

    @GetMapping("/steps/{stepId}/resources/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Integer stepId, @PathVariable Integer resourceId){
        return stepResourceService.getById(stepId, resourceId);
    }

    @PutMapping("/steps/{stepId}/resources/{resourceId}")
    public ResourceDTO updateResourceById(@PathVariable Integer stepId, @PathVariable Integer resourceId, @RequestBody ResourceDTO resourceDTO){
        return stepResourceService.update(stepId, resourceId, resourceDTO);
    }

    @DeleteMapping("/steps/{stepId}/resources/{resourceId}")
    public void deleteResource(@PathVariable Integer stepId, @PathVariable Integer resourceId){
        stepResourceService.delete(stepId, resourceId);
    }
}
