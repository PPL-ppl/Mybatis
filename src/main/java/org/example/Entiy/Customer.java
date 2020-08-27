package org.example.Entiy;

import lombok.Data;

import java.util.List;

/**
 * @author 19424
 */
@Data
public class Customer {
    private long id;
    private String name;
    private List<Goods> goods;
}
