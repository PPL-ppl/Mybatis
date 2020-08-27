package org.example.Resposty;

import org.example.Entiy.Classes;

public interface ClassesRepository {
    Classes findById(long id);
    Classes findByIdLazy(long id);
}
