package com.openpayd.exchange.integration;

        import com.openpayd.exchange.integration.adapter.rest.exchangerate.ExchangeRateRestAdapter;
        import org.junit.jupiter.api.Assertions;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractIT extends Assertions {

    @MockBean
    public ExchangeRateRestAdapter exchangeRateRestAdapter;
    @Autowired
    protected TestRestTemplate testRestTemplate;
}
