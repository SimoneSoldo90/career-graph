package net.bcsoft.careergraph.mapper;

import net.bcsoft.careergraph.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account> selectAll();
    Account selectById(Long id);
    Account insert(Account account);
    Account update(Account account);
}
