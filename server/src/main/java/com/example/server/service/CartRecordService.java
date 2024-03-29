package com.example.server.service;

import com.example.server.entity.CartRecord;
import com.example.server.entity.Product;
import com.example.server.repository.CartRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartRecordService {
    private final CartRecordRepository cartRecordRepository;

    public void addCartRecord(Integer userId, Product product , Integer amountProduct){
        CartRecord newCartRecord = new CartRecord(0, userId, product, amountProduct);
        cartRecordRepository.save(newCartRecord);
    }

    public List<CartRecord> findAllCartRecordsById(Integer userId){
        return cartRecordRepository.findAllByUserId(userId);
    }

}
