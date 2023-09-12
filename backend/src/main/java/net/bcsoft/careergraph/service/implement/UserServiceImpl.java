package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.entity.Account;
import net.bcsoft.careergraph.entity.AccountSkill;
import net.bcsoft.careergraph.mapper.AccountMapper;
import net.bcsoft.careergraph.mapper.AccountSkillMapper;
import net.bcsoft.careergraph.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// @Transactional si potrebbe mettere qui invece di sui singoli metodi
public class UserServiceImpl implements IUserService {

    AccountMapper accountMapper;
    AccountSkillMapper accountSkillMapper;

    @Override
    public UserDTO findById(Long id) {
        try {
            Account account = accountMapper.selectById(id);
            return new UserDTO(account.getId(), account.getIdSsoU(), account.getFirstName(), account.getLastName(), account.getEmail());
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<UserSkillDTO> findUserSkillByUserId(Long userId) {
        try{
            List<AccountSkill> accountSkillList = accountSkillMapper.selectByUserId(userId);
            List<UserSkillDTO> result = new ArrayList<>();
            for (AccountSkill accountSkill : accountSkillList) {
                result.add(new UserSkillDTO(accountSkill.getIdAccount(), accountSkill.getId(), accountSkill.getIdSkill(), accountSkill.getIdSkillStatus()));
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public UserSkillDTO createUserSkill(Long userId, UserSkillDTO userSkillDTO) {
        try {
            AccountSkill accountSkill = userSkillDTO.toEntity();
            accountSkillMapper.insert(accountSkill);
            AccountSkill result = accountSkillMapper.selectById(accountSkill.getId());
            return new UserSkillDTO(result.getId(), result.getIdAccount(), result.getIdSkill(), result.getIdSkillStatus());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public UserSkillDTO updateUserSkill(Long userId, Long UserSkillId, UserSkillDTO userSkillDTO) {
        try {
            AccountSkill accountSkill = userSkillDTO.toEntity();
            accountSkillMapper.update(accountSkill);
            AccountSkill result = accountSkillMapper.selectById(accountSkill.getId());
            return new UserSkillDTO(result.getId(), result.getIdAccount(), result.getIdSkill(), result.getIdSkillStatus());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserSkillDTO findUserSkillById(Long userSkillId) {
        try {
            AccountSkill result = accountSkillMapper.selectById(userSkillId);
            return new UserSkillDTO(result.getId(), result.getIdAccount(), result.getIdSkill(), result.getIdSkillStatus());
        } catch (Exception e) {
            throw e;
        }
    }

    /*@Override
    public void delete(Integer userId, Integer userSkillId) {
        System.out.println("Funziona!");

    }*/
}
