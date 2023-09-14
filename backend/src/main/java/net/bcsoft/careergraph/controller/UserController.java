package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.service.IUserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final IUserService userService;


    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public UserDTO findUser(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping("/users/{userId}/user-skills")
    public UserSkillDTO createUserSkill(@PathVariable Long userId, @RequestBody UserSkillDTO userSkillDTO){
        try {

        }catch ()
        return userService.createUserSkill(userId, userSkillDTO);
    }

    @GetMapping("/users/{userId}/user-skills")
    public List<UserSkillDTO> findUserSkillByUserId(@PathVariable Long userId){
        return userService.findUserSkillByUserId(userId);
    }

    @PutMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO updateUserSkill(@PathVariable Long userId, @PathVariable Long userSkillId, @RequestBody UserSkillDTO userSkillDTO){
        return userService.updateUserSkill(userId, userSkillId, userSkillDTO);
    }

    @GetMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO findUserSkill(@PathVariable Long userId, @PathVariable Long userSkillId){
        return userService.findUserSkillById(userSkillId);
    }

    /*@DeleteMapping("/users/{userId}/user-skills/{userSkillId}")
    public void deleteUserSkill(@PathVariable Integer userId, @PathVariable Integer userSkillId){
        userService.delete(userId, userSkillId);
    }*/
}
