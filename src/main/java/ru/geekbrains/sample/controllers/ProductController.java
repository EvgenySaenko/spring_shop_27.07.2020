package ru.geekbrains.sample.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.sample.dto.ProductDTO;
import ru.geekbrains.sample.persistence.entities.Product;
import ru.geekbrains.sample.services.ProductService;
import ru.geekbrains.sample.specifications.ProductSpecifications;
import ru.geekbrains.sample.utils.ProductFilter;

import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")//страница товаров постранично
    public String showAll(Model model, @RequestParam Map<String,String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("page","1"));

        //создаем фильтр и кладем туда мапу requestParams
        ProductFilter productFilter = new ProductFilter(requestParams);


        //выводим при помощи специикаций(выбирая любую перегрузку мтеода findAll())
        //достаем из фильтра спецификацию,чтобы найти все товары
        model.addAttribute("products",productService.findAll(productFilter.getSpec(),pageNumber));
        //и закидываем в модель filterDef  и потом в all_product этот кусочек будет добавляться при переходе на предыдущую страницу
        model.addAttribute("filterDef",productFilter.getFilterDefinition().toString());
        return "all_product";
    }

    @GetMapping("/{id}")//профиль товара
    public String getProductPage(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getProduct(UUID.fromString(id)));
        return "product";
    }

    @GetMapping("/add")//форма добавления нового товара
    public String showAddFormNewProduct(){
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProducts(@ModelAttribute Product newProduct){//@ModelAttribute сам собирает новый объект
        productService.saveProduct(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")//редактируем товар
    public String showEditForm(@PathVariable String id, Model model){
        model.addAttribute("product", productService.getProduct(UUID.fromString(id)));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct){
        productService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products/";
    }

    @PostMapping
    public void saveOne(ProductDTO productDTO) {
        productService.save(productDTO);
    }
}