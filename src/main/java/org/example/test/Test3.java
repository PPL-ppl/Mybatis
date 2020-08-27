package org.example.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Entiy.Student;
import org.example.Resposty.AccountRepository;
import org.example.Resposty.StudentRepository;

import java.io.InputStream;

//多级查询测试 一对多
public class Test3 {
    public static void main(String[] args) {
        InputStream inputStream = test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);//inputStream
        SqlSession sqlSession = build.openSession();
        StudentRepository mapper = sqlSession.getMapper(StudentRepository.class);
        Student student = mapper.FindById(1);
        System.out.println(student);

        /*延迟加载*/
        Student student1 = mapper.FindByIdLazy(1);
        System.out.println(student1.getClasses().getName());
        //System.out.println(student1);
    }
}
