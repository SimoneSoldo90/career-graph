package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.entity.Step;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StepDAO {
    List<Step> selectAll();
}
