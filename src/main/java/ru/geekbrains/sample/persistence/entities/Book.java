package ru.geekbrains.sample.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {

    public String name;

    public Date created;

    public String description;

    public boolean available;

    public String author;
}
