package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;

import java.util.List;

public interface IUserService {
    UserDTO findById(Long id) throws NotFoundException, InternalException;
    List<UserSkillDTO> findUserSkillByUserId(Long userId) throws NoContentException, InternalException;
    UserSkillDTO createUserSkill(UserSkillDTO userSkillDTO) throws BadRequestException, InternalException;
    UserSkillDTO updateUserSkill(UserSkillDTO userSkillDTO) throws ConflictException, InternalException;
    UserSkillDTO findUserSkillById(Long userSkillId) throws NotFoundException, InternalException;
    void deleteUserSkill(Long userSkillId) throws ConflictException, NotFoundException;


}
