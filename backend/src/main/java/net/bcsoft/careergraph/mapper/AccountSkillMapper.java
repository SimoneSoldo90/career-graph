package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.AccountSkill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountSkillMapper {
    AccountSkill selectById(Long id);
    AccountSkill insert(AccountSkill accountSkill);
    AccountSkill update(AccountSkill accountSkill);
}
