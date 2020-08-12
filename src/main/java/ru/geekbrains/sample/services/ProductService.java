package ru.geekbrains.sample.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.sample.dto.ProductDTO;
import ru.geekbrains.sample.exceptions.ProductNotFoundExceptions;
import ru.geekbrains.sample.persistence.entities.Product;
import ru.geekbrains.sample.persistence.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    //вернет список товаров
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    //вернет 10 товаров на странице, но отфильтрованой спецификацией
    public Page<Product> findAll(Specification<Product> specification, Integer page){
        if (page < 1){
            page = 1;
        }
        //можно отдавать сформированную specification так и null - просто не будет отфильтрован по specification
        return productRepository.findAll(specification,PageRequest.of(page-1,10));
    }

    //вернет список Page(тот же List, может хранить номер страницы,
    // размер страницы,итд)
    public Page<Product> getByPage(int pageNumber, int pageSize){
        return productRepository.findAll(PageRequest.of(pageNumber,pageSize));
    }


    //вернет товар по id
    public Product getProduct(UUID id) {//бросим наше исключение если нет продукта с запрошенным id
        return productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundExceptions("Can't found product with id=" + id));
    }
    //добавить один
    public void saveProduct(Product product){
             productRepository.save(product);
    }

    //изменить данные о продукте сохранив их
    public Product saveOrUpdateProduct(Product product){
        return productRepository.save(product);
    }


    //@Transactional уже зашита сюда ненадо указывать
    public void save(ProductDTO productDTO) {
        productRepository.save(
                Product.builder()
                        .name(productDTO.getName())
                        .cost(productDTO.getCost())
                        .build()
        );
    }

//    public Product getProduct(UUID id){
//        Optional<Product>productOptional = productRepository.findById(id);
        //если даже поймаем НПЕ метод просто выполнится и программа продолжит работать
//        if (productOptional.isPresent()){
//            Product product = productOptional.get();
//            System.out.println(product);
//        }
//    }


}