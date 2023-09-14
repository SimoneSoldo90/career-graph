package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
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
    public List<SkillDTO> findSkills() {
        try{
            return skillService.findAll();
        }catch(NoContentException e){

        }
    }

    @PostMapping("/skills/")
    public SkillDTO createSkill(@RequestBody SkillDTO skillDTO) {
        try{
             skillService.create(skillDTO);
        }catch (BadRequestException e){

        }
        return skillDTO;
    }

    @GetMapping("/skills/{skillId}")
    public SkillDTO findSkillById(@PathVariable Long skillId) {
        try{
            skillService.findById(skillId);
        }catch(NotFoundException e){

        }
        return ;
    }

    @PutMapping("/skills/{skillId}")
    public SkillDTO updateSkillId(@RequestBody SkillDTO skillDTO) {
        try{
            skillService.update(skillDTO);
        }catch (ConflictException e){

        }
        return skillDTO;
    }
    @PostMapping("/skills/{skillId}/resources")
    public ResourceDTO createResource(@PathVariable Long skillId, @RequestBody ResourceDTO resourceDTO){
        try{
            skillService.createResource(skillId, resourceDTO);
        }catch (BadRequestException e) {

        }
        return ;
    }
    @GetMapping("/skills/{skillId}/resources")
    public List<ResourceDTO> findResources(@PathVariable Long skillId){
        try{
            skillService.findAllResource(skillId);
        }catch (NoContentException e){

        }
        return
    }

    @GetMapping("/skills/{skillId}/resources/{resourceId}")
    public ResourceDTO findResourceById(@PathVariable Long skillId, @PathVariable Long resourceId){
        try{
            skillService.findResourceById(skillId, resourceId);
        }catch (NotFoundException e){

        }
        return
    }
    @PutMapping("/skills/{skillId}/resources/{resourceId}")
    public ResourceDTO updateResource (@PathVariable Long skillId, @PathVariable Long resourceId, @RequestBody ResourceDTO resourceDTO ){
        try{
            skillService.updateResource(skillId, resourceId, resourceDTO);
        }catch (ConflictException e){

        }
        return resourceDTO;
    }

}
