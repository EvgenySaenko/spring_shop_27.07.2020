package ru.geekbrains.sample.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.sample.persistence.entities.Book;
import ru.geekbrains.sample.persistence.entities.Shopuser;

import java.util.List;
import java.util.UUID;

public interface ShopuserRepository extends JpaRepository<Shopuser, UUID> {
    Shopuser findByLogin(String login);
    boolean existsByLogin(String login);
    public List<Shopuser> findAll();

}