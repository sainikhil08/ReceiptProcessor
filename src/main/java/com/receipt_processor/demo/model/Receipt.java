package com.receipt_processor.demo.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class Receipt {

    private String id;

    @NotBlank(message = "Retailer is required")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer name can only contain letters, numbers, spaces, dashes, and ampersands")
    private String retailer;

    @NotNull(message = "Purchase date is required")
    private LocalDate purchaseDate;

    @NotNull(message = "Purchase time is required")
    private LocalTime purchaseTime;

    @NotEmpty(message = "Receipt must have at least one item")
    @Size(min = 1, message = "At least one item is required")
    private List<Item> items;

    @NotNull(message = "Total is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Total must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Total must be a valid decimal with up to two digits after the decimal point")
    private Double total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank(message = "Retailer is required") @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer name can only contain letters, numbers, spaces, dashes, and ampersands") String getRetailer() {
        return retailer;
    }

    public void setRetailer(@NotBlank(message = "Retailer is required") @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer name can only contain letters, numbers, spaces, dashes, and ampersands") String retailer) {
        this.retailer = retailer;
    }

    public @NotNull(message = "Purchase date is required") LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(@NotNull(message = "Purchase date is required") LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public @NotNull(message = "Purchase time is required") LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(@NotNull(message = "Purchase time is required") LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public @NotEmpty(message = "Receipt must have at least one item") @Size(min = 1, message = "At least one item is required")
    List<Item> getItems() {
        return items;
    }

    public void setItems(@NotEmpty(message = "Receipt must have at least one item") @Size(min = 1, message = "At least one item is required") List<Item> items) {
        this.items = items;
    }

    public @NotNull(message = "Total is required") @DecimalMin(value = "0.00", inclusive = false, message = "Total must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total must be a valid decimal with up to two digits after the decimal point")
    Double getTotal() {
        return total;
    }

    public void setTotal(@NotNull(message = "Total is required") @DecimalMin(value = "0.00", inclusive = false, message = "Total must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total must be a valid decimal with up to two digits after the decimal point") Double total) {
        this.total = total;
    }
}
