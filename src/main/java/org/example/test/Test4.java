package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Classes;
import org.example.Entiy.Student;
import org.example.Resposty.ClassesRepository;
import org.example.Resposty.StudentRepository;

import java.io.InputStream;

/*
 * 一对多
 * */
public class Test4 {
    public static void main(String[] args) {
        InputStream inputStream = test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);//inputStream
        SqlSession sqlSession = build.openSession();
        ClassesRepository mapper = sqlSession.getMapper(ClassesRepository.class);
        Classes classes = mapper.findById(1);
        System.out.println(classes);
    }
}
