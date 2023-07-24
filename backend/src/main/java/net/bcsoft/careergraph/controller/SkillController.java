package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // questa parte di codice è incongruente le cose al plurale invece di list, hanno la s
public class SkillController {
    private final ISkillService skillService;

    public SkillController(ISkillService iSkillService) {
        this.skillService = iSkillService;
    }

    @GetMapping("/skills")
    public List<SkillDTO> getSkills() {
        return skillService.getAll();
    }

    @PostMapping("/skills")
    public SkillDTO createSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.create(skillDTO);
    }

    @GetMapping("/skill/{skillId}")
    public SkillDTO getSkillId(@PathVariable Integer skillId) {
        return skillService.getById(skillId);
    }

    @PutMapping("/skill/{skillId}")
    public ResponseEntity<SkillDTO> updateSkillId(@PathVariable Integer skillId, @RequestBody SkillDTO skillDTO) {
        if (skillId.equals(skillDTO.getId())) {
            SkillDTO result = skillService.update(skillId, skillDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
