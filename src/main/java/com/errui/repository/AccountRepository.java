package com.errui.repository;

import com.errui.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUsernameLike(String username);

    List<Account> findAccountsByUsernameLike(String username);

    List<Account> findAccountsByIdGreaterThanOrderByIdDesc(Integer id);

    List<Account> findAccountsByIdGreaterThanOrderByIdAsc(Integer id);

    List<Account> findAccountsByUsernameAndId(String username, Integer id);

    Boolean existsAccountByUsername(String username);

    Boolean existsAccountById(Integer id);

    @Transactional    //DML操作需要事务环境，可以不在这里声明，但是调用时一定要处于事务环境下
    @Modifying     //表示这是一个DML操作
    @Query("update Account set password = ?2 where id = ?1") //这里操作的是一个实体类对应的表，参数使用?代表，后面接第n个参数
    int updatePasswordById(int id, String newPassword);
}
