package com.receipt_processor.demo.controller;

import com.receipt_processor.demo.model.Receipt;
import com.receipt_processor.demo.services.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/")
    @ResponseBody
    public String show(){
        return "Hello";
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> addReceipt(@Valid @RequestBody Receipt receipt, BindingResult result) {
        Map<String,Object> response = new HashMap<>();
        if(result.hasErrors()){
            response.put("message", "The receipt is invalid.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        String receiptId = receiptService.processReceipt(receipt);
        response.put("id", receiptId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, Object>> getReceiptPoints(@PathVariable String id) {
        int points = receiptService.getReceiptPoints(id);
        Map<String, Object> response = new HashMap<>();
        if (points != -1) {
            response.put("points", points);
            return ResponseEntity.ok(response);
        }
        response.put("message", "No receipt found for that ID.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
