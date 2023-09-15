package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.RoadmapLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapLinkMapper {
    List <RoadmapLink> selectAll();
    List <RoadmapLink> selectByStepId(Long stepId);
    RoadmapLink selectById(Long id);
    RoadmapLink insert(RoadmapLink roadmapLink);
    int update(RoadmapLink roadmapLink);
}
