package ru.geekbrains.sample.persistence.entities;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity{
    private String name;
    private int cost;
}
