package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Account;
import org.example.Resposty.AccountRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test7 {
    public static void main(String[] args) {
        InputStream inputStream = Test7.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);//inputStream
        SqlSession sqlSession = build.openSession();
        Account account = new Account();
        List<Long> ids=new ArrayList<>();
        ids.add(9L);
        ids.add(10L);
        ids.add(11L);
        account.setIds(ids);
        /*account.setId(0);
        account.setUsername("李四");
        account.setPassword("123456789");//没有这个条件查询满足其他三个条件的信息
        account.setAge(22);*/
        AccountRepository mapper = sqlSession.getMapper(AccountRepository.class);
        List<Account> byids = mapper.findByids(account);
        System.out.println(byids);
        /*Account byAccount = mapper.findByAccount(account);
        System.out.println(byAccount);*/
    }
}
