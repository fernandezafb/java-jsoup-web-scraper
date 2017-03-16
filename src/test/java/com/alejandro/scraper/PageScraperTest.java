package com.alejandro.scraper;

import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit test for PageScraper.
 *
 * @author afernandez
 */
@RunWith(MockitoJUnitRunner.class)
public class PageScraperTest {
    private final String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    @InjectMocks
    private PageScraper pageScraper;
    @Mock
    private ScrapeService scrapeService;
    private ArrayList<Element> elements = new ArrayList<>();

    @Test
    public void testGetProductList() throws Exception {
        final Product product = new Product.Builder()
                .title("Test Title")
                .description("Test Description")
                .size("90kb")
                .unitPrice(new BigDecimal(5))
                .build();

        final Element mockElement = new Element("<a href=http://web.test.com>");
        elements.add(mockElement);
        elements.add(mockElement);

        when(scrapeService.getProducts(url)).thenReturn(elements);
        when(scrapeService.getProduct(anyString())).thenReturn(product);
        when(scrapeService.calculateTotalPrice(any())).thenReturn(new BigDecimal(10));

        final ProductList productList = pageScraper.getProductList();

        verify(scrapeService, times(2)).getProduct(anyString());

        assertEquals(product.getTitle(), productList.getProducts().get(0).getTitle());
        assertEquals(product.getDescription(), productList.getProducts().get(0).getDescription());
        assertEquals(product.getSize(), productList.getProducts().get(0).getSize());
        assertEquals(product.getUnitPrice(), productList.getProducts().get(0).getUnitPrice());

        assertEquals(product.getTitle(), productList.getProducts().get(1).getTitle());
        assertEquals(product.getDescription(), productList.getProducts().get(1).getDescription());
        assertEquals(product.getSize(), productList.getProducts().get(1).getSize());
        assertEquals(product.getUnitPrice(), productList.getProducts().get(1).getUnitPrice());

        assertEquals(new BigDecimal(10), productList.getTotal());
    }
}