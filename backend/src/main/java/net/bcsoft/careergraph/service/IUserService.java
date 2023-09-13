package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;

import java.util.List;

public interface IUserService {
    public UserDTO findById(Long id) throws NotFoundException;
    public List<UserSkillDTO> findUserSkillByUserId(Long userId) throws NoContentException;
    public UserSkillDTO createUserSkill(Long userId, UserSkillDTO userSkillDTO) throws BadRequestException;
    public UserSkillDTO updateUserSkill(Long userId, Long UserSkillId, UserSkillDTO userSkillDTO) throws ConflictException;
    public UserSkillDTO findUserSkillById(Long userSkillId) throws NotFoundException;

    //public void delete(Integer userId, Integer userSkillId);
}
