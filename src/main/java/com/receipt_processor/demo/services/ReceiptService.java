package com.receipt_processor.demo.services;

import com.receipt_processor.demo.model.Item;
import com.receipt_processor.demo.model.Receipt;
import com.receipt_processor.demo.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String processReceipt(Receipt receipt) {
        // Assign a unique ID
        String receiptId = UUID.randomUUID().toString();
        receipt.setId(receiptId);

        int points = 0;

        String retailerName = receipt.getRetailer();
        for(int i=0;i<retailerName.length();i++){
            char ch = retailerName.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                points+=1;
            }
        }

        double total = receipt.getTotal();
        if ( total == Math.floor(total)) {
            points += 50;
        }

        if (total % 0.25 == 0) {
            points += 25;
        }

        List<Item> items = receipt.getItems();
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            double price = item.getPrice();
            if (description.length() % 3 == 0) {
                points += (int) Math.ceil(price * 0.2);
            }
        }

        points+=(items.size()/2)*5;

        if (receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            points += 6;
        }

        LocalTime purchaseTime = receipt.getPurchaseTime();
        if (purchaseTime.isAfter(LocalTime.of(14, 0)) && purchaseTime.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        // Delegate call to the Repository
        receiptRepository.save(receipt, points);
        return receiptId;
    }

    public int getReceiptPoints(String id) {
        // Delegate call to the Repository
        return receiptRepository.findById(id);
    }

}
