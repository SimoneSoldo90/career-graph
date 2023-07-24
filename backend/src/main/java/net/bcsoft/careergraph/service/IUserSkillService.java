package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.UserSkillDTO;

import java.util.List;

public interface IUserSkillService {

    public List<UserSkillDTO> getAll(Integer userId);
    public UserSkillDTO create(Integer userId, UserSkillDTO userSkillDTO);
    public UserSkillDTO update(Integer userId, Integer UserSkillId, UserSkillDTO userSkillDTO);
    public UserSkillDTO getById(Integer userId, Integer userSkillId);
    public void delete(Integer userId, Integer userSkillId);
}
