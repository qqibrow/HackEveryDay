import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lniu on 4/25/16.
 */
public class ExpressionAddOperator {
        private class ExpValuePair {
            int value;
            String expression;
            public ExpValuePair(int v) {
                value = v;
                expression = "";
            }
            public ExpValuePair(int v, String e) {
                value = v;
                expression = e;
            }
            public ExpValuePair plus(ExpValuePair other) {
                return new ExpValuePair(this.value + other.value,
                                        this.expression + "+" + other.expression);
            }

            public ExpValuePair times(ExpValuePair other) {
                return new ExpValuePair(this.value * other.value,
                                        this.expression + "*" + other.expression);
            }

            public ExpValuePair minus(ExpValuePair other) {
                return new ExpValuePair(this.value - other.value,
                                        this.expression + "-" + other.expression);
            }
            public boolean equals(Object other) {
                if(other == null) return false;
                if(! (other instanceof ExpValuePair)) return false;

                ExpValuePair otherExp = (ExpValuePair) other;
                return this.expression.equals(otherExp.expression) && this.value == otherExp.value;
            }

            public int hashCode() {
                return value;
            }
        }

        public List<String> addOperators(String num, int target) {
            List<String> results = new ArrayList<>();
            if(num == null || num.length() == 0) {
                return results;
            }
            List<ExpValuePair> expValuePairs = getResult(num, 0, num.length()-1, target);
            HashSet<ExpValuePair> filter = new HashSet<>();

            for(ExpValuePair pair: expValuePairs) {
                if(pair.value == target)
                filter.add(pair);
            }
            for(ExpValuePair pair: filter) {
                results.add(pair.expression);
            }

            return results;
        }

        private List<ExpValuePair> getResult(String num, int start, int end, int target) {
            List<ExpValuePair> results = new ArrayList<>();
            if(start == end) {
                results.add(new ExpValuePair(num.charAt(start) - '0', num.substring(start, start + 1)));
            } else {
                for(int k = start; k < end; ++k) {
                    List<ExpValuePair> lefts = getResult(num, start, k, target);
                    List<ExpValuePair> rights = getResult(num, k + 1, end, target);
                    for(ExpValuePair left: lefts) {
                        for(ExpValuePair right: rights) {
                            results.add(left.plus(right));
                            results.add(left.times(right));
                            results.add(left.minus(right));
                        }
                    }
                }
            }
            return results;
        }
    public static void main(String[] args) {
        List<String> strings = new ExpressionAddOperator().addOperators("123", 6);
        for(String str : strings) {
            System.out.println(str);
        }
    }

}
