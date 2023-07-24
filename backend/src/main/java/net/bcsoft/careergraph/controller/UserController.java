package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable Integer userId){
        return userService.getById(userId);
    }
}
