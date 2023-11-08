package com.example.cinemachainmanagement.Mapper;

import com.example.cinemachainmanagement.DTO.ProductCategoryDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Product;
import com.example.cinemachainmanagement.entities.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryMapper {
    public static ProductCategoryDTO toDTO(ProductCategory productcategory) {
        ProductCategoryDTO productcategoryDTO = new ProductCategoryDTO();
        productcategoryDTO.setId(productcategory.getProductCategoryID());
        productcategoryDTO.setCategoryName(productcategory.getCategoryName());

        // Sao chép các thuộc tính khác từ Entity sang DTO
        return productcategoryDTO;
    }

    public static ProductCategory toEntity(ProductCategoryDTO productcategoryDTO) {
        ProductCategory productcategory = new ProductCategory();
        productcategory.setProductCategoryID(productcategoryDTO.getId());
        productcategory.setCategoryName(productcategoryDTO.getCategoryName());

        // Sao chép các thuộc tính khác từ DTO sang Entity
        return productcategory;
    }

    public static List<ProductCategoryDTO> toDTOList(List<ProductCategory> productCategories) {
        List<ProductCategoryDTO> productCategoryDTOs = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductCategoryDTO productCategoryDTO = toDTO(productCategory);
            productCategoryDTOs.add(productCategoryDTO);
        }
        return productCategoryDTOs;
    }

    public static List<ProductCategory> toEntityList(List<ProductCategoryDTO> productCategoryDTOs) {
        List<ProductCategory> productCategories = new ArrayList<>();
        for (ProductCategoryDTO productCategoryDTO : productCategoryDTOs) {
            ProductCategory productCategory = toEntity(productCategoryDTO);
            productCategories.add(productCategory);
        }
        return productCategories;
    }


}
