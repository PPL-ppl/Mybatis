package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Customer;
import org.example.Entiy.Goods;
import org.example.Resposty.CustomerRepository;
import org.example.Resposty.GoodsRepository;

import java.io.InputStream;

public class Test5 {
    public static void main(String[] args) {
        InputStream inputStream = test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);//inputStream
        SqlSession sqlSession = build.openSession();
        /*CustomerRepository mapper = sqlSession.getMapper(CustomerRepository.class);
        Customer byId = mapper.findById(1);
        System.out.println(byId);*/

        GoodsRepository mapper = sqlSession.getMapper(GoodsRepository.class);
        Goods byId = mapper.findById(1);
        System.out.println(byId);
    }
}
