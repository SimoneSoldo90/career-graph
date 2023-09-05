package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.RoadmapLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapLinkDAO {
    List<RoadmapLink> selectAll();
}
