package ru.tokm.tdd_example.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tokm.tdd_example.entity.CurrencyRate;
import ru.tokm.tdd_example.service.CurrencyRateService;
import ru.tokm.tdd_example.util.CurrentParser;

@Service
@RequiredArgsConstructor
public class CurrencyRateXmlService implements CurrencyRateService {

    private final CurrentParser parser;

    @Override
    public CurrencyRate getCurrencyRate(String url, String charCode) {

        List<CurrencyRate> currencyRateList = parser.parser(getRatesAsXml(url));
        return currencyRateList.stream()
                .filter(s -> s.getCharCode().equals(charCode))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Абревиатуры " + charCode + " не существует. Введите другую."));

    }

    private String getRatesAsXml(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
