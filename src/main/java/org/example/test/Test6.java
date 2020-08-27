package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Account;
import org.example.Resposty.AccountRepository;

import java.io.InputStream;

public class Test6 {
    public static void main(String[] args) {
       /* 一级缓存*/
        InputStream inputStream = test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //inputStream
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = build.openSession();
        AccountRepository mapper = sqlSession.getMapper(AccountRepository.class);
        Account byId = mapper.findById(9L);
        System.out.println(byId);
        Account byId1 = mapper.findById(9L);
        //在一个sqlSession中 同一个查询只执行一次
        System.out.println(byId1);
        sqlSession.close(); //关闭后重新执行sql语句查询
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(AccountRepository.class);
        Account byId2 = mapper.findById(9L);
        System.out.println(byId2);
        /*二级缓存*/
        //开启查询只执行一次
    }
}
