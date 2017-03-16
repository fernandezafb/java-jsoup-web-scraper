package com.alejandro.scraper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Object that represents a product from the grocery site.
 *
 * @author afernandez
 */
public class Product {
    private String title;
    private String size;
    private String description;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    public Product() {
    }

    public Product(Builder builder) {
        this.title = builder.title;
        this.size = builder.size;
        this.unitPrice = builder.unitPrice;
        this.description = builder.description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private String title;
        private String size;
        private BigDecimal unitPrice;
        private String description;

        public Builder title(String title) {
           this.title = title;
           return this;
        }

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}