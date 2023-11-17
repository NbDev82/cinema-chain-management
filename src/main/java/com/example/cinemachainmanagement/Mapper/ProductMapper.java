package com.example.cinemachainmanagement.Mapper;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getProductId());
        productDTO.setName(product.getProductName());
        productDTO.setPrice((int) product.getProductPrice());
        productDTO.setImage(product.getProductImage() );
        productDTO.setQuantity(product.getProductQuantity());
        productDTO.setDescription(product.getProductDescription());
        productDTO.setProductCategoryId(product.getProduct_category().getProductCategoryID());

        // Sao chép các thuộc tính khác từ Entity sang DTO
        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getId());
        product.setProductName(productDTO.getName());
        product.setProductPrice(productDTO.getPrice());
        product.setProductImage(productDTO.getImage());
        product.setProductQuantity(productDTO.getQuantity());
        product.setProductDescription(productDTO.getDescription());
        // Sao chép các thuộc tính khác từ DTO sang Entity
        return product;
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(toDTO(product));
        }
        return productDTOs;
    }

    public static List<Product> toEntityList(List<ProductDTO> productDTOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs) {
            products.add(toEntity(productDTO));
        }
        return products;
    }
}
