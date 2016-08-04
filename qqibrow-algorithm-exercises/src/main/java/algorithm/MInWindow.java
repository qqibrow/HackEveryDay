package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lniu on 5/21/16.
 */
public class MInWindow {
        public String minWindow(String s, String t) {
            String min = "";
            int minLen = s.length() + 1;
            Map<Character, Integer> targetCharCounts = new HashMap<>();
            int total = 0;
            for(char c : t.toCharArray()) {
                if(targetCharCounts.containsKey(c)) {
                    targetCharCounts.put(c, targetCharCounts.get(c) + 1);
                } else {
                    targetCharCounts.put(c, 1);
                }
                ++total;
            }

            Map<Character, Integer> charCounts = new HashMap<>();
            for(char c : targetCharCounts.keySet()) {
                charCounts.put(c, 0);
            }
            int last = -1;
            int valid = 0;
            for(int i = 0; i < s.length(); ++i) {
                char currChar = s.charAt(i);
                if(targetCharCounts.containsKey(currChar)) {
                    if(charCounts.get(currChar) < targetCharCounts.get(currChar)) {
                        ++valid;
                    }
                    charCounts.put(currChar, charCounts.get(currChar) + 1);
                }
                while(valid == total) {
                    if(i - last < minLen) {
                        minLen = i - last;
                        min = s.substring(last + 1, i+1);
                    }
                    ++last;
                    char tail = s.charAt(last);
                    if(charCounts.containsKey(tail)) {
                        if(charCounts.get(tail) == targetCharCounts.get(tail)) {
                            --valid;
                        }
                        charCounts.put(tail, charCounts.get(tail) - 1);

                    }
                }
            }
            return min;
        }
    public static void main(String[] args) {
        System.out.println(new MInWindow().minWindow("bba", "ab"));
    }
}
