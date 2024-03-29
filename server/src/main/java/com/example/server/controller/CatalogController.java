package com.example.server.controller;

import com.example.server.entity.Product;
import com.example.server.entity.User;
import com.example.server.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final ProductService productService;
//    private final CartRecordService cartRecordService;

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

//    @PostMapping("api/catalog/add")
//    public String addCartRec(@RequestParam Integer productId, @RequestParam Integer amountProduct, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        Product product = productService.getProduct(productId);
//        List<CartRecord> userCartRecords = cartRecordService.findAllCartRecordsById(user.getId());
//        for (CartRecord userCartRecord : userCartRecords) {
//            if (Objects.equals(userCartRecord.getProduct(), product)) {
//                userCartRecord.setAmountProduct(userCartRecord.getAmountProduct() + amountProduct);
//                cartRecordService.addCartRecord(userCartRecord);
//                return "redirect:/catalog";
//            }
//        }
//        cartRecordService.addCartRecord(user.getId(), product, amountProduct);
//        return "redirect:/catalog";
//    }
}
