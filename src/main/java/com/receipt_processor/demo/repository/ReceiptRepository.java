package com.receipt_processor.demo.repository;

import com.receipt_processor.demo.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {

    private final ConcurrentHashMap<String, Receipt> receiptStore = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> receiptPointsStore = new ConcurrentHashMap<>();

    public void save(Receipt receipt, int points) {
        receiptStore.put(receipt.getId(), receipt);
        receiptPointsStore.put(receipt.getId(), points);
    }

    public int findById(String id) {
        if(receiptPointsStore.containsKey(id)) {
            return receiptPointsStore.get(id);
        }
        return -1;
    }

}
