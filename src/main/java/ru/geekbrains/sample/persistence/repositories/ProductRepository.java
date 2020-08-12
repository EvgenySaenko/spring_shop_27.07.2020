package ru.geekbrains.sample.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sample.persistence.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    List<Product>findAll();
    Optional<Product> findById(UUID id);
    List<Product>findAllByCostGreaterThan(int minScore);




    //@Query("SELECT name FROM Product p WHERE p.cost=300")
    //@Query(value = "SELECT name FROM product p WHERE p.cost=300",nativeQuery = true)
    List<Product> findAllByIdAndNameAndCost(UUID id, String name, Integer cost);

}
