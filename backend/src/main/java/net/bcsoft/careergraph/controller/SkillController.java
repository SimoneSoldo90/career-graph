package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class SkillController {
    private final ISkillService skillService;

    public SkillController(ISkillService iSkillService) {
        this.skillService = iSkillService;
    }

    @GetMapping("/skills/")
    public ResponseEntity <List<SkillDTO>> findSkills() {
        List <SkillDTO> skillDTOList = null;
        String sErrorMsg = "";
        try{
           skillDTOList = skillService.findAllSkills();
        }catch(NoContentException | InternalException e){
            sErrorMsg = "Error getting list: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(skillDTOList != null){
            responseEntity = ResponseEntity.ok(skillDTOList);
        }else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @PostMapping("/skills/")
    public ResponseEntity <SkillDTO> createSkill(@RequestBody SkillDTO skillDTO) {
        SkillDTO skillDTO1 = null;
        String sErrorMsg = "";
        try{
             skillDTO1 = skillService.createSkill(skillDTO);
        }catch (BadRequestException | InternalException e){
            sErrorMsg = "Error creating roadmap: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(skillDTO1 != null) {
            try{
                responseEntity = ResponseEntity.created(new URI("/skills/" + skillDTO1.id())).body(skillDTO1);
            }catch (URISyntaxException e){
                responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsg);
        }
        return responseEntity;
    }

    @GetMapping("/skills/{skillId}")
    public ResponseEntity<SkillDTO> findSkillById(@PathVariable Long skillId) {
        SkillDTO skillDTO = null;
        ResponseEntity responseEntity = null;
        try{
            skillDTO = skillService.findSkillById(skillId);
            responseEntity = ResponseEntity.ok(skillDTO);
        }catch(NotFoundException | InternalException e){
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/skills/{skillId}")
    public ResponseEntity <SkillDTO> updateSkillId(@RequestBody SkillDTO skillDTO) {
        SkillDTO skillDTO1 = null;
        String sErrorMsg = "";
        try{
            skillDTO1 = skillService.updateSkill(skillDTO);
        }catch (ConflictException | InternalException e){
            sErrorMsg = "error updating skill" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(skillDTO1 != null){
            responseEntity = ResponseEntity.ok(skillDTO);
        }
        else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsg);
        }
        return responseEntity;
    }
    @PostMapping("/skills/{skillId}/resources")
    public ResponseEntity <ResourceDTO> createResource(@PathVariable Long skillId, @RequestBody ResourceDTO resourceDTO){
        ResourceDTO resourceDTO1 = null;
        String sErrorMsg = "";
        try{
            resourceDTO1 = skillService.createResource(skillId, resourceDTO);
        }catch (BadRequestException | InternalException e){
            sErrorMsg = "Error creating resource: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTO1 != null) {
            try{
                responseEntity = ResponseEntity.created(new URI("/skills/" + skillId + "/resources" + resourceDTO1.id())).body(resourceDTO1);
            }catch (URISyntaxException e){
                responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsg);
        }
        return responseEntity;
    }
    @GetMapping("/skills/{skillId}/resources")
    public ResponseEntity <List<ResourceDTO>> findResources(@PathVariable Long skillId){
        List <ResourceDTO> resourceDTOList = null;
        String sErrorMsg = "";
        try{
            resourceDTOList = skillService.findAllResource(skillId);
        }catch(NoContentException | InternalException e){
            sErrorMsg = "Error getting list: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTOList != null){
            responseEntity = ResponseEntity.ok(resourceDTOList);
        }else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @GetMapping("/skills/{skillId}/resources/{resourceId}")
    public ResponseEntity <ResourceDTO> findResourceById(@PathVariable Long skillId, @PathVariable Long resourceId){
        ResourceDTO resourceDTO = null;
        String sErrorMsh = "";
        try{
            resourceDTO = skillService.findResourceById(skillId, resourceId);
        }catch(NotFoundException | InternalException e){
            sErrorMsh = "Error getting resource: " + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTO != null) {
            responseEntity = ResponseEntity.ok(resourceDTO);
        }else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }
    @PutMapping("/skills/{skillId}/resources/{resourceId}")
    public ResponseEntity <ResourceDTO> updateResource (@PathVariable Long skillId, @PathVariable Long resourceId, @RequestBody ResourceDTO resourceDTO ){
        ResourceDTO resourceDTO1 = null;
        String sErrorMsg = "";
        try{
            resourceDTO1 = skillService.updateResource(resourceDTO);
        }catch (ConflictException | InternalException e){
            sErrorMsg = "error updating resource" + e.getMessage();
        }
        ResponseEntity responseEntity = null;
        if(resourceDTO1 != null){
            responseEntity = ResponseEntity.ok(resourceDTO);
        }
        else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsg);
        }
        return responseEntity;
    }

}
