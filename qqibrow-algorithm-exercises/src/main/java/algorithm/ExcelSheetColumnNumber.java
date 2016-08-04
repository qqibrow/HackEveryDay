package algorithm;

/**
 * Created by lniu on 4/7/16.
 */
public class ExcelSheetColumnNumber {
        public int helper(String s, int pos) {
            if(pos < 0) {
                return 0;
            } else {
                char curr = s.charAt(pos);
                int number = curr - 'A' + 1;
                return helper(s, pos-1) * 26  + number;
            }
        }

        public int titleToNumber(String s) {
            return helper(s, s.length() - 1);
        }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnNumber().titleToNumber("A"));
    }
}
