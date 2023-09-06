package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceDAO {
    List<Resource> selectAll();
}
