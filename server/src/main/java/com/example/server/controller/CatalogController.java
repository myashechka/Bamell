package com.example.server.controller;

import com.example.server.entity.CartRecord;
import com.example.server.entity.Product;
import com.example.server.entity.User;
import com.example.server.service.CartRecordService;
import com.example.server.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final ProductService productService;
    private final CartRecordService cartRecordService;

    @GetMapping("api/catalog")
    public @ResponseBody String catalog() {
        List<Product> products = productService.getAllByVisibleProduct(true);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            return "Товары закончились С:";
        }
    }

    @PostMapping("api/catalog/add")
    public String addCartRec(@RequestBody Map<String, String> data, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Product product = productService.getProduct(Integer.valueOf(data.get("productId")));
        List<CartRecord> userCartRecords = cartRecordService.findAllCartRecordsById(user.getId());
        for (CartRecord userCartRecord : userCartRecords) {
            if (Objects.equals(userCartRecord.getProduct(), product)) {
                userCartRecord.setAmountProduct(userCartRecord.getAmountProduct() + Integer.parseInt(data.get("quantity")));
//                cartRecordService.addCartRecord(userCartRecord);
                return "redirect:/catalog";
            }
        }
        cartRecordService.addCartRecord(user.getId(), product, Integer.parseInt(data.get("quantity")));
        return "redirect:/catalog";
    }
}
