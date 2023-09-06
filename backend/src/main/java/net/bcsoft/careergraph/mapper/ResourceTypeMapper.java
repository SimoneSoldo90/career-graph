package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.ResourceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceTypeMapper {
    List<ResourceType> selectAll();
    ResourceType selectById(Long id);
}
