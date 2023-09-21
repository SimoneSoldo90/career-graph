package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.entity.User;
import net.bcsoft.careergraph.entity.UserSkill;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.mapper.UserMapper;
import net.bcsoft.careergraph.mapper.UserSkillMapper;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// @Transactional si potrebbe mettere qui invece di sui singoli metodi
public class UserServiceImpl implements IUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(StepServiceImpl.class);

    UserMapper userMapper;
    UserSkillMapper userSkillMapper;
    SkillMapper skillMapper;

    public UserServiceImpl(UserMapper userMapper, UserSkillMapper userSkillMapper, SkillMapper skillMapper) {
        this.userMapper = userMapper;
        this.userSkillMapper = userSkillMapper;
        this.skillMapper = skillMapper;
    }

    @Override
    public UserDTO findById(Long userId) throws NotFoundException, InternalException {
        User result;
        try {
            result = userMapper.selectById(userId);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        if(result == null){
            throw new NotFoundException("account con id = " + userId + " non trovata");
        }
        return new UserDTO(result.getId(), result.getSsoUid(), result.getFirstName(), result.getLastName(), result.getEmail());
    }

    @Override
    public List<UserSkillDTO> findUserSkillByUserId(Long userId) throws NoContentException, InternalException {
        List<UserSkill> userSkillList;
        try {
            userSkillList = userSkillMapper.selectByUserId(userId);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
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
    public UserSkillDTO createUserSkill(UserSkillDTO userSkillDTO) throws BadRequestException, InternalException {
        UserSkill userSkill = userSkillDTO.toEntity();
        try {
            userSkillMapper.insert(userSkill);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        User user;
        try {
            user = userMapper.selectById(userSkillDTO.userId());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        Skill skill;
        try {
            skill = skillMapper.findById(userSkillDTO.skillId());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        if(user == null || skill == null) {
            throw new BadRequestException("errore di creazione, inseriti dati non corretti");
        }
        UserSkill result;
        try {
            result = userSkillMapper.selectById(userSkill.getId());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        LOGGER.info("creata user skill con id " + result.getId());
        return new UserSkillDTO(result.getId(), result.getUserId(), result.getSkillId(), result.getSkillStatusId());
    }

    @Override
    @Transactional
    public UserSkillDTO updateUserSkill(UserSkillDTO userSkillDTO) throws ConflictException, InternalException {
        UserSkill oldUserSkill;
        try {
            oldUserSkill = userSkillMapper.selectById(userSkillDTO.id());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        if(oldUserSkill == null){
            throw  new ConflictException("non e' stato possibile effettuare la modifica");
        }
        UserSkill userSkill = userSkillDTO.toEntity();
        try {
            userSkillMapper.update(userSkill);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        UserSkill result;
        try {
            result = userSkillMapper.selectById(userSkill.getId());
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
        LOGGER.info("aggiornata user skill con id " + result.getId());
        return new UserSkillDTO(result.getId(), result.getUserId(), result.getSkillId(), result.getSkillStatusId());
    }

    @Override
    public UserSkillDTO findUserSkillById(Long userSkillId) throws NotFoundException, InternalException{
        UserSkill result;
        try {
            result = userSkillMapper.selectById(userSkillId);
        } catch(RuntimeException e) {
            throw new InternalException(e.getMessage());
        }
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
                LOGGER.info("eliminata user skill con id " + result.getId());
            }catch (RuntimeException e) {
                throw new ConflictException("elemento non eliminabile");
            }
        }
        else {
            throw new NotFoundException("elemento non esistente");
        }
    }

}
