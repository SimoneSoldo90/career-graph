package net.bcsoft.careergraph.dao;

import net.bcsoft.careergraph.entity.Account;

import java.util.List;

public interface UserDAO {
    List<Account> selectAll();
}
