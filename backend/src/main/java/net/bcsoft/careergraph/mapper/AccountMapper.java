package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account selectById(Long id);
    Account insert(Account account);
    Account update(Account account);
}
