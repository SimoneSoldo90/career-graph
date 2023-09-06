package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.RoadmapLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapLinkMapper {
    RoadmapLink selectById(Long id);
}
