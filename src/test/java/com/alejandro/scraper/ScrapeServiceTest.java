package com.alejandro.scraper;

import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit Tests for ScrapeService.
 *
 * @author afernandez
 */
@RunWith(MockitoJUnitRunner.class)
public class ScrapeServiceTest {
    private final String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    private final String productUrl = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-avocado--ripe---ready-x2.html";

    @InjectMocks
    private ScrapeService scrapeService;

    @Test
    public void testGetProducts() throws Exception {
        final List<Element> elements = scrapeService.getProducts(url);

        assertEquals(7, elements.size());
    }

    @Test
    public void testGetProduct() throws Exception {
        final Product product = scrapeService.getProduct(productUrl);

        assertEquals("Sainsbury's Avocado, Ripe & Ready x2", product.getTitle());
        assertEquals("40441kb", product.getSize());
        assertEquals("Avocados", product.getDescription());
        assertEquals(new BigDecimal("1.80"), product.getUnitPrice());
    }

    @Test
    public void testCalculateTotalPrice() throws Exception {
        final Product product = new Product.Builder()
                .unitPrice(new BigDecimal(3.50))
                .build();

        final List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product);

        assertEquals(new BigDecimal("7.0"), scrapeService.calculateTotalPrice(products));
    }
}