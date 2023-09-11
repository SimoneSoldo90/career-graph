package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    List<Resource> selectAll();
    Resource selectById(Long id);
    List<Resource> selectByStepId(Long stepId);
    int insert(Resource resource);
    int update(Resource resource);

}
