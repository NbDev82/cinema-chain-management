package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.DTO.ShoppingCartItemDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Product;
import com.example.cinemachainmanagement.entities.ShoppingCartItem;
import com.example.cinemachainmanagement.entities.SnackOrder;
import com.example.cinemachainmanagement.model.CartItem;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.service.CartService;
import com.example.cinemachainmanagement.service.ShoppingCartItemService;
import com.example.cinemachainmanagement.service.SnackOrderService;
import org.springframework.ui.Model;
import com.example.cinemachainmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class BuyProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private SnackOrderService snackOrderService;
    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @GetMapping("/get_list_product")
    public String getListProduct(Model model) {
        try {
            List<ProductDTO> productsmanager = productService.getListProduct();
            model.addAttribute("productsmanager", productsmanager);
            return "customer_product_list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đặt hàng: " + e.getMessage());
            return "error_view";
        }
    }


    @GetMapping("/addtocart/{id}")
    private String addToCart(@PathVariable(name = "id") Long productId, @RequestParam(name = "quantity") Integer quantity) {
        try {
            ProductDTO productDTO = productService.getProductById(String.valueOf(productId));
            if(productDTO != null)
            {
                CartItem cartItem = new CartItem();
                cartItem.setProductId(productDTO.getId());
                cartItem.setName(productDTO.getName());
                cartItem.setPrice(productDTO.getPrice());
                cartItem.setImg_url(productDTO.getImage());
                cartItem.setPty(quantity);
                cartService.add(cartItem);

                return "redirect:/customer/view-cart";
            }
            else
                return "error_view";

        }
        catch (Exception e){
            return "error_view";
        }

    }




    @GetMapping("/view-cart")
    private String viewCart (Model model){
        model.addAttribute("CART_ITEMS", cartService.getAllItem());
        model.addAttribute("TOTAL_PRICE", cartService.totalPrice(cartService.getAllItem()));
        return "customer_cart";
    }

    @GetMapping("/remove/{id}")
    private String removeItem(@PathVariable(name = "id") Long id) {
        cartService.remove(id);
        return "redirect:/customer/view-cart";
    }

    @PostMapping("/pay")
    private String pay(@RequestParam(name = "productId")List<Long> productIds, @RequestParam(name = "pty") String pty){


        try {
            SnackOrder snackOrder = new SnackOrder();
            snackOrderService.addSnackOrder(snackOrder);
            for (Long productId : productIds) {
                ShoppingCartItemDTO shoppingCartItemDTO = new ShoppingCartItemDTO(productId,pty);
                shoppingCartItemService.addShoppingCartItem(shoppingCartItemDTO,snackOrder.getSnackOrderId(),productId);
            }
        }
        catch (Exception e)
        {
            return "error_view";
        }
        return "success";
    }
}