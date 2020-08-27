package org.example.Resposty;

import org.example.Entiy.Goods;

public interface GoodsRepository {
    Goods findById(long id);
}
