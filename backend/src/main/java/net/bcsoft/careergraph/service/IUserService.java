package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.UserDTO;

public interface IUserService {
    public UserDTO getById(Integer id);
}
