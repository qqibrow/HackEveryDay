package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lniu on 5/22/16.
 */
public class CoolDownTime {
        public static void main(String[] args) {
            System.out.println(cooldownBetter("ACAC", 5));
        }

        public static int cooldownBetter(String input, int N) {
            int extra = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < input.length(); i++) {
                char cur = input.charAt(i);
                if (map.containsKey(cur) && map.get(cur) >= i + extra - N) {
                    extra += (map.get(cur) - (i + extra - N)) + 1;
                    System.out.println(extra + " * ");
                }
                map.put(cur, i + extra);
            }
            System.out.println(extra);
            return input.length() + extra;
        }


}
