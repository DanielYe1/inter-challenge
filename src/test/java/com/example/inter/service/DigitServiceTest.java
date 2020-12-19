package com.example.inter.service;

import com.example.inter.model.CheckDigit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class DigitServiceTest {

    @InjectMocks
    DigitCalculatorService service;

    @Test
    public void deveriaCalcularOK() {
        assertThat(service.calculateSumDigit("1234", 5), equalTo(5));
    }

    @Test
    public void DeveriaCalcularOkValoresMaximos() {
        Integer digit = service.calculateSumDigit("999999999999999999999999999999999999999999999999999999999999999", 9999);

        assertThat(digit, equalTo(9));
    }


}