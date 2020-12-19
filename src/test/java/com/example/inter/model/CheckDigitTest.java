package com.example.inter.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckDigitTest {

    @Test
    public void deveriaCalcularOK(){
        CheckDigit digit = new CheckDigit("1234",5);

        assertThat(digit.calculateCheckDigit(), equalTo(5));
    }

    @Test
    public void DeveriaCalcularOkValoresMaximos(){
        CheckDigit digit = new CheckDigit("999999999999999999999999999999999999999999999999999999999999999",9999);

        assertThat(digit.calculateCheckDigit(), equalTo(9));
    }
}