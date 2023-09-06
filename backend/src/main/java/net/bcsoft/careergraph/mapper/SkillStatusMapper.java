package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.SkillStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillStatusMapper {
    List<SkillStatus> selectAll();
    SkillStatus selectById(Long id);
}
