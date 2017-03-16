package com.alejandro;

import com.alejandro.scraper.PageScraper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SainsburyApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PageScraper pageScraper;
    @Autowired
    private ObjectMapper mapper;

	public static void main(String[] args) {
		SpringApplication.run(SainsburyApplication.class, args);
	}

    @Override
    public void run(String... strings) {
	    logger.info("Execution started, ready to begin crawling.");
        try {
            System.out.println(mapper.writeValueAsString(pageScraper.getProductList()));
        } catch (JsonProcessingException e) {
            final String error = String.format("It was not possible to parse the product list to JSON format.");

            logger.error(error);
            throw new IllegalStateException(error);
        }
        logger.info("Execution finished, products retrieved in JSON format.");
    }
}