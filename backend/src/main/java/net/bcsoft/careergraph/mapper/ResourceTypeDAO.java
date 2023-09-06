package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.ResourceType;

import java.util.List;

public interface ResourceTypeDAO {
    List<ResourceType> selectAll();
    ResourceType select();
    ResourceType insert();
    ResourceType update();
}
