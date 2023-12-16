package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.TheaterRoomDTO;
import com.example.cinemachainmanagement.DTO.TicketDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.model.CartItem;
import com.example.cinemachainmanagement.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private TicketService ticketService;

    @GetMapping("/get_list_product")
    public String getListProduct(Model model, HttpSession session) {
        try {
            TheaterRoomDTO room = (TheaterRoomDTO) session.getAttribute("room");
            TicketDTO ticket = (TicketDTO) session.getAttribute("ticket");

            model.addAttribute("room", room);
            model.addAttribute("ticket", ticket);
//
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
    private String pay(@RequestParam(name = "selectPrice") String price,
//                       @RequestParam(name = "selectPriceProduct")String selectPriceProduct,
                       @RequestParam("listProductBuy") String listProductBuy,
                       HttpSession session,
                       Model model) {
        try {
            //model.addAttribute("total_price",price);
            Customer customer = (Customer) session.getAttribute("customer");
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductDTO> dataListProductBuy = objectMapper.readValue(listProductBuy, new TypeReference<List<ProductDTO>>() {
            });
            // lấy tickets qua session
            List<Ticket> tickets = (List<Ticket>) session.getAttribute("tickets");
            if (tickets == null || tickets.isEmpty()) {
                return "redirect:/customer_authentication/login";
            }
            //lấy Ticker vào de quản lý bởi persistence context của Hibernate
            List<Ticket> ticketList = new ArrayList<>();
            for (Ticket t : tickets) {
                Optional<Ticket> ticket = ticketService.findTicketById(t.getTicketId());
                if (ticket != null) {
                    ticketList.add(ticket.get());
                }
            }
            Orders orders = new Orders();
            snackOrderService.addSnackOrder(orders, Integer.parseInt(price), ticketList, dataListProductBuy, customer);
            session.setAttribute("orders", orders);

        } catch (Exception e) {
            return "error_view";
        }
        TheaterRoomDTO room = (TheaterRoomDTO) session.getAttribute("room");
        TicketDTO ticket = (TicketDTO) session.getAttribute("ticket");

        model.addAttribute("room", room);
        model.addAttribute("ticket", ticket);
        model.addAttribute("total_price",price);
        return "payment";
    }
}
