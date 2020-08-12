package ru.geekbrains.sample.utils;

import ru.geekbrains.sample.persistence.entities.Product;
import ru.geekbrains.sample.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
//строит по указанным параметрам наш фильтер

@Getter
public class ProductFilter {
    private Specification<Product> spec;//сохраняет указанную специикацию(указанные параметры)
    //все эти параметры собираются в filterDefinition = min=50 max=100 =>формирует кусок пути=> &min=50&max=100
    private StringBuilder filterDefinition;

    //отдаем мапу со списком всех реквест параметров
    public ProductFilter(Map<String, String> map) {
        this.spec = Specification.where(null);//создаем пустую спецификацию
        this.filterDefinition = new StringBuilder();//и пустой фильтр

        //если в мапе есть min_cost и он не пустой
        if (map.containsKey("min_cost") && !map.get("min_cost").isEmpty()) {
            int minCost = Integer.parseInt(map.get("min_cost"));//парсим

            //говорим что наши товары должны быть дороже чем minCost
            spec = spec.and(ProductSpecifications.costGEThan(minCost));
            //и аппендим к filterDefinition этот minCost
            filterDefinition.append("&min_cost=").append(minCost);
        }
        //если в мапе есть max_cost и он не пустой
        if (map.containsKey("max_cost") && !map.get("max_cost").isEmpty()) {
            int maxCost = Integer.parseInt(map.get("max_cost"));//парсим его
            //говорим что наши товары должны быть дешевле чем maxCost
            spec = spec.and(ProductSpecifications.costLEThan(maxCost));
            filterDefinition.append("&max_cost=").append(maxCost);//добавили кусочек
        }
    }
}
