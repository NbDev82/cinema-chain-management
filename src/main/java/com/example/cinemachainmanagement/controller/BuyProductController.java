package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.DTO.ShoppingCartItemDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Product;
import com.example.cinemachainmanagement.entities.ShoppingCartItem;
import com.example.cinemachainmanagement.entities.SnackOrder;
import com.example.cinemachainmanagement.model.CartItem;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    @Autowired
    private CustomerService customerService;

    @GetMapping("/get_list_product")
    public String getListProduct(Model model, HttpSession session) {
        try {
            String price = (String) session.getAttribute("price");

            List<ProductDTO> productsmanager = productService.getListProduct();
            model.addAttribute("productsmanager", productsmanager);
            model.addAttribute("price", price);
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
            if (productDTO != null) {
                CartItem cartItem = new CartItem();
                cartItem.setProductId(productDTO.getId());
                cartItem.setName(productDTO.getName());
                cartItem.setPrice(productDTO.getPrice());
                cartItem.setImg_url(productDTO.getImage());
                cartItem.setQty(quantity);
                cartService.add(cartItem);

                return "redirect:/customer/view-cart";
            } else
                return "error_view";

        } catch (Exception e) {
            return "error_view";
        }

    }

    @GetMapping("/view-cart")
    private String viewCart(Model model) {
        model.addAttribute("CART_ITEMS", cartService.getAllItem());
        model.addAttribute("TOTAL_PRICE", cartService.totalPrice(cartService.getAllItem()));
        return "customer_cart";
    }

    @GetMapping("/remove/{id}")
    private String removeItem(@PathVariable(name = "id") Long id) {
        cartService.remove(id);
        return "redirect:/customer/view-cart";
    }

    @GetMapping("/clear")
    private String clear() {
        cartService.clear();
        return "redirect:/customer/view-cart";
    }

    @PostMapping("/pay_product")
    private String pay(@RequestParam(name = "selectPrice") String price, HttpSession session, Model  model) {
        try {
            // lấy customer qua session
            Customer customer = (Customer) session.getAttribute("customer");
            CustomerDTO customerDTO = customerService.getCustomerById(customer.getCustomerId());
            model.addAttribute("total_price",price);
            if (customerDTO == null) {
                return "login";
            }
            // set lại Ticker(totalPrice)
            // câp nhật lại tiền trên Ticker sau khi mua bắp nước


            //lưu snackOrder
            //SnackOrder snackOrder = new SnackOrder();
//                ShoppingCartItemDTO shoppingCartItemDTO = new ShoppingCartItemDTO();
//                shoppingCartItemService.addShoppingCartItem(shoppingCartItemDTO,snackOrder.getSnackOrderId(),cartItem.getProductId());
            //snackOrderService.addSnackOrder(snackOrder, total_price, customerDTO);
        } catch (Exception e) {
            return "error_view";
        }
        return "payment";
    }


}