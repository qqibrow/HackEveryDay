package algorithm;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if(s.isEmpty() && p.isEmpty()) {
            return true;
        } else if(p.isEmpty()) {
            return false;
        } else if(s.isEmpty()) {
            if(s.length() % 2 != 0 )
                return false;
            for(int i = 0; i < p.length(); ++i) {
                char a = p.charAt(i);
                if(i % 2 == 0) {
                    if(a == '*') {
                        return false;
                    }
                } else {
                    if(a != '*') {
                        return false;
                    }
                }
            }
            return true;
        } else {
            // s is not empty and p is also not empty.
            if(p.length() >= 2) {
                char current = p.charAt(p.length() - 1);
                if(current == '*') {
                    char prev = p.charAt(p.length() - 2);
                    if(prev != '.' && prev != s.charAt(s.length() -1)) {
                        return isMatch(s.substring(0, s.length()), p.substring(0, p.length() - 2));
                    } else {
                        int j ;
                        for(j = s.length() -1; j >= 0 && (prev == '.' || s.charAt(j) == prev); --j) {
                            if(isMatch(s.substring(0, j+1), p.substring(0, p.length() - 2))) {
                                return true;
                            }
                        }
                        if(j < 0 && isMatch("", p.substring(0, p.length() - 2))) {
                            return true;
                        }
                        return false;
                    }
                } else {
                    return current == s.charAt(s.length() - 1)
                           && isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
                }


            } else {
                // assert p.length() == 1
                return s.equals(p);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching().isMatch("aaa", "ab*a"));
    }
}
