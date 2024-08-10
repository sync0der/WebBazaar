package com.syncoder.webbazaar.services;

import com.syncoder.webbazaar.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private long ID = 0;

    {
        products.add(new Product(++ID, "PlayStation 5", "simple description", 67.342, "Moscow", "Andrey"));
        products.add(new Product(++ID, "Iphone 15", "simple description", 47.342, "Pitsburgh", "syncoder"));
    }

    public List<Product> listProducts() {
        return products;
    }

    public void saveProduct(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public Product getProductById(Long id) {
        return products.stream().filter(prod -> Objects.equals(prod.getId(), id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
