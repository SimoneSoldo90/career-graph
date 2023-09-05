package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Skill;

import java.util.List;

public interface SkillDAO {
    List<Skill> selectAll();
}
