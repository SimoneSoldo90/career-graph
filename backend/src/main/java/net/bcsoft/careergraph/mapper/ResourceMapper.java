package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    List<Resource> findAll();
    Resource findById(Long id);
    List<Resource> findByStepId(Long stepId);
    List<Resource> findBySkillId(Long skillId);
    int insert(Resource resource);
    int update(Resource resource);

}
