package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.StepSkill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StepSkillMapper {
    List<StepSkill> findById();
    StepSkill findById(Long id);
    List<StepSkill> findBySkillId(Long stepId);
    StepSkill insert(StepSkill stepSkill);
    StepSkill update(StepSkill stepSkill);
}