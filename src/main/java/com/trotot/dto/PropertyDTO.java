package com.trotot.dto;

import com.trotot.model.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PropertyDTO {
    @NotEmpty(message = "The title is required")
    private String title;
    @NotEmpty(message = "The title is required")
    private String address;

    @Size(min = 10, message = "The description should be at least 10 characters")
    @Size(max = 2000, message = "The description cannot exceed 2000 characters")
    private String description;

   private Category category;


    @Min(0)
    private double price;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
public Category getCategory() {
    return category;
}

public void setCategory(Category category) {
    this.category = category;
}
}
