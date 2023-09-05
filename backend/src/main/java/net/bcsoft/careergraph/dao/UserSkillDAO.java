package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Skill;

import java.util.List;

public interface UserSkillDAO {
    List<Skill> selectAll();
}
