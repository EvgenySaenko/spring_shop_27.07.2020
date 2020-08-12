package ru.geekbrains.sample.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.sample.persistence.entities.Product;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ProductSpecifications {
    //GEThan - (Greater or Equals Then)-цена должна быть больше или равна minCost
    public static Specification<Product> costGEThan(int minCost) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }
    //LEThan - (Lesser or Equals Then)-цена должна быть меньше или равна maxCost
    public static Specification<Product> costLEThan(int maxCost) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }

}
