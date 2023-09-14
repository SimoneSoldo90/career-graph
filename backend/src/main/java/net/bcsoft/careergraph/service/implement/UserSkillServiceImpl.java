package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.service.IUserSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillServiceImpl implements IUserSkillService {
    @Override
    public List<UserSkillDTO> getAll(Integer userId) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public UserSkillDTO create(Integer userId, UserSkillDTO userSkillDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public UserSkillDTO update(Integer userId, Integer UserSkillId, UserSkillDTO userSkillDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public UserSkillDTO getById(Integer userId, Integer userSkillId) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public void delete(Integer userId, Integer userSkillId) {
        System.out.println("Funziona!");

    }
}
