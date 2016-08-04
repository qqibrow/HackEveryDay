package algorithm;

/**
 * Created by lniu on 4/23/16.
 */
public class IntegerToEnglishWords {
        String[] lesstwenty = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fivteen", "Sixteen", "Seventeen", "Eighten", "Ninteen"};
        String[] tens = {"Twtenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninty"};
        String[] handrands = {"Handrand", "Thousand", "Millon", "Billion"};
        public String numberToWords(int num) {
            int part = 1000;
            return "";

        }
        public String getlessThanOneHandrad(int num) {
            // assume 0 < num < 100
            if(num < 20) {
                return lesstwenty[num - 1];
            }
            int digit = num / 10;
            StringBuilder builder = new StringBuilder();
            builder.append(tens[digit-2]);
            builder.append(" ");
            builder.append(lesstwenty[num % 10 - 1]);
            return builder.toString();
        }
        public String getLessThanOneThousand(int num) {
            // assume num < 1000
            StringBuilder builder = new StringBuilder();
            int hands = num / 100;
            builder.append(lesstwenty[hands-1]);
            builder.append(" ");
            builder.append(handrands[0]);
            int rest = num % 100;
            builder.append(getlessThanOneHandrad(rest));
            return builder.toString();
        }

}
