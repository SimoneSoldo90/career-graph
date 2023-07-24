package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.service.ISkillResourceService;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SkillResourceController {

    ISkillResourceService skillResourceService;

    public SkillResourceController(ISkillResourceService skillResourceService) {
        this.skillResourceService = skillResourceService;
    }
    @PostMapping("/skill/{skillid}/resource")
    public ResourceDTO createResource(@PathVariable Integer skillId, @RequestBody ResourceDTO resourceDTO){
        return skillResourceService.create(skillId, resourceDTO);
    }
    @GetMapping("/skill/{skillid}/resource")

    public List<ResourceDTO> getResourceList(@PathVariable Integer skillId){
        return skillResourceService.getAll(skillId);
    }

    @GetMapping("/skill/{skillid}/resource/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Integer skillId, @PathVariable Integer resourceId){
        return skillResourceService.getById(skillId, resourceId);
    }
    @PutMapping("/skill/{skillid}/resource/{resourceId}")
    public ResourceDTO updateResource (@PathVariable Integer skillId, @PathVariable Integer resourceId, @RequestBody ResourceDTO resourceDTO ){
        return skillResourceService.update(skillId, resourceId, resourceDTO);
    }

    @DeleteMapping("/skill/{skillid}/resource/{resourceId}")
    public void deleteResource(@PathVariable Integer skillId, @PathVariable Integer resourceId){
        skillResourceService.delete(skillId, resourceId);
    }
}
