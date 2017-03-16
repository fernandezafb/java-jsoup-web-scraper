package com.alejandro.scraper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration Test for PageScraper.
 *
 * @author afernandez
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageScraperIT {

    @Autowired
    private PageScraper pageScraper;

    @Test
    public void testGetProductList() {
        final ProductList productList = pageScraper.getProductList();

        assertEquals(7, productList.getProducts().size());
        assertEquals(new BigDecimal("15.10"), productList.getTotal());

        for (Product product : productList.getProducts()) {
            assertNotNull(product.getTitle());
            assertNotNull(product.getSize());
            assertNotNull(product.getDescription());
            assertNotNull(product.getUnitPrice());
        }
    }
}