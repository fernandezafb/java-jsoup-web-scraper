package com.alejandro.scraper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for ScrapeUtil.
 *
 * @author afernandez
 */
@RunWith(MockitoJUnitRunner.class)
public class ScrapeUtilTest {
    private final String encodedUrl = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-conference-pears--ripe---ready-x4-%28minimum%29.html";
    private final String decodedUrl = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-conference-pears--ripe---ready-x4-(minimum).html";

    @Test
    public void decodeUrl() throws Exception {
        assertEquals(decodedUrl, ScrapeUtil.decodeUrl(encodedUrl));
    }

    @Test
    public void parsePrice() throws Exception {
        assertEquals(new BigDecimal("3.50"), ScrapeUtil.parsePrice("£3.50/unit"));
    }
}