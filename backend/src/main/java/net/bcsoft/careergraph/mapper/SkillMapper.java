package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Skill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillMapper {
    List<Skill> findAll();
    Skill findById(Long id);
    List<Skill> findByStepId(Long stepId);
    int insert(Skill skill);
    int update(Skill skill);
}
