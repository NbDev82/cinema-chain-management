package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.DTO.ProductCategoryDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.Mapper.ProductCategoryMapper;
import com.example.cinemachainmanagement.Mapper.ProductMapper;
import com.example.cinemachainmanagement.entities.Product;
import com.example.cinemachainmanagement.entities.ProductCategory;
import com.example.cinemachainmanagement.repositories.ProductCategoryRepository;
import com.example.cinemachainmanagement.repositories.ProductRepository;
import com.example.cinemachainmanagement.service.ProductService;
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
    public void addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        ProductCategory productCategory = productCategoryRepository.findById(productDTO.getProductCategoryId()).orElse(null);
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
        List<ProductCategory> list_product_category = productCategoryRepository.findAll();
        return ProductCategoryMapper.toDTOList(list_product_category);
    }

    @Override
    public String editProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product existingProduct = productRepository.findById(productDTO.getId()).orElse(null);

        if (existingProduct != null){
            existingProduct.setProductName(productDTO.getName());
            existingProduct.setProductQuantity(productDTO.getQuantity());
            existingProduct.setProductPrice(productDTO.getPrice());
            productRepository.save(existingProduct);
            return "success";
        }

        System.out.println("Error");
        return "error-view";
    }


    @Override
    public ProductDTO getProductById(String product_id) {
        Product product = productRepository.findById(Long.valueOf(product_id)).orElse(null);
        if(product !=null){
            return ProductMapper.toDTO(product);

        }
        return null;
    }

    @Override
    public Product getProductByIdEntity(String product_id) {
        Product product = productRepository.findById(Long.valueOf(product_id)).orElse(null);
        if(product !=null){
            return product;
        }
        return null;
    }

    public void deleteProduct(String product_id) {
        try {
            Long id = Long.parseLong(product_id);
            productRepository.deleteById(id);
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi không thể chuyển đổi thành Long
            System.out.println("Invalid product_id format");
        }
    }
}

