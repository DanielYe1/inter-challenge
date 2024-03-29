package com.example.inter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
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

    @Test
    public void DeveriaEstourarErroPorReceberStringInvalida() {
        assertThrows(RuntimeException.class, () -> service.calculateSumDigit("as1234", 9999));
    }


}