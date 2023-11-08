package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Product;
import org.springframework.ui.Model;
import com.example.cinemachainmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-list-products")
    public String getListProduct(Model model) {
        try {
            List<ProductDTO> products = productService.getListProduct();
            model.addAttribute("products", products);
            return "product-list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đặt hàng: " + e.getMessage());
            return "error-view";
        }
    }

//    @PostMapping("/add-product")
//    public String addProduct(@ModelAttribute ProductDTO request_product, @ModelAttribute ProductCategoryDTO request_product_category, Model model) {
//        try {
//            // Gọi service để xử lý việc đặt đơn hàng
//            orderService.addProduct(request_product, request_product_category);
//            model.addAttribute("message", "Đặt hàng thành công!");
//            return "success-view";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi khi đặt hàng: " + e.getMessage());
//            return "error-view";
//        }
//    }

}