package model;

public class CheckDigit {
    private Integer valueString;
    private Integer numberTimes;
    private Integer digit;

    public CheckDigit(Integer valueString, Integer numberTimes) {
        this.valueString = valueString;
        this.numberTimes = numberTimes;
    }

    public Integer calculateCheckDigit() {
        Integer num = valueString;
        Integer sum = 0;

        while (num > 0 || sum > 9) {
            if (num == 0) {
                num = sum;
                sum = 0;
            }
            sum += num % 10;
            num /= 10;
        }

        num = sum * numberTimes;
        sum = 0;

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

    private Integer calculateMethod(Integer sum, Integer num) {

        return sum;
    }
}
