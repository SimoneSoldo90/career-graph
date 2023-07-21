package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable Integer userId){
        UserDTO userDTO = new UserDTO();
        return userDTO;
    }
}
