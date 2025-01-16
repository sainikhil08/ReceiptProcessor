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

        // initialize Reward points
        int points = 0;

        //count one point for each alphanumeric character in the retailer name
        String retailerName = receipt.getRetailer();
        for(int i=0;i<retailerName.length();i++){
            char ch = retailerName.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                points+=1;
            }
        }

        //add 50 points if the total is a round dollar amount with no cents
        double total = receipt.getTotal();
        if ( total == Math.floor(total)) {
            points += 50;
        }

        //add 25 points if the total is a multiple of 0.25
        if (total % 0.25 == 0) {
            points += 25;
        }

        //If the trimmed length of the item description is a multiple of 3,
        //multiply the price by 0.2 and round up to the nearest integer
        List<Item> items = receipt.getItems();
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            double price = item.getPrice();
            if (description.length() % 3 == 0) {
                points += (int) Math.ceil(price * 0.2);
            }
        }

        //add 5 points for every pair on the receipt.
        points+=(items.size()/2)*5;

        //6 points if the day in the purchase date is odd
        if (receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            points += 6;
        }

        //10 points if the time of purchase is after 2:00pm and before 4:00pm
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
