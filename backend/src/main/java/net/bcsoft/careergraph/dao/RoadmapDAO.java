package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Roadmap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapDAO {
    List<Roadmap> selectAll();
}
