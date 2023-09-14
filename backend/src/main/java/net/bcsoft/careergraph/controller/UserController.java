package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
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
        try {
            return userService.findById(userId);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/users/{userId}/user-skills")
    public UserSkillDTO createUserSkill(@PathVariable Long userId, @RequestBody UserSkillDTO userSkillDTO){
        try {
            return userService.createUserSkill(userSkillDTO);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users/{userId}/user-skills")
    public List<UserSkillDTO> findUserSkillByUserId(@PathVariable Long userId){
        try {
            return userService.findUserSkillByUserId(userId);
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO updateUserSkill(@PathVariable Long userId, @PathVariable Long userSkillId, @RequestBody UserSkillDTO userSkillDTO){
        try {
            return userService.updateUserSkill(userSkillDTO);
        } catch (ConflictException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users/{userId}/user-skills/{userSkillId}")
    public UserSkillDTO findUserSkill(@PathVariable Long userId, @PathVariable Long userSkillId){
        try {
            return userService.findUserSkillById(userSkillId);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*@DeleteMapping("/users/{userId}/user-skills/{userSkillId}")
    public void deleteUserSkill(@PathVariable Integer userId, @PathVariable Integer userSkillId){
        userService.delete(userId, userSkillId);
    }*/
}
