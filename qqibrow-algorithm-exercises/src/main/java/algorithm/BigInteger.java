package algorithm;

import java.util.Arrays;

/**
 * Created by lniu on 5/19/16.
 */
public class BigInteger {


    public static int[] add(int[] s1, int[] s2) {
        int[] longer = s1, shorter = s2;
        if(s1.length < s2.length) {
            longer = s2;
            shorter = s1;
        }
        int[] result = new int[longer.length + 1];
        Arrays.fill(result, 0);
        int carry = 0;
        for(int i = 0; i < longer.length; ++i) {
            int digit1 = longer[i];
            int digit2 = i >= shorter.length ? 0 : shorter[i];
            int sum = digit1 + digit2 + carry;
            result[i] = sum % 10;
            carry = sum / 10;
        }
        if(carry > 0) {
            result[longer.length] = carry;
        }
        return result;
    }

    public static int[] subtract(int[] s1, int[] s2) {
        int[] result = new int[s1.length];
        int borrow = 0;
        for(int i = 0; i < s1.length; ++i) {
            int s1Digit = s1[i];
            int s2Digit = i >= s2.length ? 0 : s2[i];
            int res = s1Digit - s2Digit - borrow + 10;
            result[i] = res % 10;
            borrow = res < 10 ? 1 : 0;

        }
        return result;
    }


    public static void main(String[] args) {
        int[] s1 = {1, 2, 3};
        int[] s2 = {9, 7, 1};
        int[] sum = BigInteger.add(s1, s2);
        for(int i : sum) {
            System.out.println(i);
        }
        int[] mi = BigInteger.subtract(s1, s2);
        for(int i : mi) {
            System.out.println(i);
        }
    }


}
