package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {
    private final ISkillService skillService;

    public SkillController(ISkillService iSkillService) {
        this.skillService = iSkillService;
    }

    @GetMapping("/skills/")
    public List<SkillDTO> getSkills() {
        return skillService.findAll();
    }

    @PostMapping("/skills/")
    public SkillDTO createSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.create(skillDTO);
    }

    @GetMapping("/skills/{skillId}")
    public SkillDTO getSkillId(@PathVariable Long skillId) {
        return skillService.findById(skillId);
    }

    @PutMapping("/skills/{skillId}")
    public SkillDTO updateSkillId(@RequestBody SkillDTO skillDTO) {
        return skillService.update(skillDTO);
    }
    @PostMapping("/skills/{skillId}/resources")
    public ResourceDTO createResource(@PathVariable Long skillId, @RequestBody ResourceDTO resourceDTO){
        return skillService.createResource(skillId, resourceDTO);
    }
    @GetMapping("/skills/{skillId}/resources")
    public List<ResourceDTO> getResourceList(@PathVariable Long skillId){
        return skillService.findAllResource(skillId);
    }

    @GetMapping("/skills/{skillId}/resources/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Long skillId, @PathVariable Long resourceId){
        return skillService.findResourceById(skillId, resourceId);
    }
    @PutMapping("/skills/{skillId}/resources/{resourceId}")
    public ResourceDTO updateResource (@PathVariable Long skillId, @PathVariable Long resourceId, @RequestBody ResourceDTO resourceDTO ){
        return skillService.updateResource(skillId, resourceId, resourceDTO);
    }

}
