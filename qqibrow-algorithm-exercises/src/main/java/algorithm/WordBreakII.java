package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lniu on 5/18/16.
 */
public class WordBreakII {
    public static class Solution {
        public List<String> wordBreak(String s, Set<String> wordDict) {
            List<String> results = new ArrayList<>();
            if(s.isEmpty()) {
                return results;
            }
            boolean[] opt = new boolean[s.length() + 1];
            opt[0] = true;

            List<Integer> parents[] = new ArrayList[s.length() + 1];
            for(int i = 1; i <= s.length(); ++i) {
                opt[i] = false;
                for(int j = 0; j < i; ++j) {
                    if(opt[j] && wordDict.contains(s.substring(j, i))) {
                        opt[i] = true;
                        if(parents[i] == null) {
                            parents[i] = new ArrayList<>();
                        }
                        parents[i].add(j);
                    }
                }
            }
            if(opt[s.length()]) {
                dfs(s.length(), parents, "", results, s);
            }
            return results;
        }

        private void dfs(int end, List<Integer>[] parents, String curr, List<String> results, String raw) {
            if(end <= 0) {
                results.add(curr);
            } else {
                for(int last : parents[end]) {
                    String s = raw.substring(last, end) + " " + curr;
                    dfs(last, parents, s, results, raw);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Set<String> set = new HashSet<>();
        String[] strs = new String[]{"cat","cats","and","sand","dog"};
        for(String str : strs) {
            set.add(str);
        }
        s.wordBreak("catsanddog", set);
    }

}
