package com.alejandro;

import com.alejandro.scraper.PageScraper;
import com.alejandro.scraper.ProductList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class SainsburyApplicationTest {

    @InjectMocks
    private SainsburyApplication sainsburyApplication;
    @Mock
    private PageScraper pageScraper;
    @Mock
    private ObjectMapper mapper;

    @Test
    public void testRun() throws Exception {
	sainsburyApplication.run();

        verify(mapper).writeValueAsString(any(ProductList.class));
        verify(pageScraper).getProductList();
    }
}
