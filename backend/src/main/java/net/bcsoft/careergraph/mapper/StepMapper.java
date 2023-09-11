package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Step;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StepMapper {
    List<Step> selectAll();
    Step selectById(Long id);
    int insert(Step step);
    int update(Step step);
    List <Step> findByRoadmapId(long roadmapId);

}
