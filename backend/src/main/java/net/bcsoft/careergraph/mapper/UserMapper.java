package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    User selectById(Long id);

    User insert(User user);

    User update(User user);
}
