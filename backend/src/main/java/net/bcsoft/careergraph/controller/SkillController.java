package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.SkillDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class SkillController {
    @GetMapping("/skills/")
    public List<SkillDTO> getSkills() {
        return new ArrayList<>();
    }
    @PostMapping("/skills")
    public SkillDTO createSkill(@RequestBody SkillDTO skillDTO){
        return new SkillDTO();//TODO

    }

    @GetMapping("/skill/{id}")
    public SkillDTO getSkillId (@PathVariable Integer id) {
        SkillDTO skillDTO = new SkillDTO();
        return skillDTO;
    }

    @PutMapping("/skill/{id}")
    public SkillDTO updateSkillId (@PathVariable Integer id, @RequestBody SkillDTO skillDTO){
        return new SkillDTO();
    }
}
