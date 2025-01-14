package com.receipt_processor.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Item {

    @NotBlank(message = "Short description is required")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Short description can only contain letters, numbers, spaces, and dashes")
    private String shortDescription;

    @NotBlank(message = "Price is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be a valid decimal with two digits after the decimal point")
    private double price;

    public @NotBlank(message = "Short description is required") @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Short description can only contain letters, numbers, spaces, and dashes")
    String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(@NotBlank(message = "Short description is required") @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Short description can only contain letters, numbers, spaces, and dashes") String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    @NotBlank(message = "Price is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be a valid decimal with two digits after the decimal point")
    public double getPrice() {
        return price;
    }

    public void setPrice(@NotBlank(message = "Price is required") @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be a valid decimal with two digits after the decimal point") double price) {
        this.price = price;
    }
}
