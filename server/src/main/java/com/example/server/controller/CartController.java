package com.example.server.controller;

import com.example.server.entity.CartRecord;
import com.example.server.entity.User;
import com.example.server.service.CartRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartRecordService cartRecordService;
    @GetMapping("api/cart")
    public @ResponseBody String cart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<CartRecord> cartRecords = cartRecordService.findAllCartRecordsById(user.getId());
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(cartRecords);
        } catch (JsonProcessingException e) {
            return "Товары закончились С:";
        }

    }
    @PostMapping("api/cart/delete")
    public ResponseEntity<?> delCartRec(@RequestBody Map<String, String> data) {
        cartRecordService.deleteCartRecord(Integer.valueOf(data.get("recordId")));
        return ResponseEntity.ok("Товар удалён");
    }
}