package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    private final IUserService userService;


    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <UserDTO> findUser(@PathVariable Long userId){
        UserDTO userDTO;
        ResponseEntity responseEntity;
        try {
            userDTO = userService.findById(userId);
            responseEntity = ResponseEntity.ok(userDTO);
        } catch (NotFoundException | InternalException e) {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @PostMapping("/users/{userId}/user-skills")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <UserSkillDTO> createUserSkill(@PathVariable Long userId, @RequestBody UserSkillDTO userSkillDTO){
        UserSkillDTO userSkillDTO1 = null;
        String sErrorMsg = "";

        if(userId != userSkillDTO.userId()){
            sErrorMsg= "ids in the userskill mismatch the ones in the request body";
        }else{
            try {
                userSkillDTO1 = userService.createUserSkill(userSkillDTO);
            } catch (BadRequestException | RuntimeException | InternalException e) {
                sErrorMsg = "Error creating user skill: " + e.getMessage();
            }
        }

        ResponseEntity responseEntity;
        if(userSkillDTO1 != null){
            try{
                //responseEntity = ResponseEntity.created(new URI("/roadmap-links/" + roadmapLinkDTO1.id())).body(roadmapLinkDTO1);
                responseEntity = ResponseEntity.created(new URI("/users/" + userId + "/user-skills/" + userSkillDTO1.id())).body(userSkillDTO1);
            }catch (URISyntaxException e){
                responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
            }
        }else{
            responseEntity = ResponseEntity.badRequest().body(sErrorMsg);
        }
        return responseEntity;
    }

    @GetMapping("/users/{userId}/user-skills")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <List<UserSkillDTO>> findUserSkillByUserId(@PathVariable Long userId){
        List<UserSkillDTO> userSkillDTOList;
        ResponseEntity responseEntity;
        try{
            userSkillDTOList = userService.findUserSkillByUserId(userId);
            responseEntity = ResponseEntity.ok(userSkillDTOList);
        }catch(NoContentException | InternalException e){
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/users/{userId}/user-skills/{userSkillId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <UserSkillDTO> updateUserSkill(@PathVariable Long userId, @PathVariable Long userSkillId, @RequestBody UserSkillDTO userSkillDTO){
        UserSkillDTO userSkillDTO1 = null;
        String sErrorMsg = "";
        if(userId != userSkillDTO.userId() || userSkillId != userSkillDTO.id()){
            sErrorMsg = "ids in the url mismatch the ones in the request body";
        }else{
            try{
            userSkillDTO1 = userService.updateUserSkill(userSkillDTO);
        }catch (ConflictException | InternalException e){
            sErrorMsg = "error updating skill : " + e.getMessage();
        }
    }

        ResponseEntity responseEntity;
        if(userSkillDTO1 != null){
            responseEntity = ResponseEntity.ok(userSkillDTO);
        }
        else{
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(sErrorMsg);
        }
        return responseEntity;
    }

    @GetMapping("/users/{userId}/user-skills/{userSkillId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity <UserSkillDTO> findUserSkillById(@PathVariable Long userId, @PathVariable Long userSkillId){
        UserSkillDTO userSkillDTO;
        ResponseEntity responseEntity;
        try{
            userSkillDTO = userService.findUserSkillById(userSkillId);
            responseEntity = ResponseEntity.ok(userSkillDTO);
        }catch(NotFoundException | InternalException e){
            responseEntity = ResponseEntity.notFound().build();
        }

        return responseEntity;
    }

    @DeleteMapping("/users/{userId}/user-skills/{userSkillId}")
    public ResponseEntity<String> deleteUserSkill(@PathVariable Long userId, @PathVariable Long userSkillId){
        ResponseEntity responseEntity;
        try{
            userService.deleteUserSkill(userSkillId);
            responseEntity = ResponseEntity.noContent().build();
        }catch (NotFoundException e){
            responseEntity = ResponseEntity.notFound().build();
        }catch (ConflictException e){
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body("Errore cancellazione elemento");
        }
        return responseEntity;
    }
}
