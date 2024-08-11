package com.syncoder.webbazaar.services;

import com.syncoder.webbazaar.models.Image;
import com.syncoder.webbazaar.models.Product;
import com.syncoder.webbazaar.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> listProducts(String title) {
        if (title != null)
            return productRepository.findByTitle(title);
        else
            return productRepository.findAll();
    }

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        Image image1 = new Image();
        Image image2 = new Image();
        Image image3 = new Image();

        image1.setPreviewImage(true);
        try {
            extractImage(product, file1, image1);
            extractImage(product, file2, image2);
            extractImage(product, file3, image3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("Saving new Product, Title: {}; Author: {}", product.getTitle(), product.getAuthor());

        product.setPreviewImageId(product.getImages().getFirst().getId());
        productRepository.save(product);
    }

    private void extractImage(Product product, MultipartFile file, Image image) throws IOException {
        if (file.getSize() != 0) {
            image = getImageEntity(file);
            product.addImageToProduct(image);
        }
    }


    private Image getImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found!!!"));
    }
}
