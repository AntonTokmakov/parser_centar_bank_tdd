package ru.tokm.tdd_example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.tokm.tdd_example.entity.CurrencyRate;
import ru.tokm.tdd_example.service.CurrencyRateService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CurrencyRateController {

    @Value("${CBR_URL}")
    private String CBR_URL;

    private final CurrencyRateService rateService;

    @GetMapping("currency-rate/{charCode}/{date}")
    public CurrencyRate getCurrency(@PathVariable String charCode,
                                    @DateTimeFormat(pattern = "dd-MM-yyyy")
                                    @PathVariable LocalDate date){

        String urlWithDate = String.format("%s?date_req=%s", CBR_URL, DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date));
        return rateService.getCurrencyRate(urlWithDate, charCode);

    }


}
