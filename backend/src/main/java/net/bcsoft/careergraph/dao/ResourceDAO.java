package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Skill;

import java.util.List;

public interface ResourceDAO {
    List<Resource> selectAll();
}
