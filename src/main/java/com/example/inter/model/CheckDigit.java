package com.example.inter.model;

public class CheckDigit {
    private String value;
    private Integer numberTimes;
    private Integer digit;

    public CheckDigit(String value, Integer numberTimes, Integer digit) {
        this.value = value;
        this.numberTimes = numberTimes;
        this.digit = digit;
    }

    public String getValue() {
        return value;
    }

    public Integer getNumberTimes() {
        return numberTimes;
    }

    public Integer getDigit() {
        return digit;
    }
}
