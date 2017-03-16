package com.alejandro.scraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;

/**
 * Utility class with helper methods for the scraper service.
 *
 * @author afernandez
 */
public class ScrapeUtil {
    private final static Logger logger = LoggerFactory.getLogger(ScrapeUtil.class);

    /**
     * Decodes an url.
     *
     * @param url The URL to decode
     * @return The url already decoded
     */
    public static String decodeUrl(String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            final String error = String.format("Cannot decode the URL: %s", url);

            logger.error(error);
            throw new IllegalStateException(error);
        }
        return url;
    }

    /**
     * Parses the price in string for with special character to its corresponding double value.
     *
     * @param price The price in string format
     * @return The price as a double numeric value
     */
    public static BigDecimal parsePrice(String price) {
        return new BigDecimal(price.substring(1, price.indexOf("/")));
    }
}