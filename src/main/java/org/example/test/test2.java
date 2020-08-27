package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Account;
import org.example.Resposty.AccountRepository;

import java.io.InputStream;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        //加载MyBatis配置文件
        InputStream inputStream = test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);//inputStream
        SqlSession sqlSession = build.openSession();
        AccountRepository mapper = sqlSession.getMapper(AccountRepository.class);
        //mapper.deleteById(2l);

        /*List<Account> all1 = mapper.findAll();
        for (Account account: all1) {
            System.out.println(account);
        }*/
        //mapper.save(new Account(2,"李四","123456",21));
        //Account byId = mapper.findById(1l);
        //System.out.println(byId);

        /*List<Account> all = mapper.findAll();
        for (Account account: all) {
            System.out.println(account);
        }*/
        /*Account account = mapper.findById(5L);
        account.setAge(12);
        mapper.update(account);*/
        Account account = mapper.findById(10L);
        account.setPassword("12345678910");
        mapper.update(account);
        mapper.findByNameAndAge("李四", 23);
        sqlSession.commit();
        sqlSession.close();
    }
}
