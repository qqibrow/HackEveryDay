package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lniu on 4/23/16.
 */
public class LongestSubStringTwoDistinct {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> charWithLastIndexMap = new HashMap<>();
        int globalMax = 0;
        int start = 0;
        for(int i = 0 ; i < s.length(); ++i) {
            char curr = s.charAt(i);
            if(charWithLastIndexMap.size() == 2 && !charWithLastIndexMap.containsKey(curr)) {
                // find the char that shown left most.
                int leftMost = s.length();
                char target = ' ';
                for(char c : charWithLastIndexMap.keySet()) {
                    int last = charWithLastIndexMap.get(c).intValue();
                    if(last < leftMost) {
                        target = c;
                        leftMost = last;
                    }
                }
                charWithLastIndexMap.remove(target);
                start = leftMost + 1;
            }
            charWithLastIndexMap.put(curr, i);
            globalMax = Math.max(globalMax, i - start + 1);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStringTwoDistinct().lengthOfLongestSubstringTwoDistinct("eceba"));
    }

}
