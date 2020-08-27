package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Account;

import java.io.InputStream;

//原生接口实现
public class test {
    public static void main(String[] args) {
        //加载MyBatis配置文件
        InputStream inputStream = test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);//inputStream
        SqlSession sqlSession = build.openSession();
        String statement = "org.example.Mapper.AccountMapper.save";
        Account account = new Account(1L, "张三", "123", 22);
        sqlSession.insert(statement, account);
        sqlSession.commit();
        sqlSession.close();
    }
}
