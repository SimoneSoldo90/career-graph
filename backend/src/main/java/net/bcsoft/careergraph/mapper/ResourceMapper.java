package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Account;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Step;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    List<Resource> selectAll();
    Resource selectById(Long id);
    Resource insert(Resource resource);
    Resource update(Resource resource);

}
