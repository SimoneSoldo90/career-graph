package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Skill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillDAO {
    List<Skill> selectAll();
}
