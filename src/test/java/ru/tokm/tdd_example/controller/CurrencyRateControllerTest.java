package ru.tokm.tdd_example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.tokm.tdd_example.service.impl.CurrencyRateXmlService;
import ru.tokm.tdd_example.util.impl.CurrencyRateDOMParser;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyRateController.class)
@Import({CurrencyRateXmlService.class, CurrencyRateDOMParser.class})  // Для того чтоб внедрить зависимость в контроллер и сервис соответственно

class CurrencyRateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindRatesByNameAndDate() throws Exception {
        String currency = "USD";
        String date = "01-01-2021";

        String result = mockMvc.perform(get(String.format("/api/v1/currency-rate/%s/%s", currency, date)))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"numCode\":\"840\",\"charCode\":\"USD\",\"nominal\":\"1\",\"name\":\"Доллар США\",\"value\":\"73,8757\",\"vunitRate\":\"73,8757\"}", result);
    }

}