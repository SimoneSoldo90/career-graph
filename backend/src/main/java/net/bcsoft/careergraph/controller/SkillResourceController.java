package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SkillResourceController {
    @PostMapping("/skill/{skillid}/resource")
    public ResourceDTO createResource(@RequestBody ResourceDTO resourceDTO){
        return resourceDTO;
    }

    @GetMapping("/skill/{skillid}/resource")

    public List<ResourceDTO> getResource(@PathVariable Integer skillId){
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        return resourceDTOList;
    }

    @GetMapping("/skill/{skillid}/resource/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Integer skillId, @PathVariable Integer resourceId){
        ResourceDTO resourceDTO = new ResourceDTO();
        return resourceDTO;
    }
    @PutMapping("/skill/{skillid}/resource/{resourceId}")
    public ResourceDTO updateResource (@PathVariable Integer skillId, @PathVariable Integer resourceId, @RequestBody ResourceDTO resourceDTO ){
        return resourceDTO;
    }

    @DeleteMapping("/skill/{skillid}/resource/{resourceId}")
    public void deleteResource(@PathVariable Integer skillId, @PathVariable Integer resourceId){

    }
}
