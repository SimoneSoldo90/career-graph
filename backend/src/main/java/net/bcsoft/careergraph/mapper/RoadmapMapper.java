package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Roadmap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapMapper {
    List<Roadmap> selectAll();
    Roadmap selectById(Long id);
    int insert(Roadmap roadmap);
    int update(Roadmap roadmap);
}
