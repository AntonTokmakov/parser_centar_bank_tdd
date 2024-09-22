package ru.tokm.tdd_example.util;

import ru.tokm.tdd_example.entity.CurrencyRate;

import java.util.List;

@FunctionalInterface
public interface CurrentParser {

    List<CurrencyRate> parser(String ratesAsString);

}
