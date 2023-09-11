package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/skill/{skillId}")
    public SkillDTO getSkillId(@PathVariable Long skillId) {
        return skillService.findById(skillId);
    }

    @PutMapping("/skill/{skillId}")
    public ResponseEntity<SkillDTO> updateSkillId(@RequestBody SkillDTO skillDTO) {
        return null;
    }
    @PostMapping("/skill/{skillId}/resource")
    public ResourceDTO createResource(@RequestBody ResourceDTO resourceDTO){
        return null;
    }
    @GetMapping("/skill/{skillId}/resource")

    public List<ResourceDTO> getResourceList(@PathVariable Long skillId){
        return null;
    }

    @GetMapping("/skill/{skillId}/resource/{resourceId}")
    public ResourceDTO getResourceById(@PathVariable Long skillId, @PathVariable Long resourceId){
        return null;
    }
    @PutMapping("/skill/{skillId}/resource/{resourceId}")
    public ResourceDTO updateResource (@PathVariable Long skillId, @PathVariable Long resourceId, @RequestBody ResourceDTO resourceDTO ){
        return null;
    }

}
