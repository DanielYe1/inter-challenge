package com.example.inter.service;

import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class DigitCalculatorService {

    private static final int MAX_INTEGER_THRESHOLD = 2147483600;
    private static final int INTEGER_ASCII_TABLE_THRESHOLD = 48;
    private static final int CACHE_SIZE = 10;

    DigitLRUCache cache = new DigitLRUCache(CACHE_SIZE);

    public Integer calculateSumDigit(String value, Integer numberTimes) {
        Integer cacheValue = cache.get(asList(value, numberTimes));
        if (cacheValue != -1) {
            return cacheValue;
        }

        String num = value;
        Integer sum = 0;

        sum = calculateStringDigit(num);
        sum = calculateIntegerDigit(sum * numberTimes);

        cache.put(asList(value, numberTimes), sum);
        return sum;
    }


    public Integer calculateStringDigit(String value) throws RuntimeException {
        if (value.matches("[0-9]+")) {
            return value.chars()
                    .map(intChar -> (intChar - '0'))
                    .sum();
        }
        throw new RuntimeException("Invalid value, it should have only numbers: " + value);
    }

    private Integer calculateStringDigitForSumHigherThanIntMax(String value) {
        String tempValue = value;
        int length = tempValue.length();
        StringBuilder builder = new StringBuilder();
        int tempSum = 0;

        while (length > 1) {
            for (int i = 0; i < length; i++) {
                tempSum += tempValue.charAt(i) - INTEGER_ASCII_TABLE_THRESHOLD;
                if (tempSum > MAX_INTEGER_THRESHOLD) {
                    builder.append(tempSum);
                    builder.setLength(0);
                    tempSum = 0;
                }
            }
            builder.append(tempSum);
            tempValue = builder.toString();
            length = tempValue.length();
            tempSum = 0;
            builder.setLength(0);
        }
        return Integer.parseInt(tempValue);
    }

    private Integer calculateIntegerDigit(Integer num) {
        int sum = 0;
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
