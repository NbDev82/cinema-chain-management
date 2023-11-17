package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ProductCategoryDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void addProduct(ProductDTO request_product);
    List<ProductDTO> getListProduct();

    List<ProductCategoryDTO> getListCategory();

    String editProduct(ProductDTO request_product);

    ProductDTO getProductById(String id);

    void deleteProduct(String product_id);


}
