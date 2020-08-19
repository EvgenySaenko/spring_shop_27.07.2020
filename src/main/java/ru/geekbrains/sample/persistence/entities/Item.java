package ru.geekbrains.sample.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends AbstractEntity{

    private String name;

    private int price;

    @Column(name = "in_stock")
    private boolean inStock;
}
