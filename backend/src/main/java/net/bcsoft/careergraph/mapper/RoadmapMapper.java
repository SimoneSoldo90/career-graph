package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Roadmap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapMapper {
    Roadmap selectById(Long id);
    Roadmap insert(Roadmap roadmap);
    Roadmap update(Roadmap roadmap);
}
