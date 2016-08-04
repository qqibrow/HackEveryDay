package algorithm;

/**
 * Created by lniu on 3/27/16.
 */
public class CompareVersionNumber {
        public int compareVersion(String version1, String version2) {
            String[] version1substrs = version1.split("\\.");
            String[] version2substrs = version2.split("\\.");
            int len = Math.max(version1substrs.length, version2substrs.length);
            for(int i = 0; i < len; ++i) {
                int v1 = i < version1substrs.length? Integer.valueOf(version1substrs[i]) : 0;
                int v2 = i < version2substrs.length? Integer.valueOf(version2substrs[i]) : 0;
                if(v1 == v2) {
                    continue;
                } else if(v1 > v2) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }
    public static void main(String[] args) {
        System.out.println(new CompareVersionNumber().compareVersion("1.0", "1"));
    }
}
