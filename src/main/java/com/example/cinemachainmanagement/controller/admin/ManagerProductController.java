package com.example.cinemachainmanagement.controller.admin;

import com.example.cinemachainmanagement.DTO.ProductCategoryDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-list-product")
    public String getListProduct(Model model) {
        try {
            List<ProductDTO> productsmanager = productService.getListProduct();
            model.addAttribute("productsmanager", productsmanager);
            return "manager-product-list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đặt hàng: " + e.getMessage());
            return "error-view";
        }
    }

    @GetMapping("/add-product")
    public String showProductForm(Model model) {
        try {
            List<ProductCategoryDTO> products_category_manager = productService.getListCategory();
            model.addAttribute("products_category_manager", products_category_manager);
            return "add-product";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đặt hàng: " + e.getMessage());
            return "error-view";
        }
    }



    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("description") String description,
                             @ModelAttribute("name") String name,
                             @ModelAttribute("price") Long price,
                             @ModelAttribute("quantity") int quantity,
                             @RequestParam("image_url") MultipartFile image,
                             @ModelAttribute("categoryId") Long categoryId,
                             Model model) throws IOException {
        try {
            // Lưu hình ảnh vào thư mục
            Path path = Paths.get("uploads/");
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            // lưu đối tương produt vào database
            ProductDTO productDTO = new ProductDTO(name,description,price, image.getOriginalFilename(),quantity);
            productService.addProduct(productDTO,categoryId);

        } catch (Exception e) {
            e.printStackTrace();
            return "error-view";
        }
        return "success";
    }
}
