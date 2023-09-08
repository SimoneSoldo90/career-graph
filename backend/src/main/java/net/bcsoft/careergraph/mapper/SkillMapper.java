package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Skill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillMapper {
    List<Skill> selectAll();
    Skill selectById(Long id);
    Skill insert(Skill skill);
    Skill update(Skill skill);

    List<Skill> selectByStepId(Long stepId);
}
