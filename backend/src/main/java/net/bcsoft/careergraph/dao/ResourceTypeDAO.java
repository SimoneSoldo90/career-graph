package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.ResourceType;

import java.util.List;

public interface ResourceTypeDAO {
    List<ResourceType> selectAll();
    ResourceType select();
    ResourceType insert();
    ResourceType update();
}
