package org.example.Resposty;

import org.example.Entiy.Account;

import java.util.List;

public interface AccountRepository {
    int save(Account account);

    int update(Account account);

    int deleteById(Long id);

    List<Account> findAll();

    Account findById(Long id);
    Account findByNameAndAge(String name,int age);
    Account findByAccount(Account account);
    List<Account> findByids(Account account);
}
