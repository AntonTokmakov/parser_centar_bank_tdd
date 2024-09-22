package ru.tokm.tdd_example.service;

import ru.tokm.tdd_example.entity.CurrencyRate;

public interface CurrencyRateService {

    CurrencyRate getCurrencyRate(String url, String charCode);

}
