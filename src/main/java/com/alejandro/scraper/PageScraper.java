package com.alejandro.scraper;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Main component.
 *
 * @author afernandez
 */
@Component
public class PageScraper {
    private final String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    @Autowired
    private ScrapeService scrapeService;

    public ProductList getProductList() {
        final List<Element> elements = scrapeService.getProducts(url);

        List<Product> products = elements.stream()
                .map(element -> scrapeService.getProduct(element.attr("href")))
                .collect(Collectors.toList());

        return new ProductList(products, scrapeService.calculateTotalPrice(products));
    }
}