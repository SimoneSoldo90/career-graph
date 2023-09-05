package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.entity.Step;

import java.util.List;

public interface StepDAO {
    List<Step> selectAll();
}
