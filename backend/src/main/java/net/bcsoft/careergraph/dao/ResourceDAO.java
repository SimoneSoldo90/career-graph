package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Skill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceDAO {
    List<Resource> selectAll();
}
