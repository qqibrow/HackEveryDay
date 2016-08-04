package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lniu on 5/7/16.
 */
public class PalindromePartition {
        Map<Integer, List<Integer>> getPalindromes(String s) {
            Map<Integer, List<Integer>> palindromes = new HashMap<>();
            for(int i = 0; i < s.length(); ++i) {
                addPalindrome(s, i, i, palindromes);
                addPalindrome(s, i, i+1, palindromes);
            }
            return palindromes;
        }

        private void addPalindrome(String s, int left, int right, Map<Integer, List<Integer>> palindromes) {
            for(; left <= right && left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); --left, ++right) {
                if(!palindromes.containsKey(left)) {
                    palindromes.put(left, new ArrayList<>());
                }
                palindromes.get(left).add(right);
            }
        }

        public List<List<String>> partition(String s) {
            List<List<String>> results = new ArrayList<>();
            if(s == null || s.isEmpty()) {
                return results;
            }

            helper(s, 0, new ArrayList<>(), getPalindromes(s), results);
            return results;
        }

        void helper(String s, int start, ArrayList<String> curr, Map<Integer, List<Integer>> palindromes, List<List<String>> results) {
            if(start == s.length()) {
                results.add(curr);
            } else {
                // asssert palindromes.get(start) != null
                for(int end : palindromes.get(start)) {
                    curr.add(s.substring(start, end + 1));
                    helper(s, end + 1, curr, palindromes, results);
                    curr.remove(curr.size() - 1);
                }
            }
        }
    public static void main(String[] args) {
        System.out.println(new PalindromePartition().partition("a"));

    }

}
