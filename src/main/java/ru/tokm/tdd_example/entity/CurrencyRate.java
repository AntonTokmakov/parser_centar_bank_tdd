package ru.tokm.tdd_example.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CurrencyRate {
    private String numCode;
    private String charCode;
    private String nominal;
    private String name;
    private String value;
    private String vunitRate;

}
