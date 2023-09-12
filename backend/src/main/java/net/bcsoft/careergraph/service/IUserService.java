package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;

import java.util.List;

public interface IUserService {
    public UserDTO findById(Long id);
    public List<UserSkillDTO> findUserSkillByUserId(Long userId);
    public UserSkillDTO createUserSkill(Long userId, UserSkillDTO userSkillDTO);
    public UserSkillDTO updateUserSkill(Long userId, Long UserSkillId, UserSkillDTO userSkillDTO);
    public UserSkillDTO findUserSkillById(Long userSkillId);

    //public void delete(Integer userId, Integer userSkillId);
}
