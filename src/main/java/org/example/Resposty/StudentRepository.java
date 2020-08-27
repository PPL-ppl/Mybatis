package org.example.Resposty;

import org.example.Entiy.Student;

public interface StudentRepository {
    Student FindById(long id);
    Student FindByIdLazy(long id);
}
