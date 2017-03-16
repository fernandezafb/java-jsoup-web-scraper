package com.alejandro.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Performs the available features to crawl the product page.
 *
 * @author afernandez
 */
@Service
class ScrapeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String productSelector      = ".productInfo a";
    private final String titleSelector        = ".productTitleDescriptionContainer h1";
    private final String descriptionSelector  = ".productText p";
    private final String pricePerUnitSelector = ".pricePerUnit";

    private Document document;

    /**
     * Crawls the product's list and returns the list of elements containing their respective links.
     *
     * @param url The main url where the products are shown
     * @return A list of elements containing their respective links
     */
    public List<Element> getProducts(String url) {
        return getDocument(url).select(productSelector);
    }

    /**
     * Crawls the specific product details.
     *
     * @param url The specific url for the product
     * @return The product details
     */
    public Product getProduct(String url) {
        // Decode the URL first as it can be already encoded
        url = ScrapeUtil.decodeUrl(url);

        document = getDocument(url);

        final Element title = document.select(titleSelector).first();
        final Element description = document.select(descriptionSelector).first();
        final Element pricePerUnit = document.select(pricePerUnitSelector).first();

        return new Product.Builder()
                .title(title.text())
                .size(document.outerHtml().length() + "kb")
                .description(description.text())
                .unitPrice(ScrapeUtil.parsePrice(pricePerUnit.text()))
                .build();
    }

    /**
     * Calculates the total price of the product's list.
     *
     * @return The total price
     */
    public BigDecimal calculateTotalPrice(List<Product> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getUnitPrice());
        }

        return total;
    }

    /**
     * Returns the document page for a given URL.
     *
     * @param url The given url
     * @return The document page
     */
    private Document getDocument(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            final String error = String.format("Cannot retrieve web document from URL: %s", url);

            logger.error(error);
            throw new IllegalStateException(error);
        }
        return document;
    }
}