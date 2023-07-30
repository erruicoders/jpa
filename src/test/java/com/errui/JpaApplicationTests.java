package com.errui;

import com.errui.entity.Account;
import com.errui.entity.AccountDetail;
import com.errui.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaApplicationTests {

    @Autowired
    AccountRepository repository;

    @Test
    void findById() {
        repository.findById(1).ifPresent(System.out::println);
    }

    @Test
    void save() {
        Account account = new Account();
        account.setUsername("test");
        account.setPassword("000000");
        repository.save(account);
    }

    @Test
    void count() {
        System.out.println(repository.count());
    }

    @Test
    void findAccountByUsernameLike() {
        System.out.println(repository.findAccountByUsernameLike("%t%"));
    }

    @Test
    void findAccountsByUsernameLike() {
        System.out.println(repository.findAccountsByUsernameLike("%te%"));
    }

    @Test
    void findAccountsByIdGreaterThanOrderByIdDesc() {
        System.out.println(repository.findAccountsByIdGreaterThanOrderByIdDesc(2));
    }

    @Test
    void findAccountsByIdGreaterThanOrderByIdAsc() {
        System.out.println(repository.findAccountsByIdGreaterThanOrderByIdAsc(2));
    }

    @Test
    void findAccountsByUsernameAndId() {
        System.out.println(repository.findAccountsByUsernameAndId("小吴", 6));
    }

    @Test
    void existsAccountByUsername() {
        Boolean existsed = repository.existsAccountByUsername("小吴");
        if (existsed) {
            System.out.println("存在该用户");
        }
    }

    @Test
    void existsAccountById() {
        Boolean existsed = repository.existsAccountById(6);
        if (existsed) {
            System.out.println("存在该用户");
        }
    }

    @Test
    @Transactional
    void findByIdOneToOne() {
        Account account = repository.findById(1).get();
        System.out.println(account);
        System.out.println(account.getDetail());
    }

    @Test
    void addAccount(){
        Account account = new Account();
        account.setUsername("Nike");
        account.setPassword("123456");
        AccountDetail detail = new AccountDetail();
        detail.setAddress("重庆市渝中区解放碑");
        detail.setPhone("1234567890");
        detail.setEmail("73281937@qq.com");
        detail.setRealName("张三");
        account.setDetail(detail);
        account = repository.save(account);
        System.out.println("插入时，自动生成的主键ID为："+account.getId()+"，外键ID为："+account.getDetail().getId());
    }

    @Test
    void deleteById() {
        repository.deleteById(7);
    }

    @Transactional
    @Test
    void test() {
        repository.findById(1).ifPresent(account -> {
            account.getScoreList().forEach(System.out::println);
        });
    }

    @Test
    @Transactional
    void findByIdOneToMany() {
        repository.findById(1).ifPresent(System.out::println);
    }

    @Transactional
    @Test
    void findByIdManyToOne() {
        repository.findById(1).ifPresent(account -> {
            account.getScoreList().forEach(score -> {
                System.out.println("课程名称："+score.getSubject().getName());
                System.out.println("得分："+score.getScore());
                System.out.println("任课教师："+score.getSubject().getTeacher());
            });
        });
    }

    @Test
    void updateAccount(){
        repository.updatePasswordById(1, "654321");
    }

}
