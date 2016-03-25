import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lniu on 3/24/16.
 */
public class ReverseWords {
    public String reverseWords(String s) {
        if(s.isEmpty()) {
            return s;
        }
        String[] strs = s.split(" ");
        for(int i = 0, j = strs.length - 1; i < j; ++i, --j) {
            String tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < strs.length; ++i) {
            builder.append(strs[i]);
            if(i < strs.length - 1) {
                builder.append(' ');
            }
        }
        return  builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWords().reverseWords(" 1"));
    }
}
