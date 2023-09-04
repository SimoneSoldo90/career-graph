package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.service.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public UserDTO getById(Integer id) {
        System.out.println("Funziona!");
        return null;
    }
}
