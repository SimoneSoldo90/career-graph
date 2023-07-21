package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StepResourcesController {
    @PostMapping("/step/{stepId}/resources")
    public ResourceDTO createResource(@PathVariable Integer stepId,@RequestBody ResourceDTO resourceDTO){
        return resourceDTO;
    }

    @GetMapping("/step/{stepId}/resources")
    public List <ResourceDTO> getResource(@PathVariable Integer stepId){
        List <ResourceDTO> resourceDTOList = new ArrayList<>();
        return resourceDTOList;
    }

    @GetMapping("/steps/{stepId}/resources/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Integer stepId, @PathVariable Integer resourceId){
        ResourceDTO resourceDTO = new ResourceDTO();
        return resourceDTO;
    }

    @PutMapping("/steps/{stepId}/resources/{resourceId}")
    public ResourceDTO updateResourceById(@PathVariable Integer stepId, @PathVariable Integer resourceId, @RequestBody ResourceDTO resourceDTO){
        return resourceDTO;
    }

    @DeleteMapping("/steps/{stepId}/resources/{resourceId}")
    public void deleteResource(@PathVariable Integer stepId, @PathVariable Integer resourceId){

    }
}
