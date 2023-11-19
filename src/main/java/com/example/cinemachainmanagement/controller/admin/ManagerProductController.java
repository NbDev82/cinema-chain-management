package com.example.cinemachainmanagement.controller.admin;

import com.example.cinemachainmanagement.DTO.ProductCategoryDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/get_list_product")
    public String getListProduct(Model model) {
        try {
            List<ProductDTO> productsmanager = productService.getListProduct();
            model.addAttribute("productsmanager", productsmanager);
            return "manager_product_list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đặt hàng: " + e.getMessage());
            return "error_view";
        }
    }

    @GetMapping("")
    public String pageAddOrEdit(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        return "add_product";
    }


    @ModelAttribute(name = "CATEGORYS")
    private List<ProductCategoryDTO> getListProductCategory() {
        return productService.getListCategory();
    }

    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute(name = "productDTO") ProductDTO productDTO, @RequestParam(name = "image_multipart") MultipartFile image_multipart) {
        try {

            // Lưu hình ảnh vào thư mục
            Path path = Paths.get("src/main/resources/static/");
            InputStream inputStream = image_multipart.getInputStream();
            Files.copy(inputStream, path.resolve(image_multipart.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            // lưu đối tương produt vào database
            productDTO.setImage(image_multipart.getOriginalFilename());
            productService.addProduct(productDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return "error_view";
        }
        return "success";
    }

    @GetMapping("/get_product_edit/{id}")
    private String getProductEdit(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "get_product_edit";
    }

    @PostMapping("/submitEditForm")
    private String editProduct(@RequestParam(name = "name") String name,
                               @RequestParam(name = "quantity") int quantity,
                               @RequestParam(name = "price") int price,
                                @RequestParam(name = "id") Long id)
                                {

        try {

            ProductDTO productEditDTO = new ProductDTO(id,name,price,quantity);
            productService.editProduct(productEditDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return "error_view";
        }
        return "success";
    }


    @GetMapping("/delete/{id}")
    private String deleteProduct(@PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
        return "redirect:/manager/get_list_product";
    }


}
