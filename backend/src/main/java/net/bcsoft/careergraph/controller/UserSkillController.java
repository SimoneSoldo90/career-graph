package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.service.IUserService;
import net.bcsoft.careergraph.service.IUserSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSkillController {
    private final IUserSkillService userSkillService;

    public UserSkillController(IUserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping("/users/{userId}/user-skills")
    public UserSkillDTO createUserSkill(@PathVariable Integer userId, @RequestBody UserSkillDTO userSkillDTO){
        return userSkillService.create(userId, userSkillDTO);
    }

    @GetMapping("/users/{userId}/user-skills")
    public List <UserSkillDTO> getUserSkill(@PathVariable Integer userId){
        return userSkillService.getAll(userId);
    }

    @PutMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO updateUserSkill(@PathVariable Integer userId, @PathVariable Integer userSkillId, @RequestBody UserSkillDTO userSkillDTO){
        return userSkillService.update(userId, userSkillId, userSkillDTO);
    }

    @GetMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO getUserSkillById(@PathVariable Integer userId, @PathVariable Integer userSkillId){
        return userSkillService.getById(userId, userSkillId);
    }

    @DeleteMapping("/users/{userId}/user-skills/{userSkillId}")
    public void deleteUserSkill(@PathVariable Integer userId, @PathVariable Integer userSkillId){
        userSkillService.delete(userId, userSkillId);
    }
}
