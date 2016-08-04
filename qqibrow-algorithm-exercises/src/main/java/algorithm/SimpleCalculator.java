package algorithm;

import java.util.Stack;

/**
 * Created by lniu on 4/1/16.
 */
public class SimpleCalculator {
    private boolean isOperator(char s) {
        return s == '-' || s == '+';
    }

    private boolean isNumber(char s) {
        return s >= '0' && s <= '9';
    }

    public int getResult(String input) {
        return -1;
    }
    }
