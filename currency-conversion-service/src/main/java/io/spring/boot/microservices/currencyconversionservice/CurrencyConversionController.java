package io.spring.boot.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy exchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionHolder convertCurrency(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable BigDecimal quantity) {

        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionHolder> responseEntity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionHolder.class,
                uriVariables);

        CurrencyConversionHolder response = responseEntity.getBody();

        return new CurrencyConversionHolder(response.getId(), from, to,
                response.getExchangeRate(), quantity,
                quantity.multiply(response.getExchangeRate()),
                response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionHolder convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
                                                    @PathVariable BigDecimal quantity) {

        CurrencyConversionHolder response = exchangeServiceProxy.getExchangeValue(from, to);

        return new CurrencyConversionHolder(response.getId(), from, to, response.getExchangeRate(), quantity,
                quantity.multiply(response.getExchangeRate()), response.getPort());
    }
}
