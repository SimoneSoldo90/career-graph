package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.AccountSkill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountSkillMapper {
    List <AccountSkill> selectAll();
    AccountSkill selectById(Long id);
    AccountSkill insert(AccountSkill accountSkill);
    AccountSkill update(AccountSkill accountSkill);
}
