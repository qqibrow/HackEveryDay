import org.junit.Assert;

/**
 * Created by lniu on 3/28/16.
 */
public class CheckUniqueChar {
    class Bitset {
        private int buckets = 0;
        public boolean exists(char s) {
            int offset = s - 'a';
            return ((buckets >> offset) & 1) == 1;
        }
        public void put(char s) {
            int offset = s - 'a';
            buckets = buckets | (1 << offset);
        }
    }

    public boolean isAllUnique(String input) {
        Bitset bitset = new Bitset();
        for(char s: input.toCharArray()) {
            if(bitset.exists(s)) {
                return false;
            }
            bitset.put(s);
        }
        return true;
    }
    public static void main(String[] args) {
        Assert.assertTrue(new CheckUniqueChar().isAllUnique("abcd"));
        Assert.assertFalse(new CheckUniqueChar().isAllUnique("abcetuc"));
    }

}
