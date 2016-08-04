package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lniu on 4/23/16.
 */
public class PalindromePermutation {
        public List<String> generatePalindromes(String s) {
            if(s == null || s.length() == 0) {
                return new ArrayList<String>();
            }
            int odd = canPermutePalindrome(s);
            if(odd > 1) {
                return new ArrayList<String>();
            }
            int[] counts = new int[256];
            for(char c: s.toCharArray()) {
                ++counts[c];
            }
            StringBuilder builder = new StringBuilder();
            char oddchar = ' ';
            for(char c: s.toCharArray()) {
                int halfCounts = counts[c] / 2;
                while(halfCounts > 0) {
                    builder.append(c);
                    halfCounts--;
                }
                if(counts[c] % 2 != 0) {
                    oddchar = c;
                }
            }
            String half = builder.toString();
            List<String> allhalfs = getAllPermutations(half.toCharArray());
            List<String> results = new ArrayList<>();
            for(String h : allhalfs) {
                StringBuilder b = new StringBuilder();
                b.append(h);
                b.reverse();
                if(odd == 1) {
                    b.append(oddchar);
                }
                b.append(h);
                results.add(b.toString());
            }
            return results;
        }

        List<String> getAllPermutations(char[] str) {
            Arrays.sort(str);
            boolean[] visited = new boolean[str.length];
            Arrays.fill(visited, false);
            List<String> results = new ArrayList<>();
            dfs(0, str, visited, "", results);
            return new ArrayList<String>(results);
        }

        void dfs(int start, char[] str, boolean[] visited, String result, List<String> results) {
            if(result.length() == str.length) {
                results.add(result);
            } else {
                char last = ' ';
                for(int i = 0; i < str.length; ++i) {
                    if(last != str[i]) {
                        if(!visited[i]) {
                            visited[i] = true;
                            dfs(i+1, str, visited, result + str[i], results);
                            visited[i] = false;
                            last = str[i];
                        }
                    }

                }
            }
        }

        public int canPermutePalindrome(String s) {
            int odd = 0;
            int[] counts = new int[256];
            for(char c : s.toCharArray()) {
                odd += (++counts[c] & 1) > 0 ? 1 : -1;
            }
            return odd;
        }

    public static void main(String[] args) {
        List<String> results = new PalindromePermutation().getAllPermutations(new char[]{'a', 'a', 'b'});
        for(String str: results) {
            System.out.println(str);
        }
    }
}
