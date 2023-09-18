package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.User;
import net.bcsoft.careergraph.entity.UserSkill;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.mapper.UserMapper;
import net.bcsoft.careergraph.mapper.UserSkillMapper;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// @Transactional si potrebbe mettere qui invece di sui singoli metodi
public class UserServiceImpl implements IUserService {

    UserMapper userMapper;
    UserSkillMapper userSkillMapper;
    SkillMapper skillMapper;

    public UserServiceImpl(UserMapper userMapper, UserSkillMapper userSkillMapper, SkillMapper skillMapper) {
        this.userMapper = userMapper;
        this.userSkillMapper = userSkillMapper;
        this.skillMapper = skillMapper;
    }

    @Override
    public UserDTO findById(Long userId) throws NotFoundException {
        User result = userMapper.selectById(userId);
        if(result == null){
            throw new NotFoundException("account con id = " + userId + " non trovata");
        }
        return new UserDTO(result.getId(), result.getSsoUid(), result.getFirstName(), result.getLastName(), result.getEmail());
    }

    @Override
    public List<UserSkillDTO> findUserSkillByUserId(Long userId) throws NoContentException {
        List<UserSkill> userSkillList = userSkillMapper.selectByUserId(userId);
        if(userSkillList == null){
            throw new NoContentException("nessuna skill trovata per l'user con id = " + userId);
        }
        List<UserSkillDTO> result = new ArrayList<>();
        for (UserSkill userSkill : userSkillList) {
            result.add(new UserSkillDTO(userSkill.getId(), userSkill.getUserId(), userSkill.getSkillId(), userSkill.getSkillStatusId()));
        }
        return result;
    }

    @Override
    @Transactional
    public UserSkillDTO createUserSkill(UserSkillDTO userSkillDTO) throws BadRequestException {
        UserSkill userSkill = userSkillDTO.toEntity();
        userSkillMapper.insert(userSkill);
        User user = userMapper.selectById(userSkillDTO.userId());
        Skill skill = skillMapper.findById(userSkillDTO.skillId());
        if(user == null || skill == null) {
            throw new BadRequestException("errore di creazione, inseriti dati non corretti");
        }
        UserSkill result = userSkillMapper.selectById(userSkill.getId());
        return new UserSkillDTO(result.getId(), result.getUserId(), result.getSkillId(), result.getSkillStatusId());
    }

    @Override
    @Transactional
    public UserSkillDTO updateUserSkill(UserSkillDTO userSkillDTO) throws ConflictException {
        UserSkill oldUserSkill = userSkillMapper.selectById(userSkillDTO.id());
        if(oldUserSkill == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        UserSkill userSkill = userSkillDTO.toEntity();
        userSkillMapper.update(userSkill);
        UserSkill result = userSkillMapper.selectById(userSkill.getId());
        return new UserSkillDTO(result.getId(), result.getUserId(), result.getSkillId(), result.getSkillStatusId());
    }

    @Override
    public UserSkillDTO findUserSkillById(Long userSkillId) throws NotFoundException{
        UserSkill result = userSkillMapper.selectById(userSkillId);
        if(result == null){
            throw new NotFoundException("skill dell'account con id = " + userSkillId + " non trovata");
        }
        return new UserSkillDTO(result.getId(), result.getUserId(), result.getSkillId(), result.getSkillStatusId());
    }

    @Override
    public void deleteUserSkill(Long userSkillId) throws ConflictException, NotFoundException {
        UserSkill result = userSkillMapper.selectById(userSkillId);
        if(result != null) {
            try {
                userSkillMapper.delete(userSkillId);
            }catch (RuntimeException e) {
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }

}
