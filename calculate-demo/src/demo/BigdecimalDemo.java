package demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BigdecimalDemo {


    private static void test() {
        Double[] rangeLimit = {50.0d, 65.0d, 75.0d, 90.0d, 100.0d};
        String[] grade = {"F", "D", "C", "B", "A"};

        String maxGrade = grade[grade.length - 1];
        System.out.println(maxGrade);
    }


    public static void main(String[] args) {
//        test2();
        test();
    }

    private static void test2() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 29, 39, 11);

        double scale1 = 0.2d;
        double scale2 = 0.7d;
        double scale3 = 0.1d;

        int inputToal = 3;

        BigDecimal bigDecimal = new BigDecimal(scale1);
        bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal bigDecimal1 = new BigDecimal(inputToal);
        BigDecimal multiply = bigDecimal.multiply(bigDecimal1);
        BigDecimal bigDecimal2 = multiply.setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal2);

        BigDecimal bignum1 = new BigDecimal("10.5536");
        System.out.println("商 是：" + bignum1.setScale(0, BigDecimal.ROUND_HALF_UP));
    }

}
