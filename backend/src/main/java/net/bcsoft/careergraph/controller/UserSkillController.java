package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserSkillDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserSkillController {
    @PostMapping("/users/{userId}/user-skills")
    public UserSkillDTO createUserSkill(@PathVariable Integer userId, @RequestBody UserSkillDTO userSkillDTO){
        return userSkillDTO;
    }

    @GetMapping("/users/{userId}/user-skills")
    public List <UserSkillDTO> getUserSkill(@PathVariable Integer userId){
        List <UserSkillDTO> userSkillDTOList = new ArrayList<>();
        return  userSkillDTOList;
    }

    @PutMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO updateUserSkill(@PathVariable Integer userId, @PathVariable Integer userSkillId, @RequestBody UserSkillDTO userSkillDTO){
        return userSkillDTO;
    }

    @GetMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO getUserSkillById(@PathVariable Integer userId, @PathVariable Integer userSkillId){
        UserSkillDTO userSkillDTO = new UserSkillDTO();
        return userSkillDTO;
    }

    @DeleteMapping("/users/{userId}/user-skills/{userSkillId}")
    public void deleteUserSkill(@PathVariable Integer userId, @PathVariable Integer userSkillId){

    }
}
