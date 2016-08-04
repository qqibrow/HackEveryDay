package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lniu on 5/9/16.
 */
public class GeneratePigFilter {
    public static void main(String[] args) {
        int[] linIds = new int[] {
            117755,141487,150608,151756,155982,157665,163344,163345,163346,164256,168567,172784,174480,175623,
            169097,174856,155483,173112,176591,117754,148426,178660,164387,107834,178054,152216,180216,179935,149640
        };
        List<String> stringids = new ArrayList<>();
        for(int linid : linIds) {
            stringids.add("line_id==" + String.valueOf(linid));
        }

        System.out.println(String.join(" OR ", stringids));
    }

}
