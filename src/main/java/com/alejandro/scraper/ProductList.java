package com.alejandro.scraper;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a list of products from the grocery site, including its total price of all products.
 *
 * @author afernandez
 */
public class ProductList {
    private List<Product> products;
    private BigDecimal total;

    public ProductList(List<Product> products, BigDecimal total) {
        this.products = products;
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}