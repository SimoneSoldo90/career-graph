package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Step;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StepDAO {
    List<Step> selectAll();
}
