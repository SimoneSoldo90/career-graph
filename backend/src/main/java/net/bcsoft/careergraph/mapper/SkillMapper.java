package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Account;
import net.bcsoft.careergraph.entity.Skill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillMapper {
    List<Skill> selectAll();
    Skill selectById(Long id);
    int insert(Skill skill);
    int update(Skill skill);
}
