import java.util.Arrays;

/**
 * Created by lniu on 4/5/16.
 */
public class LongestSubStringWithoutRepeatingChar {
    public int lengthOfLongestSubstring(String s) {
        int[] lastshown = new int[256];
        Arrays.fill(lastshown, -1);
        int start = -1;
        int len = 0;
        for(int i = 0; i < s.length(); ++i) {
            char curr = s.charAt(i);
            if(lastshown[curr] == -1) {
                lastshown[curr] = i;
                len = Math.max(len, i - start);
            } else {
                // has seen curr before at position lastshown[curr]
                len = Math.max(len, i - lastshown[curr]);
                for(int j = start + 1; j < lastshown[curr]; ++j) {
                    lastshown[j] = -1;
                }
                start = lastshown[curr];
                lastshown[curr] = i;
            }
        }
        return len;
    }
    public static void main(String[] args) {
        System.out.println(new LongestSubStringWithoutRepeatingChar().lengthOfLongestSubstring("abba"));
    }

}
