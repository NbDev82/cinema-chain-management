package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ProductCategoryDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.Mapper.ProductCategoryMapper;
import com.example.cinemachainmanagement.Mapper.ProductMapper;
import com.example.cinemachainmanagement.entities.Product;
import com.example.cinemachainmanagement.entities.ProductCategory;
import com.example.cinemachainmanagement.repositories.ProductCategoryRepository;
import com.example.cinemachainmanagement.repositories.ProductRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImp implements ProductService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public void addProduct(ProductDTO productDTO, Long productCategoryID) {

        Product product = ProductMapper.toEntity(productDTO);
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryID).orElse(null);
        if (productCategory != null) {
            product.setProduct_category(productCategory);
            productRepository.save(product);
        } else {
            System.out.println("Error");
            return;
        }
    }

    @Override
    public List<ProductDTO> getListProduct() {
        List<Product> listproduct = productRepository.findAll();
        return ProductMapper.toDTOList(listproduct);
    }

    @Override
    public List<ProductCategoryDTO> getListCategory() {
        List<ProductCategory> listproductcategory = productCategoryRepository.findAll();
        return ProductCategoryMapper.toDTOList(listproductcategory);
    }
}

