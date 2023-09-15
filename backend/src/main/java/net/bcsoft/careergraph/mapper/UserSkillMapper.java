package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.UserSkill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSkillMapper {
    List <UserSkill> selectAll();
    UserSkill selectById(Long id); //?
    List<UserSkill> selectByUserId(Long userId);
    int insert(UserSkill userSkill);
    int update(UserSkill userSkill);
}
