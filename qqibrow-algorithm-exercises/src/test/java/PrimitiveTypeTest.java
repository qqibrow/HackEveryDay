/**
 * Created by lniu on 12/23/15.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class PrimitiveTypeTest {
    @Test
    public void howMany1sInNumber() {
        int number = 5;
        int input = number;
        int res = 0;
        while(input != 0) {
           res += (input & 1);
            input = input >> 1;
        }
        System.out.println(res);
    }

    @Test
    public void testTwoSum() {
        TwoSum twoSum = new TwoSum();
        int[] inputs = {2, 4, 3, 1, 5};
        int[] res = twoSum.twoSum(inputs, 4);
        for(int i : res) {
            System.out.println(i);
        }
    }

}
