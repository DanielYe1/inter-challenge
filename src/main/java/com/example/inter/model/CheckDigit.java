package com.example.inter.model;

public class CheckDigit {
    private Integer value;
    private Integer numberTimes;
    private Integer digit;

    public CheckDigit(Integer value, Integer numberTimes) {
        this.value = value;
        this.numberTimes = numberTimes;
    }

    public Integer calculateCheckDigit() {
        Integer num = value;
        Integer sum = 0;

        sum = calculateMethod(sum, num);

        num = sum * numberTimes;
        sum = 0;

        sum = calculateMethod(sum, num);

        return sum;
    }

    private Integer calculateMethod(Integer sum, Integer num) {
        while (num > 0 || sum > 9) {
            if (num == 0) {
                num = sum;
                sum = 0;
            }
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
