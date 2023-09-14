package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.entity.Account;
import net.bcsoft.careergraph.entity.AccountSkill;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.mapper.AccountMapper;
import net.bcsoft.careergraph.mapper.AccountSkillMapper;
import net.bcsoft.careergraph.mapper.SkillMapper;
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

    SkillMapper skillMapper;

    @Override
    public UserDTO findById(Long accountId) throws NotFoundException {
        Account result = accountMapper.selectById(accountId);
        if(result == null){
            throw new NotFoundException("account con id = " + accountId + " non trovata");
        }
        return new UserDTO(result.getId(), result.getIdSsoU(), result.getFirstName(), result.getLastName(), result.getEmail());
    }

    @Override
    public List<UserSkillDTO> findUserSkillByUserId(Long userId) throws NoContentException {
        List<AccountSkill> accountSkillList = accountSkillMapper.selectByUserId(userId);
        if(accountSkillList == null){
            throw new NoContentException("nessuna skill trovata per l'user con id = " + userId);
        }
        List<UserSkillDTO> result = new ArrayList<>();
        for (AccountSkill accountSkill : accountSkillList) {
            result.add(new UserSkillDTO(accountSkill.getIdAccount(), accountSkill.getId(), accountSkill.getIdSkill(), accountSkill.getIdSkillStatus()));
        }
        return result;
    }

    @Override
    @Transactional
    public UserSkillDTO createUserSkill(UserSkillDTO userSkillDTO) throws BadRequestException {
        AccountSkill accountSkill = userSkillDTO.toEntity();
        accountSkillMapper.insert(accountSkill);
        Account account = accountMapper.selectById(userSkillDTO.userId());
        Skill skill = skillMapper.findById(userSkillDTO.skillId());
        if(account == null || skill == null) {
            throw new BadRequestException("errore di creazione, inseriti dati non corretti");
        }
        AccountSkill result = accountSkillMapper.selectById(accountSkill.getId());
        return new UserSkillDTO(result.getId(), result.getIdAccount(), result.getIdSkill(), result.getIdSkillStatus());
    }

    @Override
    @Transactional
    public UserSkillDTO updateUserSkill(UserSkillDTO userSkillDTO) throws ConflictException {
        AccountSkill oldUserSkill = accountSkillMapper.selectById(userSkillDTO.id());
        if(oldUserSkill == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        AccountSkill accountSkill = userSkillDTO.toEntity();
        accountSkillMapper.update(accountSkill);
        AccountSkill result = accountSkillMapper.selectById(accountSkill.getId());
        return new UserSkillDTO(result.getId(), result.getIdAccount(), result.getIdSkill(), result.getIdSkillStatus());
    }

    @Override
    public UserSkillDTO findUserSkillById(Long userSkillId) throws NotFoundException{
        AccountSkill result = accountSkillMapper.selectById(userSkillId);
        if(result == null){
            throw new NotFoundException("skill dell'account con id = " + userSkillId + " non trovata");
        }
        return new UserSkillDTO(result.getId(), result.getIdAccount(), result.getIdSkill(), result.getIdSkillStatus());
    }

    /*@Override
    public void delete(Integer userId, Integer userSkillId) {
        System.out.println("Funziona!");

    }*/
}
