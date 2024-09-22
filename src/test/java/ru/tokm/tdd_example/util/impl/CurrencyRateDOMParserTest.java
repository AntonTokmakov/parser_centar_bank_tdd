package ru.tokm.tdd_example.util.impl;

import org.junit.jupiter.api.Test;
import ru.tokm.tdd_example.entity.CurrencyRate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateDOMParserTest {

    @Test
    void shouldParse() throws IOException, URISyntaxException {
        CurrencyRateDOMParser parser = new CurrencyRateDOMParser();
        URI uri = ClassLoader.getSystemResource("rates.xml").toURI();
        String ratesXml = Files.readString(Paths.get(uri));

        List<CurrencyRate> rates = parser.parser(ratesXml);

        CurrencyRate audRate = CurrencyRate.builder()
                .numCode("036")
                .charCode("AUD")
                .nominal("1")
                .name("Австралийский доллар").value("59,7675")
                .vunitRate("59,7675").build();

        assertEquals(43, rates.size());
        assertTrue(rates.contains(audRate));
    }
}